package imageselect;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author fonter
 */
public class ImageSelect {
    private static JFileChooser imageChooser = null;
    private static void setupFileChooser() {
        imageChooser = new JFileChooser();
        
        ImagePreviewPanel preview = new ImagePreviewPanel();
        imageChooser.setAccessory(preview);
        imageChooser.addPropertyChangeListener(preview);
        
        imageChooser.setMultiSelectionEnabled(true);

        FileFilter ff = new ImageFilter();
        imageChooser.addChoosableFileFilter(ff);
        imageChooser.setFileFilter(ff);
        
    }
    
    public static File[] showSelectImageDialog() {
        if (imageChooser == null) {
            setupFileChooser();
        }
        
        int result = imageChooser.showOpenDialog(null);
        if(result != JFileChooser.APPROVE_OPTION){
            return null;
        }
        
        return imageChooser.getSelectedFiles();
    }
}
