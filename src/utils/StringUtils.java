
package utils;

import java.io.File;

/**
 *
 * @author Van Hai
 */
public class StringUtils {
    public static final String EMPTY_STRING="";
    public static final String BREAK_LINE="\n";
    public static final String UPLOAD = "UPLOAD";
    
    public static String getExtFile(File file){
        if(file == null || file.exists()){
            return EMPTY_STRING;
        }
        
        String filename = file.getName();
        return filename.substring(filename.lastIndexOf(".")+1);
    }
}
