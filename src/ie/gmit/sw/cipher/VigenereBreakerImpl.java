package ie.gmit.sw.cipher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// This is the Remote Cipher Object
public class VigenereBreakerImpl extends UnicastRemoteObject implements VigenereBreaker {

	private static final long serialVersionUID = 1L;
	private KeyEnumerator breaker;
	private Vigenere vigenere;

	public VigenereBreakerImpl() throws Exception {
		breaker = new KeyEnumerator();
		vigenere = new Vigenere();
		// UnicastRemoteObject.exportObject(this);
	}

	@Override
	public String[] crack(String cipherText, int maxKeyLength) throws RemoteException {
		return breaker.crackCipher(cipherText, maxKeyLength);
	}

	@Override
	public String encrypt(String plainText, String key) throws RemoteException {
		vigenere.setKey(key);
		return vigenere.doCipher(plainText, true);
	}

	@Override
	public String decrypt(String cipherText, String key) throws RemoteException {
		vigenere.setKey(key);
		return vigenere.doCipher(cipherText, false);
	}

}
