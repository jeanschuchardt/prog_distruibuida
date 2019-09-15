package T1.T1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.bind.DatatypeConverter;

public class Peer {
	private HashMap<String, String> mapFiles = new HashMap<String, String>();

	Peer() throws Exception {
		// clienteUDP();
		// serverUDP();

	}

	public void getMyIP() throws IOException {
		InetAddress inetAddress = InetAddress.getLocalHost();
		System.out.println("IP Address:- " + inetAddress.getHostAddress());

		System.out.println("Host Name:- " + inetAddress.getHostName());

	}

	public void requestFile(String ip, String fileName) {
		// aqui deve ser a implementação do socket com o peer detentor do recurso

	}

	public HashMap<String, String> contentList() throws NoSuchAlgorithmException, IOException {
		

		String path = "C:\\Users\\jean_burda\\Desktop\\Test";
//		Scanner sc = new Scanner(System.in);
//		System.out.println("informe uma pasta do sistema");
//		path = sc.nextLine();
		File f = null;
		try {
			f = new File(path);
			File[] files = f.listFiles();

			calculateHash( path, files);

		} catch (Exception e) {
			System.out.println("Error contentList");
			System.out.println(e);
		}
		return mapFiles;

		///
	}

	private HashMap<String, String> calculateHash(String path, File[] files)
			throws NoSuchAlgorithmException, IOException {
		for (int i = 0; i < files.length; i++) {
			String fileName = files[i].getName();
			String fileFullPath = path + "\\" + files[i].getName();

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(Files.readAllBytes(Paths.get(fileFullPath)));
			byte[] digest = md.digest();
			String myChecksum = DatatypeConverter.printHexBinary(digest).toUpperCase();

			System.out.println();
			System.out.println(fileName);
			System.out.println(myChecksum);

			mapFiles.put(myChecksum, fileName);

		}

		return mapFiles;
	}
	
	public HashMap<String, String> getMapFiles() {
		return mapFiles;
	}

	public void clienteUDP() throws IOException {

		DatagramSocket serverSocket = new DatagramSocket(9876);
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		while (true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			String sentence = new String(receivePacket.getData());
			System.out.println("RECEIVED: " + sentence);
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String capitalizedSentence = sentence.toUpperCase();
			sendData = capitalizedSentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
	}

	public void serverUDP() throws Exception {
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		String sentence = inFromUser.readLine();
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		String modifiedSentence = new String(receivePacket.getData());
		System.out.println("FROM SERVER:" + modifiedSentence);
		clientSocket.close();

	}

	

}
