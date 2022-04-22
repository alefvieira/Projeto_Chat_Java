
package Cliente;


import java.net.*;
//import javax.swing.JOptionPane;


public class Server {
       
    public void  main(String[] args){
        try {
            ServerSocket server = new ServerSocket(9000);
            Socket socket;
            
            while(true){
                socket = server.accept();
                System.out.println("Conectou com sucesso");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, e);
        }
    }
    
        
    
    
}
