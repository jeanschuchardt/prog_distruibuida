package cliente;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.DatatypeConverter;

public class Peer {
	

	private HashMap<String, String> mapFiles =  new HashMap<String, String>();
	private ArrayList<String> listFiles = new ArrayList<String>();;
	private socketClass socketClass;
	

	Peer() throws Exception {
//		serverUDP();
		socketClass = new socketClass();
		Thread a  = new Thread(socketClass);
		a.start();
		


	}

	public HashMap<String, String> contentList() throws NoSuchAlgorithmException, IOException {

		// String path = "C:\\Users\\jean_burda\\Desktop\\Test";
		// String path = "C:\\Users\\jeans\\Desktop\\New folder";

		String path = "../trabalho1/files";

//		Scanner sc = new Scanner(System.in);
//		System.out.println("informe uma pasta do sistema");
//		path = sc.nextLine();
		HashMap<String, String> calculateHash = new HashMap<String, String>();
		File f = null;
		try {
			f = new File(path);
			File[] files = f.listFiles();

		calculateHash = calculateHash(path, files);

		} catch (Exception e) {
			System.out.println("Error contentList");
			System.out.println(e);
		}
		return calculateHash;

		///
	}

	private HashMap<String, String> calculateHash(String path, File[] files)
			throws Exception {
		
		for (int i = 0; i < files.length; i++) {
			
			String fileName = files[i].getName();
			String fileFullPath = path + "\\" + files[i].getName();
			listFiles.add(fileFullPath);
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(Files.readAllBytes(Paths.get(fileFullPath)));
			byte[] digest = md.digest();
			String myChecksum = DatatypeConverter.printHexBinary(digest).toUpperCase();

			System.out.println();
			System.out.println(fileName);
			System.out.println(myChecksum);

			mapFiles.put(myChecksum, fileName);

		}
		setlist();
		return mapFiles;
	}

	public HashMap<String, String> getMapFiles() {
		return mapFiles;
	}

	// deve mandar a mensagem para o peer que tem o conteudo
	public void clienteUDP(String findByHash) throws IOException {
//		// String path = "../trabalho1/files";
//
//		Socket socket = null;
//		// String path =
//		// "C:\\puc\\prog_distruibuida\\trabalho1\\files\\create_update.sql";
//		// FileClient fc = new FileClient("localhost", 1988, path );
//
//		String[] split = findByHash.split(";");
//		try {
//			String host = split[0];
//			System.out.println("mensagem para " + host);
//			int port = 1988;
//			InetAddress address = InetAddress.getByName(host);
//			socket = new Socket(address, port);
//
//			// Send the message to the server
//			OutputStream os = socket.getOutputStream();
//			OutputStreamWriter osw = new OutputStreamWriter(os);
//			BufferedWriter bw = new BufferedWriter(osw);
//
//			String number = findByHash;
//
//			String sendMessage = number + "\n";
//			bw.write(sendMessage);
//			bw.flush();
//			System.out.println("Message sent to the server : " + sendMessage);
//
//		} catch (Exception exception) {
//			exception.printStackTrace();
//		} finally {
//			// Closing the socket
//			try {
//				socket.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}

	}

	public void serverUDP() throws Exception {
//		FileServer fs = new FileServer(1988);
//		fs.start();

	}

	public ArrayList<String> listFiles() throws Exception {
		setlist();
		return listFiles;

	}
	public void getResource(String ip, String nome) throws Exception {
		setlist();
		socketClass.cliente(ip, nome);
	}
	
	public void setlist() throws Exception {
		socketClass.listFiles(listFiles);
	}

	

}
