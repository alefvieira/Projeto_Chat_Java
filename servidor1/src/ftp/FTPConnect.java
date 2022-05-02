package ftp;

import org.apache.commons.net.ftp.FTPClient;
import java.net.SocketException;
import java.io.IOException;

public class FTPConnect {

      public static void main (String[] args) throws SocketException, IOException {

     

            FTPClient ftp = new FTPClient();

            ftp.connect( "files.000webhost.com" );

            ftp.login( "tempoprevisto", "tempoprevisto@2022" );

            ftp.changeWorkingDirectory ("/conversas_teste");

            String[] arq = ftp.listNames();

            System.out.println ("Listando arquivos: \n");

            for (String f : arq){

                  System.out.println(f);            

            }

      }

}