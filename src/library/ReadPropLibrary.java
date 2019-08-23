
package library;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author Van Hai
 */
public class ReadPropLibrary {
    private Properties prop;
    
    public Properties readProp(String fileName){
        prop = new Properties();
        File file = new File(fileName);
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            prop.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return prop;
    }
}
