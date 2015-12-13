package ie.gmit.sw.cipher;

// the key and the plain text will be converted to upper case and white space is trimmed.

// this is the 
public class Vigenere { // Blaise de Vigenere is (incorrectly) accredited with
						// inventing this encryption mechanism
	private char[] key; // Store the cypher key as a char array for convenience

	// The tabula recta represents a 26x26 array of characters. For a message of
	// length n, there are 26^n combinations.
	private char[][] tabulaRecta = {
			{ 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
					'V', 'W', 'X', 'Y', 'Z' },
			{ 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
					'W', 'X', 'Y', 'Z', 'A' },
			{ 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
					'X', 'Y', 'Z', 'A', 'B' },
			{ 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
					'Y', 'Z', 'A', 'B', 'C' },
			{ 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
					'Z', 'A', 'B', 'C', 'D' },
			{ 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
					'A', 'B', 'C', 'D', 'E' },
			{ 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A',
					'B', 'C', 'D', 'E', 'F' },
			{ 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B',
					'C', 'D', 'E', 'F', 'G' },
			{ 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C',
					'D', 'E', 'F', 'G', 'H' },
			{ 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D',
					'E', 'F', 'G', 'H', 'I' },
			{ 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E',
					'F', 'G', 'H', 'I', 'J' },
			{ 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F',
					'G', 'H', 'I', 'J', 'K' },
			{ 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
					'H', 'I', 'J', 'K', 'L' },
			{ 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
					'I', 'J', 'K', 'L', 'M' },
			{ 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
					'J', 'K', 'L', 'M', 'N' },
			{ 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
					'K', 'L', 'M', 'N', 'O' },
			{ 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
					'L', 'M', 'N', 'O', 'P' },
			{ 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
					'M', 'N', 'O', 'P', 'Q' },
			{ 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
					'N', 'O', 'P', 'Q', 'R' },
			{ 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
					'O', 'P', 'Q', 'R', 'S' },
			{ 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
					'P', 'Q', 'R', 'S', 'T' },
			{ 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
					'Q', 'R', 'S', 'T', 'U' },
			{ 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
					'R', 'S', 'T', 'U', 'V' },
			{ 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
					'S', 'T', 'U', 'V', 'W' },
			{ 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
					'T', 'U', 'V', 'W', 'X' },
			{ 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
					'U', 'V', 'W', 'X', 'Y' } };

	// Null constructor
	public Vigenere() {

	}

	// The class constructor takes a String key as a parameter
	public Vigenere(String key) {
		setKey(key); // Pass the parameter to the setKey method below
	}

	/*
	 * Strip leading/trailing whitespace from the String, convert it to
	 * upper-case and then convert it into an array and assign it to the
	 * instance variable key
	 */
	public void setKey(String s) {
		this.key = s.trim().toUpperCase().toCharArray();
	}

	public void setKey(char[] key) {
		this.key = key;
	}

	/*
	 * Return the character given by the intersection of the row of the keyword
	 * character and the column of the plain-text character. If no such
	 * intersection exists, return the (unencrypted) plain-text character.
	 */
	private char getEncryptedCharacter(char key, char plain) {
		for (int rows = 0; rows < tabulaRecta.length; rows++) {
			if (tabulaRecta[rows][0] == key) {
				for (int cols = 0; cols < tabulaRecta[rows].length; cols++) {
					if (tabulaRecta[0][cols] == plain) {
						return tabulaRecta[rows][cols];
					}
				}
			}
		}
		return plain;
	}

	/*
	 * Return the character in the first column of the row containing the cipher
	 * character that intersects with the column containing the keyword
	 * character.
	 */
	private char getDecryptedCharacter(char key, char cipher) {
		for (int cols = 0; cols < tabulaRecta[0].length; cols++) {
			if (tabulaRecta[0][cols] == key) {
				for (int rows = 0; rows < tabulaRecta.length; rows++) {
					if (tabulaRecta[rows][cols] == cipher) {
						return tabulaRecta[rows][0];
					}
				}
			}
		}
		return cipher;
	}

	/*
	 * The duplication in the encrypt() and decrypt() methods invites us to
	 * optimise and simplify the class, by combining their functionality in a
	 * new method called doCipher()
	 */
	public String doCipher(char[] text, boolean encrypt) {
		StringBuffer buffer = new StringBuffer();
		int j = 0;
		
		for (int i = 0; i < text.length; i++) {
			// we don't check punctuation, only A to Z
			// A = 65
			// Z = 90
			// if the letter is less then 65 or greater then 90 we skip this
			// iteration of the loop
			if (text[i] < 'A' || text[i] > 'Z')
				continue; // break leaves the loop, continue skips to the next
							// iteration
			// int j = 0;
			// if (i < key.length) j = i;

			// resets the key back to the first char
			if (j >= key.length)
				j = 0; // or j = j % key.length

			// sends in the letter with it's corresponding Caesar key
			if (encrypt) {				// Caesar part
				// encrypt
				buffer.append(getEncryptedCharacter(key[j], text[i]));
			} else {				// Caesar part
				// decrypt
				buffer.append(getDecryptedCharacter(key[j], text[i]));
			}
			j++;
		}
		
		// too many logs when you run crack:
//		if (encrypt) {
//			System.out.println("encrypted the word: " + text.toString() + ", with the keyword: " + key.toString() + ", result: " + buffer.toString());
//		}else{
//			System.out.println("decrypted the word: " + text.toString() + ", with the keyword: " + key.toString() + ", result: " + buffer.toString());
//		}
		
		return buffer.toString();
	}

	public String doCipher(String s, boolean encrypt) {
		char[] text = s.trim().toUpperCase().toCharArray();
		return doCipher(text, encrypt);
	}

//	public static void main(String[] args) {
//		Vigenere v = new Vigenere("JAV");
//		String cipherTxt = v.doCipher("ANTIDISESTABLISHMENTARIANISM", true);
//		System.out.println(cipherTxt);
//
//		String plainTxt = v.doCipher(cipherTxt, false);
//		System.out.println(plainTxt);
//	}
}