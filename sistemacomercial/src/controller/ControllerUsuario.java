package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Gelvazio Camargo
 */
public class ControllerUsuario {
    
    public boolean getUsuario(String sql){
        
        // https://neon.tech/
        String database  = "neondb";
        String host    = "ep-damp-waterfall-a5s23fvv.us-east-2.aws.neon.tech";
        String driver  = "org.postgresql.Driver";
        String url     = "jdbc:postgresql://" + host + "/" + database;
        String usuario = "neondb_owner";
        String senha   = "I1rEsX7tUqCm";

        Connection conexao;
        Statement statement;
        ResultSet resultset;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);

            resultset = statement.executeQuery(sql);

            if (resultset.next()) {
                return true;
            }
        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver não localizado: " + Driver);
        } catch (SQLException Fonte) {
            JOptionPane.showMessageDialog(null, "Deu erro na conexão com a fonte de dados: " + Fonte);
        }

        return false;
    }
}
