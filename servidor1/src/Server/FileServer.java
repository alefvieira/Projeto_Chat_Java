package Server;

import java.net.*;
import java.io.*;

public class FileServer {
  public static void main (String [] args ) throws IOException {
    // cria o nosso socket
    ServerSocket servsock = new ServerSocket(9000);
    while (true) {

      Socket sock = servsock.accept();
      System.out.println("Conexão aceita: " + sock);

      // envia o arquivo (transforma em byte array)
      
      
      File myFile = new File ("alefEduardo.txt");
      byte [] mybytearray  = new byte [(int)myFile.length()];
      FileInputStream fis = new FileInputStream(myFile);
      BufferedInputStream bis = new BufferedInputStream(fis);
      bis.read(mybytearray,0,mybytearray.length);
      OutputStream os = sock.getOutputStream();
      System.out.println("Enviando...");
      os.write(mybytearray,0,mybytearray.length);
      os.flush();
      sock.close();
      }
    }
}
