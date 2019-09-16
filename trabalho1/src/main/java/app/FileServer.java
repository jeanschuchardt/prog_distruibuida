package app;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer extends Thread {

	private ServerSocket ss;
	private Socket socket;

	public FileServer(int port) {
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				Socket clientSock = ss.accept();
				saveFile(clientSock);
				getRequest();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void saveFile(Socket clientSock) throws IOException {

		DataInputStream dis = new DataInputStream(clientSock.getInputStream());
		FileOutputStream fos = new FileOutputStream("2asdasd.hda");
		byte[] buffer = new byte[4096];

		int filesize = 15123; // Send file size in separate msg
		int read = 0;
		int totalRead = 0;
		int remaining = filesize;
		while ((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
			totalRead += read;
			remaining -= read;
			System.out.println("read " + totalRead + " bytes.");
			fos.write(buffer, 0, read);
		}

		fos.close();
		dis.close();	
		
	}
	
	

	public void getRequest() throws IOException {
		Thread t1 = new Thread(new Runnable() {
			String[] split ;
		    public void run() {
		    	while (true) {
					try {
						socket = ss.accept();
						InputStream is = socket.getInputStream();
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(isr);
						String number = br.readLine();
						split = number.split(";");
						
						System.out.println(split[0]);
						System.out.println(split[1]);
						System.out.println(split[2]);
						 
						
						is.close();
						isr.close();
						br.close();
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					String path = "C:\\puc\\prog_distruibuida\\trabalho1\\files\\create_update.sql";
					FileClient fc = new FileClient(split[0], 1988, path);
					 
				}
		    }
		});  
		t1.start();
		
	}

}