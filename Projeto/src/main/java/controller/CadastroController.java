/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cadastro;
import util.ConnectionFactory;

/**
 *
 * @author mathe
 */
public class CadastroController {

    public void save(Cadastro cadastro) {
        String sql = "INSERT INTO cadastro (nome,"
                + "login,"
                + "senha) VALUES (?,?,?)";
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(sql);
            statement.setString(1, cadastro.getNome());
            statement.setString(2, cadastro.getLogin());
            statement.setString(3, cadastro.getSenha());
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao efetuar o cadastro! "
                    + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(con, statement);
        }
    }

    

    public CadastroController() {
    }
}
