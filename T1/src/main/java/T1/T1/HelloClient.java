package T1.T1;

import java.rmi.Naming;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

class HelloClient {
	// Programa cliente para o exemplo "Hello, world!"
	public static void main(String[] argv) {
		Scanner sc = new Scanner(System.in);
		try {
			ServerHostInterface hello = (ServerHostInterface) Naming.lookup("//localhost/Hello");
			//Peer cliente =  new Peer();
			System.out.println("iniciado");
			int key = 1;
			do {
				System.out.println("digite uma opção");
				key = sc.nextInt();
				switch (key) {
				case 1:

					System.out.println(hello.registraPeer());
					break;

				case 2:
					System.out.println(hello.say());
					break;

				case 3:
					// calcula hash dos arquivos de um diretorio
					//HashMap<String, String> contentList = cliente.contentList();
					break;

				case 4:
					System.out.println(hello.registraPeer());
					break;

				case 5:

					break;

				case 7:

					break;

				case 8:

					break;

				case 9:

					break;

				case 0:
					System.out.println("\nfim da aplicação \n\n");
					break;
				default:
					System.out.println("opção errada");
					break;
				}

			} while (key != 0);

		} catch (Exception e) {
			System.out.println("HelloClient failed:");
			e.printStackTrace();
		}
	}

	
	
}
