package ie.gmit.sw.cipher;

public class KeyEnumerator {
	private Vigenere cipher;
	private QuadgramMap map = null;
	private float bestScore = -1f;
	private String bestKeyWord;

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

				if (score > bestScore) {
					bestScore = score;
					bestKeyWord = new String(key);
				}

			} while ((key = getNextKey(key)) != null);
		}
		System.out.println("Enumerated " + counter + " keys.");
		String mostEnglishWord = new Vigenere(bestKeyWord).doCipher(cypherText, false);
		System.out.println("Best key: " + bestKeyWord + "\tScore of: " + bestScore);
		System.out.println(cypherText + " decrypted with a key of " + bestKeyWord + " outputs: " + mostEnglishWord);

		String[] data = { mostEnglishWord, bestKeyWord };

		resetVars();
		
		return data;
	}

	private void resetVars() {
		// reset data
		bestKeyWord = null;
		bestScore = -1f;
	}

//	public static void main(String[] args) throws Exception {
//		new KeyEnumerator().crackCipher("JNORDDBENCAWUINQMZWTVAIVWINV", 3);
//	}
}