package ie.gmit.sw.tests;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.*;

import ie.gmit.sw.cipher.*;

public class TestVigenereBreakerImpl {
	private static VigenereBreakerImpl vbi = null;
	
	@BeforeClass
	public static void setupBeforeClass() throws Exception{
		System.out.println("initializing new instance of VigenereBreakerImpl");
		vbi = new VigenereBreakerImpl();
	}
	
	@Before
	public void setup() throws Exception{
//		System.out.println("re-initializing new instance of VigenereBreakerImpl");
//		vbi = new VigenereBreakerImpl();
	}
	
	@Test
	public void testEncrypt() throws RemoteException{
		System.out.println("Testing encrypt method...");
		
		String cipherText = "JNORDDBENCAWUINQMZWTVAIVWINV";
		String plainText = "ANTIDISESTABLISHMENTARIANISM";
		String keyWord = "jav";
		
		System.out.println("Encrypting word: " + plainText + " with the key: " + keyWord);
		String result = vbi.encrypt(plainText, keyWord);
		
		assertEquals(result, cipherText);
	}
	
	@Test
	public void testDecrypt() throws RemoteException{
		System.out.println("Testing decrypt method...");
		
		String cipherText = "JNORDDBENCAWUINQMZWTVAIVWINV";
		String plainText = "ANTIDISESTABLISHMENTARIANISM";
		String keyWord = "jav";
		
		System.out.println("Decrypting word: " + cipherText + " with a key of max length: " + keyWord.length());
		String result = vbi.decrypt(cipherText, keyWord);
		
		assertEquals(result, plainText);
	}
	
	@Test
	public void testCrack() throws RemoteException{
		System.out.println("Testing decrypt method...");
		
		String cipherText = "JNORDDBENCAWUINQMZWTVAIVWINV";
		String plainText = "ANTIDISESTABLISHMENTARIANISM";
		String keyWord = "jav";
		
		System.out.println("Decrypting word: " + cipherText + " with a key of max length: " + keyWord.length());
		String result[] = vbi.crack(cipherText, keyWord.length());
		
		assertEquals(result[0], plainText);
	}
	
	@Test
	public void testTwoWayEncrypt() throws RemoteException{
		System.out.println("Testing TwoWayEncryption...");
		
		String plainText = "ANTIDISESTABLISHMENTARIANISM";
		String keyWord = "jav";
		
		String result1 = vbi.encrypt(plainText, keyWord);
		String result2 = vbi.decrypt(result1, keyWord);
		
		System.out.println("plaintext: " + plainText + "\nresult1: " + result1 + "\nresult2: " + result2);
		
		assertEquals(plainText, result2);
	}
	
	@Test
	public void testTwoWayCrack() throws RemoteException{
//		String plainText = "’Twas brillig, and the slithy toves Did gyre and gimble in the wabe: All mimsy were the borogoves, And the mome raths outgrabe. “Beware the Jabberwock, my son! The jaws that bite, the claws that catch! Beware the Jubjub bird, and shun The frumious Bandersnatch!” He took his vorpal sword in hand; Long time the manxome foe he sought— So rested he by the Tumtum tree And stood awhile in thought. And, as in uffish thought he stood, The Jabberwock, with eyes of flame, Came whiffling through the tulgey wood, And burbled as it came! One, two! One, two! And through and through The vorpal blade went snicker-snack! He left it dead, and with its head He went galumphing back. “And hast thou slain the Jabberwock? Come to my arms, my beamish boy! O frabjous day! Callooh! Callay!” He chortled in his joy. ’Twas brillig, and the slithy toves Did gyre and gimble in the wabe: All mimsy were the borogoves, And the mome raths outgrabe.";
//		String testWord1 = "In order to encipher a message using the Vigenère autokey method, the sender and receiver must first agree on a priming key. The priming key is a single letter that will be added to the beginning of the message to form the key. The sender will encrypt the message by writing the plaintext on one line and writing the key on the line beneath it. The sender will use the plaintext and key letters to select a row and a column in the Vigenère square. The selected row is the row in which the plaintext letter is in the first column and the selected column is the column in which the key letter is in the first row. A ciphertext letter will be the letter that appears in the Vigenère square at the position corresponds to the selected row and column. In the following example, to find the ciphertext letter, first locate the row in the Vigenère square that corresponds to plaintext letter T. Next locate the column that corresponds to the key letter L. The letter at which they intersect is the ciphertext letter, in this case E. Continue to do this for each pair of letters to form the compete ciphertext.";
//		String testKey1 = "Cats";
		
		System.out.println("Testing TwoWayCracking...");
		
		String plainText = "ANTIDISESTABLISHMENTARIANISM";
		String keyWord = "jav";
		
		String result1 = vbi.encrypt(plainText, keyWord);
		String result2[] = vbi.crack(result1, keyWord.length());
		
		System.out.println("plaintext: " + plainText + "\nresult1: " + result1 + "\nresult2: " + result2);
		
		assertEquals(plainText, result2[0]);
	}
	
	@After
	public void tearDown() throws Exception{
//		System.out.println("Setting instance of VigenereBreakerImpl to null");
//		vbi = null;
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Setting instance of VigenereBreakerImpl to null");
		vbi = null;
	}
}
