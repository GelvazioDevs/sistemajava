package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Pessoa;

/**
 *
 * @author Gelvazio Camargo
 */
public class ControllerPessoa {
    
    public ArrayList<Pessoa> getAll(String sql){
        ArrayList dadosArrayList = new ArrayList();
        
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

            while (resultset.next()) {
                int codigo = resultset.getInt("codigo");
                String nome = resultset.getString("nome");
                String endereco = resultset.getString("endereco");
                String cpf = resultset.getString("cpf");
                
                Pessoa pessoa = new Pessoa();
                pessoa.setCodigo(codigo);
                pessoa.setNome(nome);
                pessoa.setEndereco(endereco);
                pessoa.setCpf(cpf);
                
                dadosArrayList.add(pessoa);
            }
        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver não localizado: " + Driver);
        } catch (SQLException Fonte) {
            JOptionPane.showMessageDialog(null, "Deu erro na conexão com a fonte de dados: " + Fonte);
        }

        return dadosArrayList;
    }
}
