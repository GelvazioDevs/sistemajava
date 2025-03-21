package principal.conexao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JOptionPane;
import model.Pessoa;

/**
 * @author Gelvazio Camargo
 */
public class Conexao {

    public static void main(String args[]) {
        conectaBanco();
    }

    // banco postgresql online free
    // opção 01
    // https://supabase.com/
    // opção 02
    // https://neon.tech/
    // PGHOST='ep-damp-waterfall-a5s23fvv.us-east-2.aws.neon.tech'
    // PGDATABASE='neondb'
    // PGUSER='neondb_owner'
    // PGPASSWORD='I1rEsX7tUqCm'
    public static String conectaBanco(){
        String database  = "neondb";
        String host    = "ep-damp-waterfall-a5s23fvv.us-east-2.aws.neon.tech";
        String driver  = "org.postgresql.Driver";
        String url     = "jdbc:postgresql://" + host + "/" + database;
        String usuario = "neondb_owner";
        String senha   = "I1rEsX7tUqCm";

        String lista_dados = "";
        String lista_dados_json = "";

        Connection conexao;
        Statement statement;
        ResultSet resultset;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);

            JOptionPane.showMessageDialog(null, "Conectou com o PostgreSQL!");

            System.out.println("Conectou com o PostgreSQL!");

            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);

            resultset = statement.executeQuery("select * from usuario limit 10");

            while (resultset.next()) {
                int usucodigo = resultset.getInt("usucodigo");
                String usuemail = resultset.getString("usucodigo");
                String ususenha = resultset.getString("ususenha");

                lista_dados = lista_dados + "Codigo .: " + resultset.getInt("usucodigo");
                lista_dados = lista_dados + "\nE-mail .: " + resultset.getString("usuemail") + "\n";
                lista_dados = lista_dados + "\nSenha .: " + resultset.getString("ususenha") + "\n";

                lista_dados_json = lista_dados_json + "{" +
                        "\"usucodigo\":\"" + usucodigo +"\"," +
                        "\"usuemail\":\"" + usuemail +"\"," +
                        "\"ususenha\":\"" + ususenha +"\"" +
                        "}";
            }

            //JOptionPane.showMessageDialog(null, lista_dados);

            System.out.println(lista_dados);

        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver não localizado: " + Driver);
        } catch (SQLException Fonte) {
            JOptionPane.showMessageDialog(null, "Deu erro na conexão com a fonte de dados: " + Fonte);
        }

        return lista_dados_json;
    }
    
}
