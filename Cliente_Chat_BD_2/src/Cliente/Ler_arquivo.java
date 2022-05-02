/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Álef Vieira
 */
public class Ler_arquivo {
    private String conteudo_do_arquivo;

    public void setConteudo_do_arquivo(String arquivoTXT) {
    
      String conteudoArq = "";
      arquivoTXT = arquivoTXT+".txt";
        try {
            
            Scanner scanner = new Scanner(new File(arquivoTXT));
            while (scanner.hasNext()) {
                
                conteudoArq += scanner.nextLine()+"\n";
                
                //System.out.println(scanner.nextLine());
            }
            scanner.close();
//            System.out.println( conteudoArq);
//            JOptionPane.showMessageDialog(null, conteudoArq);
            this.conteudo_do_arquivo = conteudoArq;
            
	} catch (IOException e) {
            e.printStackTrace();
	}
    }
    
    public String getConteudo_do_arquivo(){
        return this.conteudo_do_arquivo;
    }
}
