package imageresizerforandroid;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author fonter
 */
public class ImageContainer {
    private final BufferedImage image;
    private ImageIcon cache;
    private final File file;

    public ImageContainer (BufferedImage image, File file) {
        this.image = image;
        this.file = file;
    }
    
    public String getNormalizeName () {
        String name = file.getName();
        
        int pos = name.lastIndexOf(".");
        if (pos > 0) {
            return name.substring(0, pos);
        }
        
        return name;
    }

    public ImageIcon getCache () {
        return cache;
    }

    public File getFile () {
        return file;
    }

    public BufferedImage getImage () {
        return image;
    }

    public void setCache (ImageIcon cache) {
        this.cache = cache;
    }
    
    public boolean isCacheCreate () {
        return cache != null;
    }
}
