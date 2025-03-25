package view;

import java.sql.SQLException;
import java.text.ParseException;
/**
 *
 * @author paulojp
 */
public class Biblioteca {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws SQLException, ParseException {

        JFPrincipal principal = new JFPrincipal();
        principal.setVisible(true);
        
    }
    
}
