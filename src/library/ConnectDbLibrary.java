
package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Van Hai
 */
public class ConnectDbLibrary {
    private Connection conn = null;
    ReadPropLibrary objProp = new ReadPropLibrary();
    Properties prop = objProp.readProp("config.properties");
    
    String url = prop.getProperty("url");
    String user = prop.getProperty("user");
    String password =  prop.getProperty("password");
    
    public Connection getConnectMySQL(){
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Khong the load Driver");
            }
            
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sai thong so ket noi");
        }
        
        return conn;
    }
}


