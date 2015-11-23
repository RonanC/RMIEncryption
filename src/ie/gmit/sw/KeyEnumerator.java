package ie.gmit.sw;

public class KeyEnumerator {
	// scores are not working
	private Vigenere cypher;
	private QuadgramMap map = null;
	private float bestScore;
	private String bestKey;
	
	public KeyEnumerator() throws Exception{
		map = new QuadgramMap("./warpeace.txt");
	}
	
	private char[] getNextKey(char[] key){
		for (int i = key.length - 1; i >=0; i--){
			if (key[i] =='Z'){
				if (i == 0) return null;
				key[i] = 'A';
			}else{
				key[i]++;
				break;
			}
		}
		return key;
	}
	
	
	public String crackCypher(String cypherText, int maxKeyLength){
		char[] key = null;
		
		int counter = 0;
		for (int j = 3; j <= maxKeyLength; j++){
			key = new char[j];
			for (int k = 0; k < key.length; k++) key[k] = 'A';
			
			do{
				counter++;
				String result = new Vigenere(new String(key)).doCypher("TVHUGTUDHKOURUGTS", false);
				float score = map.getScore(result);
				
				System.out.println(score);
				if (score > bestScore) {
					bestKey = new String(key);
					System.out.println(bestKey);
				}
				//System.out.println(result); //new String(key)
				
			}while ((key = getNextKey(key)) != null);
		}
		System.out.println("Enumerated " + counter + " keys.");
		String yahoo = new Vigenere(bestKey).doCypher("TVHUGTUDHKOURUGTS", false);
		System.out.println(yahoo);
		
		return yahoo;
	}
	
	public static void main(String[] args) throws Exception {
		new KeyEnumerator().crackCypher("TVHUGTUDHKOURUGTS", 3);
		
	}
}