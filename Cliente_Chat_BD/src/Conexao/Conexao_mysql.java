
package Conexao;



import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import javax.swing.JOptionPane;

public class Conexao_mysql {
    
    private static Properties config = new Properties();
    private static String arquivo = "config.ini";
    private String host;
    private String banco_Dados;
    
    
    Connection conect = null;
    
    public Connection conectar(){
        try {
//            criar usuario no banco de dados
//            CREATE USER 'monty'@'localhost' IDENTIFIED BY 'some_pass';
//GRANT ALL PRIVILEGES ON *.* TO 'monty'@'localhost'
//WITH GRANT OPTION;
// CREATE USER 'monty'@'%' IDENTIFIED BY 'some_pass';
//GRANT ALL PRIVILEGES ON *.* TO 'monty'@'%'
//WITH GRANT OPTION;

            config.load(new InputStreamReader(  new FileInputStream(arquivo), Charset.forName("UTF-8")));
            
            this.host = config.getProperty("host");
            this.banco_Dados = config.getProperty("nomeServer");
            
            conect = DriverManager.getConnection(
                    "jdbc:mysql://"+host+"/"+banco_Dados, //linha de conexao
                    "monty", //usuario do mysql
                    "some_pass" // senha de conexao
            );


            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro na conexão" +e);
            System.out.println("erro: "+e);
        }
        return conect;
    }

}

