package Cliente;

import Views.Tela_Login;
import javax.swing.JOptionPane;



public class Cliente {
    
            
    public static void main(String[] args)
    {
        
//        String nome = joptString();
        
         Tela_Login chat = new Tela_Login();
        chat.setVisible(true);
          
        
    }
    public static String joptString(){
        String nome = JOptionPane.showInputDialog(null, "Digite seu Nome: ", "", JOptionPane.PLAIN_MESSAGE);
        return nome;
        
    }
}
