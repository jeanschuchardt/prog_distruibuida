package cliente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class socketClass implements Runnable {
	public HashMap<String, String> mapFiles;
	public ArrayList<String> listFiles;

	
	
	public void server() throws Exception {
		ServerSocket servsock = new ServerSocket(2016);
				

		System.out.println("ServerSocket awaiting connections...");
        Socket socket = servsock.accept(); // blocking call, this will wait until a connection is attempted on this port.
        System.out.println("Connection from " + socket + "!");

        // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();
        // create a DataInputStream so we can read data from it.
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        // read the message from the socket
        String message = dataInputStream.readUTF();
        System.out.println("The message sent from the socket was: " + message);
		for (String string : listFiles) {
			if(string.contains(message)) {
				
				File myFile = new File(message);
				while (true) {
					Socket sock = servsock.accept();
					byte[] mybytearray = new byte[(int) myFile.length()];
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
					bis.read(mybytearray, 0, mybytearray.length);
					OutputStream os = sock.getOutputStream();
					os.write(mybytearray, 0, mybytearray.length);
					os.flush();
					sock.close();
				}
			}
		}
		
		

	}

	//preciso do ip do server
	public void cliente(String ipRecurso, String n) throws Exception {
		System.out.println(ipRecurso);
		System.out.println(n);
		Socket sock = new Socket(ipRecurso, 2016);
		  // get the output stream from the socket.
        OutputStream outputStream = sock.getOutputStream();
        // create a data output stream from the output stream so we can send data through it
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        System.out.println("Sending string to the ServerSocket");

        // write the message we want to send
        dataOutputStream.writeUTF(n);
        dataOutputStream.flush(); // send the message
        dataOutputStream.close(); // close the output stream when we're done.

		
		byte[] mybytearray = new byte[1024];
		InputStream is = sock.getInputStream();
		FileOutputStream fos = new FileOutputStream(n);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		int bytesRead = is.read(mybytearray, 0, mybytearray.length);
		bos.write(mybytearray, 0, bytesRead);
		bos.close();
		sock.close();
	}

	public void run() {
		try {
			server();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void listFiles(ArrayList<String> listFiles2) {
		listFiles = listFiles2;
		
	}
}