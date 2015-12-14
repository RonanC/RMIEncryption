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

	private static boolean startService() {
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

			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

}
