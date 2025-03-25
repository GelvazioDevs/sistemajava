package principal;



import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 * @author Gelvazio Camargo
 */
public class Conexao {
    static Connection conexao;

    public static Connection getConexao() {
        return conexao;
    }

    public static void setConexao(Connection conexao) {
        Conexao.conexao = conexao;
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
    public String conectaBanco(){
        String database  = "neondb";
        String host    = "ep-damp-waterfall-a5s23fvv.us-east-2.aws.neon.tech";
        String driver  = "org.postgresql.Driver";
        String url     = "jdbc:postgresql://" + host + "/" + database;
        String usuario = "neondb_owner";
        String senha   = "I1rEsX7tUqCm";

        String lista_dados = "";
        String lista_dados_json = "";

        Statement statement;
        ResultSet resultset;
        try {
            Class.forName(driver);
            
            setConexao(DriverManager.getConnection(url, usuario, senha));

            System.out.println("Conectou com o PostgreSQL!");
            
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
    
    public static void criaConexaoBanco(){
        String database  = "neondb";
        String host    = "ep-damp-waterfall-a5s23fvv.us-east-2.aws.neon.tech";
        String driver  = "org.postgresql.Driver";
        String url     = "jdbc:postgresql://" + host + "/" + database;
        String usuario = "neondb_owner";
        String senha   = "I1rEsX7tUqCm";

        try {
            Class.forName(driver);
            
            setConexao(DriverManager.getConnection(url, usuario, senha));

            System.out.println("Conectou com o PostgreSQL!");
        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver não localizado: " + Driver);
        } catch (SQLException Fonte) {
            JOptionPane.showMessageDialog(null, "Deu erro na conexão com a fonte de dados: " + Fonte);
        }
    }
    
    public static int getProximoCodigo(String tabela, Object objeto){
        int codigo = 0;
        criaConexaoBanco();
        
        String sql = "";
        if(tabela.equals("usuario")){
            sql = "select nextval('usuario_usucodigo_seq') as codigo";
        }
        
        Statement statement;
        ResultSet resultset;
        try {
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);

            resultset = statement.executeQuery(sql);
            if (resultset.next()) {
                if(objeto instanceof Usuario){
                    codigo = resultset.getInt("codigo");
                } else {
                    throw new SQLException("OBJETO NAO ENCONTRADO!\nObjeto:" + objeto.getClass().toString(), sql);
                }
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR SQL: \n" + erro.getMessage(), "ERRO AO EXECUTAR SQL", JOptionPane.ERROR_MESSAGE);            
        }

        return codigo;
    }
    
    public static ArrayList executaQuery(String sql, Object objeto){
        criaConexaoBanco();
        
        ArrayList<Object> lista = new ArrayList();
        
        Statement statement;
        ResultSet resultset;
        try {
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);

            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                if(objeto instanceof Usuario){
                    Usuario usuario = new Usuario();
                    
                    usuario.setCodigo(resultset.getInt("usucodigo"));
                    usuario.setNome(resultset.getString("usunome"));
                    usuario.setLogin(resultset.getString("usulogin"));
                    usuario.setSenha(resultset.getString("ususenha"));
                    usuario.setEmail(resultset.getString("usuemail"));
                    
                    lista.add(usuario);
                } else {
                    throw new SQLException("OBJETO NAO ENCONTRADO!\nObjeto:" + objeto.getClass().toString(), sql);
                }
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR SQL: \n" + erro.getMessage(), "ERRO AO EXECUTAR SQL", JOptionPane.ERROR_MESSAGE);                        
        }

        return lista;
    }
    
    public static boolean executeUpdate(String sql){
        criaConexaoBanco();
        
        Statement statement;
        try {
            statement = conexao.createStatement();
            statement.executeUpdate(sql);            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR SQL: \n" + erro.getMessage(), "ERRO AO EXECUTAR SQL", JOptionPane.ERROR_MESSAGE);                        
            return false;
        }

        return true;
    }
    
}
