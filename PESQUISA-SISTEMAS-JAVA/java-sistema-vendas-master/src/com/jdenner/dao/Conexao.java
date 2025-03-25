package com.jdenner.dao;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Classe responsável por abrir a conexão com o banco de dados
 *
 * @author Juliano
 */
public class Conexao {

    private final String SERVIDOR = "localhost";
    private final String PORTA = "3306";
    private final String BANCO_DE_DADOS = "dbsistemavenda";
    private final String USUARIO = "root";
    private final String SENHA = "root";
    private final String URL = "jdbc:mysql://" + SERVIDOR + ":" + PORTA + "/" + BANCO_DE_DADOS;

    private Connection conexao;

//    public Conexao() throws ClassNotFoundException, SQLException {
//        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
//            conexao.setAutoCommit(false);
//        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
//            throw new ClassNotFoundException("O driver de conexão não foi adicionado ao projeto.\n" + e.getMessage());
//        } catch (SQLException e) {
//            throw new SQLException("Problemas nos parâmetros de conexão.\n" + e.getMessage());
//        }
//    }

    public Conexao() {
        String database  = "neondb";
        String host    = "ep-damp-waterfall-a5s23fvv.us-east-2.aws.neon.tech";
        String driver  = "org.postgresql.Driver";
        String url     = "jdbc:postgresql://" + host + "/" + database;
        String usuario = "neondb_owner";
        String senha   = "I1rEsX7tUqCm";

        try {
            Class.forName(driver);
            
            conexao = DriverManager.getConnection(url, usuario, senha);
            conexao.setAutoCommit(false);
            
            System.out.println("Conectou com o PostgreSQL!");
        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver não localizado: " + Driver);
        } catch (SQLException Fonte) {
            JOptionPane.showMessageDialog(null, "Deu erro na conexão com a fonte de dados: " + Fonte);
        }
    }

    
    public Connection getConexao() {
        return conexao;
    }

    public void confirmar() throws SQLException {
        try {
            conexao.commit();
        } catch (SQLException e) {
            throw new SQLException("Problemas na instrução SQL.\n" + e.getMessage());
        } finally {
            conexao.close();
        }
    }
}
