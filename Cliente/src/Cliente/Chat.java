package Cliente;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JOptionPane;





public class Chat extends javax.swing.JFrame {
    private Socket s;
    private String nome;
    private PrintStream ps;
    private static Properties config = new Properties();
    private static String arquivo = "config.ini";
   
//    configuração dos atributos do arquivo de configuração
    
    private String arquivoTXT;
    private String host;
    private String porta;
    
    /**
     * Creates new form Chat
     */
    public Chat() {
        initComponents();

        try {
//            System.out.println("Conectou");
//faz a configuração e leitura do arquivo que pega o host e porta do servidor

//            config.load(new FileInputStream(arquivo));
//esse metodo vai tratar os caracteres especiais que está vindo do arquivo de configuração
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
        
        Thread();
        
        
        
    }
    
    public void receberNome(String nome){
//        vai verificar se foi digitado um nome
        if(!nome.equals("")){
            this.nome = nome;
        }
        else{
            this.nome = "Anônimo";
        }
        // vai setar o this do nome do jframe
        JFrame esteframe = this;
        esteframe.setTitle(this.nome);
        
//       vai setar as mensagens que estiverem dentro do arquivo txt ao abrir o chat
        Ler_arquivo lerArq = new Ler_arquivo();
        
        lerArq.setConteudo_do_arquivo(arquivoTXT);
        
        String cntTXT = lerArq.getConteudo_do_arquivo();
        mensagemRecebida.setText(cntTXT);
        
    }
    
    public void Thread(){
        Thread t = new Thread( new Runnable() {
            String message;
            
            @Override
            public void run(){
                try {
                    InputStreamReader irs = new InputStreamReader(s.getInputStream());
                    BufferedReader br = new BufferedReader(irs);
                    
                    System.out.println( br.readLine());

                    while((message = br.readLine())!= null){
                        
                        mensagemRecebida.setText(mensagemRecebida.getText() +  message + "\n");
                    }

                } catch (Exception e) {
//                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro na conexão  com o servidor", "", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                    
                }
            }
        });
        t.start(); 
     
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mensagemRecebida = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        msgEnviar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mensagemRecebida.setEditable(false);
        mensagemRecebida.setColumns(20);
        mensagemRecebida.setRows(5);
        jScrollPane1.setViewportView(mensagemRecebida);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        msgEnviar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        msgEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msgEnviarActionPerformed(evt);
            }
        });
        msgEnviar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                msgEnviarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(msgEnviar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                    .addComponent(msgEnviar))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(607, 449));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String verif = verif_CampoLimpo();
        if(verif.equals("false")){
            
            enviarMensagem();
            
        }    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void msgEnviarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_msgEnviarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String verif = verif_CampoLimpo();
            //aqui vai o q voce deseja fazer quando o usuario clicar enter naquele jtextfield
            if(verif.equals("false")){
                enviarMensagem() ;
               
            }
        }
    }//GEN-LAST:event_msgEnviarKeyPressed

    private void msgEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msgEnviarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_msgEnviarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Chat().setVisible(true);
            }
        });
    }
   
    public void enviarMensagem(){
        String mensagem;
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());    
        try{

                this.ps = new PrintStream(this.s.getOutputStream());
            
                mensagem = "  "+timeStamp+" -- "+ this.nome + "\n   "+ msgEnviar.getText();

                this.ps.println();
                this.ps.flush();            

                this.ps.println(mensagem);
                this.ps.flush();            

                msgEnviar.setText(null);
           
        } catch (IOException e) {
           JOptionPane.showMessageDialog(null ,"Não conseguiu enviar a mensagem","", JOptionPane.ERROR_MESSAGE);
       }
    }
    public String verif_CampoLimpo(){
        String result;
        
        if(msgEnviar.getText().equals("")) {
            result = "true";
            msgEnviar.setText(null);
        } 
        
        else result = "false";
        
        return result;
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea mensagemRecebida;
    private javax.swing.JTextField msgEnviar;
    // End of variables declaration//GEN-END:variables
}