/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Álef Vieira
 */
public class CriarConexServer {
    private Socket s;
    private PrintStream ps;
    private static Properties config = new Properties();
    private static String arquivo = "config.ini";
   
//    configuração dos atributos do arquivo de configuração
    
    private String arquivoTXT;
    private String host;
    private String porta;
    
    
    
    public CriarConexServer(){
         try {
//              faz a configuração e leitura do arquivo que pega o host e porta do servidor

//            config.load(new FileInputStream(arquivo));
//              esse metodo vai tratar os caracteres especiais que está vindo do arquivo de configuração
            config.load(new InputStreamReader(  new FileInputStream(arquivo), Charset.forName("UTF-8")));

            
            this.host = config.getProperty("host");
            this.porta = config.getProperty("porta");
            this.arquivoTXT = config.getProperty("arquivotxt");
            
//            this.arquivoTXT = this.arquivoTXT
//                    .replace("Ã", "Á");
  
            
//            JOptionPane.showMessageDialog(null, porta+"\n"+ host+"\n"+arquivoTXT);
            System.out.println(porta+"\n"+ host+"\n"+arquivoTXT);
            
            this.s = new Socket(host, Integer.parseInt(porta));
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null , "erro: "+e+" não foi possível conectar no servidor");
            System.exit(0);
        }        
        
    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }

    public PrintStream getPs() {
        return ps;
    }

    public void setPs(PrintStream ps) {
        this.ps = ps;
    }

    public static Properties getConfig() {
        return config;
    }

    public static void setConfig(Properties config) {
        CriarConexServer.config = config;
    }

    public static String getArquivo() {
        return arquivo;
    }

    public static void setArquivo(String arquivo) {
        CriarConexServer.arquivo = arquivo;
    }

    public String getArquivoTXT() {
        return arquivoTXT;
    }

    public void setArquivoTXT(String arquivoTXT) {
        this.arquivoTXT = arquivoTXT;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }
    
    
}
