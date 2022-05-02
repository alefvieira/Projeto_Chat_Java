
package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;

public class Server {
    
    public static void  main(String[] args){
        
        InterfaceServer interServ = new InterfaceServer();
       
        
        ArrayList<PrintStream> clientes = new ArrayList<>();
        
        try {
            interServ.mensagemJFreme("Servidor Conectado");
            interServ.setVisible(true);
            
            
            ServerSocket server = new ServerSocket(9000);
            Socket socket;
            
                while(true){

                    socket = server.accept();

                    InputStreamReader irs = new InputStreamReader(socket.getInputStream());
                    BufferedReader br = new BufferedReader(irs);



    //                vai guardar o endereço do cliente
                    clientes.add(new PrintStream(socket.getOutputStream()));

                    Mensagem msg = new Mensagem(socket, clientes );

                }
            
            
            
        } catch (IOException e) {
            System.out.println("Erro:  "+e);
        }   
    }
    public void sair(){
        System.exit(0);
    }
}
