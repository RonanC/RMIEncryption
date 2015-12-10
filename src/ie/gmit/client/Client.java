package ie.gmit.client;

import java.rmi.Naming;

import ie.gmit.cipher.VigenereBreaker;

public class Client {
	public static void main(String[] args) throws Exception {
		/*
		 * This stuff needs to be in your tomcat app
		 */
		
		VigenereBreaker vb = (VigenereBreaker) Naming.lookup("///cypher-service");
		
		String result = vb.decrypt("sdfsdfr", 7);
	}
}
