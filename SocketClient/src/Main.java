
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class Main {
  public static void main(String[] argv) throws Exception {
    Socket sock = new Socket("127.0.0.1", 2000);
    byte[] mybytearray = new byte[1024];
    InputStream is = sock.getInputStream();
    FileOutputStream fos = new FileOutputStream("s.pdf");
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    int bytesRead = is.read(mybytearray, 0, mybytearray.length);
    bos.write(mybytearray, 0, bytesRead);
    bos.close();
    sock.close();
  }
}