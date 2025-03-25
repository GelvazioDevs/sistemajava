
package dao;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexao {
    public static String url="";
    public static String usr="";
    public static String pass="";
    
    public Conexao(){     
        control.ReadFiles.ler();
    }
    
    public static Connection getConnection(boolean teste){
        try {
            Class.forName("org.postgresql.Driver");
           
            Connection con = DriverManager.getConnection(url, usr, pass);
            if(teste){
                JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
            }
            return con;
        }catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, "O driver não foi encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Problemas com a conexão\n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
 
    public int executeUpdate(String query){
        try{
            Connection con = getConnection(false);
            Statement stm = con.createStatement();
            System.out.println(query);
            int res=stm.executeUpdate(query);
            con.close();
            return res;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Problemas com a conexão\n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
 
    public ResultSet executeQuery(String query){
        try{
            Connection con = getConnection(false);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            //con.close();
            return rs;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Problemas com a conexão\n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    
}
