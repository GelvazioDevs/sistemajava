package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author paulojp
 */
public class CriaConexao {
    
    public static Connection getConexaoMySQL() throws SQLException {        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Conectando ao banco de dados.");
            return DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "P9544504");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }
    
    public static Connection getConexao() throws SQLException {
        return getConexaoPostgreSQL();
    }
    
    public static Connection getConexaoPostgreSQL() throws SQLException {   
        String database  = "neondb";
        String host    = "ep-damp-waterfall-a5s23fvv.us-east-2.aws.neon.tech";
        String driver  = "org.postgresql.Driver";
        String url     = "jdbc:postgresql://" + host + "/" + database;
        String usuario = "neondb_owner";
        String senha   = "I1rEsX7tUqCm";
        
        Connection conexao = null;
        try {
            Class.forName(driver);
            
            System.out.println("Conectando ao banco de dados.");
            
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver não localizado: " + Driver);
        } catch (SQLException Fonte) {
            JOptionPane.showMessageDialog(null, "Deu erro na conexão com a fonte de dados: " + Fonte);
        }

        return conexao;
    }
    
    public static void main(String[] args) throws SQLException {
        getConexao();
    }
}
