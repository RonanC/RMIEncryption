package ie.gmit.sw.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import ie.gmit.sw.cipher.VigenereBreaker;
import ie.gmit.sw.cipher.VigenereBreakerImpl;

// this is the RMI server

public class CipherService {

	public static void main(String[] args) {
		startService();
	}
	
	private static void startService() {
		try {
			final int PORT = 1099;
			VigenereBreaker vb = new VigenereBreakerImpl();
			
			System.out.println("\nstarting remote service...");
			try {
				LocateRegistry.createRegistry(PORT);
				System.out.println("Java RMI registry created");
				System.out.println("registry on port: " + PORT);
				Naming.bind("cipher-service", vb);
			} catch (Exception e) {
				System.out.println("java RMI registry already exists.");
				Naming.rebind("rmi://localhost:" + PORT + "/cipher-service", vb);
			}

			System.out.println("service started...\n");
			
			// test
//			String testWord2 = "’Twas brillig, and the slithy toves Did gyre and gimble in the wabe: All mimsy were the borogoves, And the mome raths outgrabe. “Beware the Jabberwock, my son! The jaws that bite, the claws that catch! Beware the Jubjub bird, and shun The frumious Bandersnatch!” He took his vorpal sword in hand; Long time the manxome foe he sought— So rested he by the Tumtum tree And stood awhile in thought. And, as in uffish thought he stood, The Jabberwock, with eyes of flame, Came whiffling through the tulgey wood, And burbled as it came! One, two! One, two! And through and through The vorpal blade went snicker-snack! He left it dead, and with its head He went galumphing back. “And hast thou slain the Jabberwock? Come to my arms, my beamish boy! O frabjous day! Callooh! Callay!” He chortled in his joy. ’Twas brillig, and the slithy toves Did gyre and gimble in the wabe: All mimsy were the borogoves, And the mome raths outgrabe.";
//			String testWord = "In order to encipher a message using the Vigenère autokey method, the sender and receiver must first agree on a priming key. The priming key is a single letter that will be added to the beginning of the message to form the key. The sender will encrypt the message by writing the plaintext on one line and writing the key on the line beneath it. The sender will use the plaintext and key letters to select a row and a column in the Vigenère square. The selected row is the row in which the plaintext letter is in the first column and the selected column is the column in which the key letter is in the first row. A ciphertext letter will be the letter that appears in the Vigenère square at the position corresponds to the selected row and column. In the following example, to find the ciphertext letter, first locate the row in the Vigenère square that corresponds to plaintext letter T. Next locate the column that corresponds to the key letter L. The letter at which they intersect is the ciphertext letter, in this case E. Continue to do this for each pair of letters to form the compete ciphertext.";

//			String testEnWord = "JNORDDBENCAWUINQMZWTVAIVWINV";
//			String key = "Jav";
//			String word = "Hello";

			
//			String testWord = "ANTIDISESTABLISHMENTARIANISM";
//			String testKey = "jav";
//			
//			System.out.println("Encrypting word: " + testWord + " with the key: " + testKey);
//			String encryptedWord = vb.encrypt(testWord, testKey);
//			
//			String decryptedWord = vb.decrypt(encryptedWord, testKey.length());
			
			// closes (debug)
//			Runtime.getRuntime().exit(0);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
