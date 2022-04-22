package Server;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;


public final class Mensagem {
    
    private Socket s ;
    private ArrayList<PrintStream> clientes;

    
    public Mensagem(Socket s, ArrayList<PrintStream> clientes ){
        this.s = s;
        this.clientes = clientes;
        
        Thread();
        
    } 
     
    public void Thread(){

        Thread t = new Thread( new Runnable(){
            
            @Override
            public void run(){
                String message;
                try {
                    InputStreamReader irs = new InputStreamReader(s.getInputStream());
                    BufferedReader br = new BufferedReader(irs);
                    
                    
                    while((message = br.readLine()) != null){
                        System.out.println(message);
                        enviarMensagem(message);
                        
//                        vai escrever no arquivo
                        escritaArquivo(message);
                    }

                } catch (Exception e) {
                    System.out.println("Erro: " +e);
                }
            }
        });
        t.start();
         
    }
    public void  enviarMensagem(String message){
            for(int a = 0; a < this.clientes.size(); a++){


                this.clientes.get(a).println(message);
                this.clientes.get(a).flush();

            }
        
    }
    
    public void escritaArquivo(String texto){    
        //GRAVAR ARQUIVO
        try {
            FileWriter arq = new FileWriter("conversaChat.txt",true);
            PrintWriter gravarArq = new PrintWriter(arq);
            //gravarArq.println(Texto);
            gravarArq.println(texto);
            gravarArq.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

    }
    
    
    
}
