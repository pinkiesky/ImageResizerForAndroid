package imageselect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;


/**
 *
 * @author http://www.javalobby.org/java/forums/t49462.html
 * @author fonter
 */
 
public class ImagePreviewPanel extends JPanel implements PropertyChangeListener {
    private int width, height;
    private Image image;
    private static final int ACCSIZE = 150;
    private Color bg;
    private FileFilter iFilter = new ImageFilter();
    
    public ImagePreviewPanel() {
        setPreferredSize(new Dimension(ACCSIZE, -1));
        bg = getBackground();
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        String propertyName = e.getPropertyName();
        
        if (propertyName.equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) || 
                propertyName.equals(JFileChooser.DIRECTORY_CHANGED_PROPERTY)) {
            File selection = (File) e.getNewValue();
            
            if (selection != null && iFilter.accept(selection) && selection.isFile()) {
                try {
                    image = ImageIO.read(selection);
                    scaleImage();
                } catch (IOException ex) {
                    image = null;
                }
            } else {
                image = null;
            }
            repaint();
        }
    }
    
    private void scaleImage() {
        if(image == null) {
            image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            return;
        }
        width = image.getWidth(this);
        height = image.getHeight(this);
        double ratio = 1.0d;

        if (width >= height) {
            ratio = (double)(ACCSIZE-5) / width;
            width = ACCSIZE-5;
            height = (int)(height * ratio);
        } else {
            if (getHeight() > 150) {
                ratio = (double)(ACCSIZE-5) / height;
                height = ACCSIZE-5;
                width = (int)(width * ratio);
            } else {
                ratio = (double)getHeight() / height;
                height = getHeight();
                width = (int)(width * ratio);
            }
        }
        try {
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        }
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(bg);
        
        g.fillRect(0, 0, ACCSIZE, getHeight());
        g.drawImage(image, getWidth() / 2 - width / 2 + 5,
                getHeight() / 2 - height / 2, this);
    }
    
}
