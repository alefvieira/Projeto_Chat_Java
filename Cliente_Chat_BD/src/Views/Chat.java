package Views;

import Cliente.CriarConexServer;
import Cliente.Ler_arquivo;
import DAO.UserDAO;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;





public class Chat extends javax.swing.JFrame {
    private Socket s;
    private String nome;
    private PrintStream ps;
    private static String arquivo = "config.ini";
   
//    configuração dos atributos do arquivo de configuração
    
    private String arquivoTXT;
    private CriarConexServer conServer;
    private String ConversaP;
    /**
     * Creates new form Chat
     */
    public Chat() {
        
        initComponents();
        
        
        criando_conexaoSoket();
        Thread();
        
    }
    
    public void criando_conexaoSoket(){
        conServer = new CriarConexServer();
        s = conServer.getS();
        ps = conServer.getPs();
    }
    
//    Função que recebe o nome da pessoa logada
    public void receberNome(String nome){
//        vai verificar se foi digitado um nome

        this.nome = nome;
        
        // vai setar o this do nome do jframe
        JFrame esteframe = this;
        esteframe.setTitle(this.nome);
        
        atualiza_tabela(); // essa função vai ser executada após todos os componentes serem carregados e o nome setado, desta forma o usuário logado não vai aparecer na tabela com os contatos
 
    }
    private void setConversa(){
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
                       
                    
                    while((message = br.readLine())!= null){
                        
//                        boolean resultado = verificaPessoa(message);
//                        
//                        if(resultado == true){
//  
//                            mensagemRecebida.setText(mensagemRecebida.getText() +  message + "\n");
//                            setConversa();
//                            System.out.println("passou por aqui no if");
//                            
//                        }else{
//                            
//                            mensagemRecebida.setText(mensagemRecebida.getText()+ "\n");
//                            System.out.println("passou por aqui no else");
//                        }
                          setConversa();
                            
                        
                    }

                } catch (IOException e) {
//                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro na conexão  com o servidor", "", JOptionPane.ERROR_MESSAGE);
             
                    
                    System.exit(0);
                    
                }
            }
        });
        t.start(); 
     
    }
    
    private boolean verificaPessoa(String mensagem){
        String[] arr = mensagem.split("-- ");
        System.out.println(arr[1]);
        
        if(conversando.getText().equals(arr[1])){
            
            System.out.println("é valido");
            return true;
        }else{
            return false;
        }
            
        
       
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        contatos = new javax.swing.JTable();
        conversando = new javax.swing.JLabel();

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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Conversas"));

        contatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Contatos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        contatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contatosMouseClicked(evt);
            }
        });
        contatos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                contatosKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(contatos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addContainerGap())
        );

        conversando.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                        .addComponent(msgEnviar))
                    .addComponent(conversando, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(conversando, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(msgEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(654, 500));
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

    private void contatosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contatosKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_contatosKeyPressed

    private void contatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contatosMouseClicked
        int indicelinha = contatos.getSelectedRow();
        String PessoaConversa = contatos.getValueAt(indicelinha, 0).toString(); // converte em numero inteiro
        conversando.setText(PessoaConversa);
        
        
        String[] arrToSort = {this.nome , PessoaConversa}; // vai adicionar duas strings do nome das pessaos na conversa e depois vai ser executado o metodo sort que vai deixar em ordem alfabetica
        Arrays.sort(arrToSort, String.CASE_INSENSITIVE_ORDER);//case insensitive sort
        
        this.ConversaP = arrToSort[0] + arrToSort[1];
        
        arquivoTXT = conServer.getArquivoTXT()+ ConversaP+".txt";
//        arquivoTXT = conServer.getArquivoTXT()+ "conversaChat.txt";
        
        setConversa();
        
    }//GEN-LAST:event_contatosMouseClicked

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
                
                if(ConversaP.equals("")){
                    ConversaP = this.nome;
                }
                
                mensagem = "  "+timeStamp+" -- "+ this.nome + "@@@@ADMIN@ "+ msgEnviar.getText()+"@@@@ADMIN@"+ConversaP;
                
                
//                JOptionPane.showMessageDialog(null, msg_conttConversa[0]);
//                essa parte serve para pular uma linha
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
    public void atualiza_tabela(){
     
        
        DefaultTableModel modeloconsultcliente = (DefaultTableModel) contatos.getModel();
        modeloconsultcliente.setNumRows(0);
        
        contatos.getColumnModel().getColumn(0).setPreferredWidth(100);

        try {
            UserDAO uDAO = new UserDAO();
            
            ResultSet rs = uDAO.todosOsContatos(this.nome);
            
            //vai criar o laçõ de repetição que vai adicionar os valores na tabela
            while (rs.next()) {
                
                modeloconsultcliente.addRow(new Object[]{
                    rs.getString(1),
                });
            }
            
 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Valor não encontrado");
            
        }
        
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable contatos;
    private javax.swing.JLabel conversando;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea mensagemRecebida;
    private javax.swing.JTextField msgEnviar;
    // End of variables declaration//GEN-END:variables
}
