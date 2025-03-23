package principal;

/**
 *
 * @author gelvazio camargo
 */
public class UpdateEmSerie {
        
    public static void main(String[] args) {
        
        String senha = "123456";
        String hashGerado = BCrypt.hashpw(senha, BCrypt.gensalt());
        String update = "update usuario set ususenha = '" + hashGerado + "'";
        
        Conexao.executaQueryInsertUpdate(update);
    }
}
