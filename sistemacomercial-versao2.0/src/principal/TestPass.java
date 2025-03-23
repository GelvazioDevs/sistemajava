package principal;

import javax.swing.JOptionPane;

/**
 *
 * @author gelva
 */
public class TestPass {
    
    public static void main(String[] args) {
        String senha = "123456";
        String hashGerado = BCrypt.hashpw(senha, BCrypt.gensalt());
        
        String senhaInformada = "123456";        
        if(BCrypt.checkpw(senhaInformada, hashGerado)){
            JOptionPane.showMessageDialog(null, "SENHA VALIDADA COM SUCESSO!");            
        } else {
            JOptionPane.showMessageDialog(null, "SENHA INVÁLIDA!");
        }
    }
}
