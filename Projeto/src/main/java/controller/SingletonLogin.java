/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Cadastro;
import util.ConnectionFactory;

/**
 *
 * @author mathe
 */
public class SingletonLogin {

    private static SingletonLogin instance;

    private SingletonLogin() {
    }

    public static SingletonLogin getInstance() {
        if (instance == null) {
            instance = new SingletonLogin();
        }
        return instance;
    }

    public Cadastro search(String login, String senha) throws SQLException {
        String sql = "SELECT * FROM cadastro";
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Cadastro c = new Cadastro();
        if (login.length() < 3) {
            JOptionPane.showMessageDialog(null, "Login muito curto");
        } else {
            try {
                con = ConnectionFactory.getConnection();
                statement = con.prepareStatement(sql);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {

                    if (resultSet.getString("login").equalsIgnoreCase(login)
                            && resultSet.getString("senha").equals(senha)) {

                        c.setIdCadastro((resultSet.getInt("idCadastro")));
                        c.setLogin((resultSet.getString("login")));
                        c.setNome((resultSet.getString("nome")));
                        c.setSenha((resultSet.getString("senha")));
                        return c;
                    }
                }
            } catch (Exception ex) {
                throw new SQLException("Erro ao procurar o cadastro! "
                        + ex.getMessage(), ex);
            } finally {
                ConnectionFactory.closeConnection(con, statement, resultSet);
            }
        }
        return null;
    }
}
