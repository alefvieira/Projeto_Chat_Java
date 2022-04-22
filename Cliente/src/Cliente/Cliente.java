package Cliente;

import javax.swing.JOptionPane;



public class Cliente {
    
            
    public static void main(String[] args)
    {
        
        String nome = joptString();
        
        Chat chat = new Chat();
        chat.receberNome(nome);
        chat.setVisible(true);
          
        
    }
    public static String joptString(){
        String nome = JOptionPane.showInputDialog(null, "Digite seu Nome: ", "", JOptionPane.PLAIN_MESSAGE);
        return nome;
        
    }
}
