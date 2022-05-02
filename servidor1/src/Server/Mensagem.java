package Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;


public final class Mensagem {
    
    private Socket s ;
    private ArrayList<PrintStream> clientes;
    private Ler_arquivo let_arq = new Ler_arquivo();
    private String nomeDoArquivo;
    
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
                    
                    message = br.readLine();
                    String[] t = message.split("@@@@ADMIN@");
                    
                    while((message = br.readLine() ) != null){
                        
                        t = message.split("@@@@ADMIN@");
                        
                        if(t.length > 0 ){
                            nomeDoArquivo = t[2]+".txt";
                            
//                        vai escrever no arquivo caso o valor do texto não seja nulo, esse texto está no indice 1
                            if(!t[1].equals("")){
                                escritaArquivo(message);
                            }else{
                                escritaArquivoVazio(message);
                            }
                           
                            System.out.println("passou pela escrita no arquivo");
                            enviarMensagem(t);
               
                        }
                        
   
 
                    }

                } catch (Exception e) {
                    System.out.println("Erro: " +e);
                }
            }
        });
        t.start();
         
    }
    
    public void enviarMensagem(String[] msg){

        String conteudoArq = "";      
        String conteudoArq2 = "";      
        
        System.out.println("Nome do arquivo: "+nomeDoArquivo);
        let_arq.setConteudo_do_arquivo(nomeDoArquivo);
        
        String msg_Conteudo = let_arq.getConteudo_do_arquivo();
        
        
//        JOptionPane.showMessageDialog(null, msg_Conteudo);
        
        for (int a = 0; a < clientes.size(); a++) {
            this.clientes.get(a).println(msg[2]+" ---  "+ msg_Conteudo);
            this.clientes.get(a).flush();
        }       
        System.out.println(msg[2]+" ---  "+ msg_Conteudo);
    }
    
//    public void  enviarMensagem(String message){
//            String[] t;
//            
//            t = message.split("@@@@ADMIN@");
//
//            
//            
//            for(int a = 0; a < this.clientes.size(); a++){
//                 
//                this.clientes.get(a).println("\n  "+t[0]+"\n   "+t[1]);
//                this.clientes.get(a).flush();              
//                System.out.println("\n  "+t[0]+"\n  "+t[1]+ t[2]);
//            }
//
//    }
    
    public void escritaArquivo(String texto){    
        //GRAVAR ARQUIVO
        try {
            String[] t = texto.split("@@@@ADMIN@");
            System.out.println(Arrays.toString(t));

            FileWriter arq = new FileWriter(nomeDoArquivo,true);
                
            PrintWriter gravarArq = new PrintWriter(arq);
                
            gravarArq.println("\n  "+t[0]+"\n   "+t[1]);
            gravarArq.close();  
                                
                    
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

    } 
    
//    esse metodo vai criar um arquivo vazio, desta forma quando um usuário selecionar o contato vai aparecer uma caixa de mensagem vazia 
    public void escritaArquivoVazio(String texto){    
        //GRAVAR ARQUIVO
        try {
            String[] t = texto.split("@@@@ADMIN@");
            System.out.println(Arrays.toString(t));

            FileWriter arq = new FileWriter(nomeDoArquivo,true);
                
//            PrintWriter gravarArq = new PrintWriter(arq);
//                
//            gravarArq.println();
//            gravarArq.close();  
                                
                    
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

    } 
}
