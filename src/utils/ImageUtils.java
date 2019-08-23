
package utils;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class ImageUtils {
   public static <T> Image createImage(String path, Class<T> tClass){
        URL url = tClass.getResource(path);
        ImageIcon image = new ImageIcon(url); 
        return image.getImage();
    }
    
    public static <T> Image createImage(Class<T> tClass ,String path, int width, int height ){
        return createImage(path, tClass).getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
    
    public static<T> ImageIcon createImageIcon(Class<T> tClass ,String path, int width, int height ){
        return new ImageIcon(createImage(tClass,path,width, height));
    }
    
    public static Image createImage(String path, int width, int height ){
        ImageIcon image = new ImageIcon(path); 
        return image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
    
    public static ImageIcon createImageIcon(String path, int width, int height ){
        return new ImageIcon(createImage(path, width, height));
    }
}
