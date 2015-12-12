package ie.gmit.sw.cipher;

public class KeyEnumerator {
	// scores are not working
	private Vigenere cipher;
	private QuadgramMap map = null;
	private float bestScore = -999.99f;
	private String bestKey;

	public KeyEnumerator() throws Exception {
		map = new QuadgramMap("./WarAndPeace-Tolstoy.txt");
	}

	// updates key by one char
	private char[] getNextKey(char[] key) {
		for (int i = key.length - 1; i >= 0; i--) {
			if (key[i] == 'Z') {
				if (i == 0)
					return null;
				key[i] = 'A';
			} else {
				key[i]++;
				break;
			}
		}
		return key;
	}

	public String[] crackCipher(String cypherText, int maxKeyLength) {
		char[] key = null;

		int counter = 0;
		// key must be at least 3 characters
		for (int j = 3; j <= maxKeyLength; j++) {
			key = new char[j];
			for (int k = 0; k < key.length; k++)
				// each letter of the key will start as being 'A'
				key[k] = 'A';

			do {
				counter++;
				// checking the cipher against our key
				String result = new Vigenere(new String(key)).doCipher(cypherText, false);
				
				// get the score
				float score = map.getScore(result);

				// debug, check key
				//System.out.println(key);
				
				// print score
				//System.out.print("Score: " + score);
				if (score > bestScore) {
					// custom
					bestScore = score;
					
					bestKey = new String(key);
					//System.out.print("\tnew best key: " + bestKey);
				}
				
				// debug
//				if (result.charAt(0) == 'A') {
//					System.out.println(result);
//				}
				 //System.out.println(result); //new String(key)
				//System.out.println();
				
			} while ((key = getNextKey(key)) != null);
		}
		System.out.println("Enumerated " + counter + " keys.");
		String mostEnglishWord = new Vigenere(bestKey).doCipher(cypherText, false);
		System.out.println("Best key: " + bestKey + "\tScore of: " + bestScore);
		System.out.println(cypherText + " decrypted with a key of " + bestKey + " outputs: " + mostEnglishWord);

//		System.out.println(map.getMap());
		
		String[] data = {mostEnglishWord, bestKey};
		
		return data;
	}

	public static void main(String[] args) throws Exception {
		 new KeyEnumerator().crackCipher("JNORDDBENCAWUINQMZWTVAIVWINV", 3);

	}
}