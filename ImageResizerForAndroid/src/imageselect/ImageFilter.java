package imageselect;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ImageFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        String name = f.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || 
                name.endsWith(".png") || name.endsWith(".gif") || f.isDirectory();
    }

    @Override
    public String getDescription() {
        return "Image";
    }
}
