/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexao.Conexao_mysql;
import Model.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Álef Vieira
 */
public class UserDAO {
    private Conexao_mysql conexao; 
    private Connection conn;
    
    //criar o construtor, o construtor é executado automaticamente sempre que um novo objeto é criado
    public UserDAO(){
        this.conexao = new Conexao_mysql();
        this.conn = this.conexao.conectar();
    }
    
    
    public void insertUser(UserModel u){
        
        
        
        String sql = "INSERT INTO usuarios(nickname, usuario, senha) values (?, ? ,MD5(?))";
        
        try {
            
            boolean verificaUsario = verificaUserCadastrado(u.getUser(), u.getNome());
            
            if(verificaUsario == true){
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setString(1, u.getNome());
                stmt.setString(2, u.getUser());
                stmt.setString(3, u.getSenha());

                stmt.execute();
                JOptionPane.showMessageDialog(null,"Inserido com sucesso" );
            }
            else{
                JOptionPane.showMessageDialog(null,"Esse usuário ou nickmane não é permitido");
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro ao inserir usuario "+ e.getMessage());
        }
    }
    
//    esse metodo vai verificar se o usuário já foi cadastrado caso já tenha sido cadastrado vai retornar um valor booleano true
    public boolean verificaUserCadastrado(String u, String nick){
        String resultado = null;
        try {
            String sqlConfere = "SELECT usuario FROM usuarios WHERE usuario = ? OR nickname = ?";
            
            PreparedStatement stmt = this.conn.prepareStatement(sqlConfere);
            stmt.setString(1, u);
            stmt.setString(2, nick);
            
            ResultSet rs = stmt.executeQuery();
            rs.first();
            
            resultado = rs.getString("usuario");
            
            if( !resultado.equals("")){ // false é quando o resultado estiver vazio
                return false; 
                
            }else{
                return true;
            }
            
        }
            
        catch (Exception e) {
//            JOptionPane.showMessageDialog(null,"Valor não encontrado");
            System.out.println("verificaUserCadastrado "+e);
            
            if(resultado == null){
                return true;
            }
            
        }
        return false;
    }
    
//    autenticação do usuário na tela de login
   public String autenticacaoUser(UserModel u){
       
       String sql = "SELECT nickname, usuario, senha FROM usuarios WHERE usuario = ? AND senha = MD5(?)";
       
       try {
           PreparedStatement stmt = this.conn.prepareStatement(sql);
           
            //vai verificar se possui o usuario
            stmt.setString(1, u.getUser());
            stmt.setString(2, u.getSenha());
            
            ResultSet rs = stmt.executeQuery();
            rs.first();
            
            String usu = rs.getString("nickname");
            
            if(!usu.equals("")){
                return usu;
            }
           
       } catch (Exception e) {
           System.out.println("Erro de autenticação do usuario: "+ e);
       }
       return "";
   }
   
   
   public ResultSet todosOsContatos(String nome){
       
       String sql = "SELECT nickname FROM usuarios WHERE nickname != ?";
       
       try {
           try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nome);
            
            
            ResultSet rs = stmt.executeQuery();

            return rs;
            
            
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null,"Valor não encontrado");;
            System.out.println(e);
            return null;
            
        }
       } catch (Exception e) {
           System.out.println("Erro na leitura dos contatos");
       }
        return null;
   }
}
