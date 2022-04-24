package Server;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


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
                    
                    
                    while((message = br.readLine() ) != null){
                        
                        

                        enviarMensagem(message);
                        

//                        escritaArquivo(separatedStrings[0], message);

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
            String[] t;
            
            t = message.split("@@@@ADMIN@");
//            System.out.println(t[0]+ t[1]+ t[2]);
            
            for(int a = 0; a < this.clientes.size(); a++){

                for(int i = 0 ;i < t.length; i++){
                    if(t.length-1 == i ){
                        this.clientes.get(a).flush();
                    }else{
                        this.clientes.get(a).println(t[i]);
                        
                    }
                    
                }
            }
 
    }
    
    public void escritaArquivo(String texto){    
        //GRAVAR ARQUIVO
        try {
            String[] t = texto.split("@@@@ADMIN@");
            String t2 = "";
            for(int i = 0; i < t.length ; i++){
                
                if(t.length-1 == i ){
                    FileWriter arq = new FileWriter(t[i]+".txt",true);
                    PrintWriter gravarArq = new PrintWriter(arq);

                    gravarArq.println();
                    gravarArq.println(t2);
                    gravarArq.close();
                }else{
                    t2 += t[i]+"\n  "; 
                
                }
            }
            
            

        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

    }
    
    
    
}
