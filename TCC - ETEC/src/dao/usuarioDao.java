/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.dbConnection;
import controller.loginController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.usuarioModel;
import view.Home;

/**
 *
 * @author Aluno
 */
public class usuarioDao {

    private Connection conexao;
    
    loginController login = new loginController();
            
    Home home = new Home();
    
    public usuarioDao() throws SQLException, ClassNotFoundException {
        this.conexao = dbConnection.abrirConexaoComBancoDeDados();
    }

    public void cadastrarUsuario(usuarioModel usuariomodel) throws SQLException {
        if (usuariomodel.getIdUsuario() == 0) {
            inserirusuario(usuariomodel);
        } else {
            atualizarusuario(usuariomodel);
        }
    }

    private void inserirusuario(usuarioModel usuariomodel) throws SQLException {
        String sql = "insert into usuario values(default, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuariomodel.getNomeUsuario());
            stmt.setString(2, usuariomodel.getCargoUsuario());
            stmt.setString(3, usuariomodel.getEmailUsuario());
            stmt.setString(4, usuariomodel.getSenhaUsuario());
            stmt.execute();
        } catch (SQLException ex) {
            throw new SQLException(ex + "Erro ao inserir usuário");
        } finally {
            dbConnection.encerrarConexao(conexao, stmt, rs);
        }
    }

    private void atualizarusuario(usuarioModel usuariomodel) throws SQLException {
        String sql = "update usuario set nome = ?, cargo = ?, email = ?, senha = ? where idUsuario = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuariomodel.getNomeUsuario());
            stmt.setString(2, usuariomodel.getCargoUsuario());
            stmt.setString(3, usuariomodel.getEmailUsuario());
            stmt.setString(4, usuariomodel.getSenhaUsuario());
            stmt.setInt(5, usuariomodel.getIdUsuario());
            stmt.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar usuario");
        } finally {
            dbConnection.encerrarConexao(conexao, stmt, rs);
        }

    }

    public void gravarUsuario(usuarioModel usuariomodel) throws SQLException {
        if (usuariomodel.getIdUsuario() == 0) {
            inserirusuario(usuariomodel);
        } else {
            atualizarusuario(usuariomodel);
        }
    }
    
    public boolean validarUsuario(String usuario, String senha) throws ClassNotFoundException, SQLException{
        Connection con = dbConnection.abrirConexaoComBancoDeDados();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean validar = false;
        
        try{
            stmt = con.prepareStatement("SELECT * FROM usuario where nome = ? and senha = ?");
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                validar = true;
            }
        } catch(SQLException ex){
            Logger.getLogger(usuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dbConnection.encerrarConexao(con, stmt, rs);
        }
        return validar;
    }
    
//    public boolean validarUsuarioTeste(String usuario, String senha) throws ClassNotFoundException, SQLException {
//        String sql = "SELECT * FROM usuario where nomeUsuario = ? and senhaUsuario = ?";
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        usuarioModel usuarioModel = new usuarioModel();
//        Boolean validacao = false;
//        
//        try {
//            stmt = conexao.prepareStatement(sql);
//            stmt.setString(1, usuario);
//            stmt.setString(2, senha);
//            rs = stmt.executeQuery();
//            
//            if (usuario.equals(rs.getString("nomeUsuario")) && senha.equals(rs.getString("senhaUsuario"))) {
//                validacao = true;
//            }
//            
//        } catch (SQLException ex) {
//            throw new SQLException("Erro ao fazer login! Nome: " + rs.getString("nomeUsuario") + " e Senha: " + rs.getString("senhaUsuario"));
//        } finally {
//            dbConnection.encerrarConexao(conexao, stmt, rs);
//        }
//        return validacao;
//    }
    public void Login(String nomeUsuario, String senhaUsuario) throws SQLException, ClassNotFoundException{
        Connection conexao = dbConnection.abrirConexaoComBancoDeDados();
        String sql = "select nomeUsuario, senhaUsuario from usuario where nomeUsuario = '"+nomeUsuario+"' and senhaUsuario = '"+senhaUsuario+"'";
        PreparedStatement statment = conexao.prepareStatement(sql);
        ResultSet rs = statment.executeQuery();
        
        if(rs.next()){
            JOptionPane.showMessageDialog(null, "Bem-Vindo ao Sistema!");
            login.abrirHome(home);
        } else {
            JOptionPane.showMessageDialog(null, "Nome ou Sennha Incorretos!");
        }
        conexao.close();
    }
}
