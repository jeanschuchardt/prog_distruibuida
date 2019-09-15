package cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.bind.DatatypeConverter;

import app.FileClient;
import app.FileServer;

public class Peer {
	private HashMap<String, String> mapFiles = new HashMap<String, String>();

	Peer() throws Exception {
		// clienteUDP();
		// serverUDP();
		serverUDP() ;

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
		

		String path = "../trabalho1/files";
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
		//String path = "../trabalho1/files";
		String path = "C:\\puc\\prog_distruibuida\\trabalho1\\files\\create_update.sql";
		FileClient fc = new FileClient("localhost", 1988, path );
	}

	public void serverUDP() throws Exception {
		FileServer fs = new FileServer(1988);
		fs.start();
		

	}

	public void conectToPeer(String findByHash) {
	
	}


	

}
