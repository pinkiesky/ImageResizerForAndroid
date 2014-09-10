package imageselect;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class UTKUI {
    
    public static BufferedImage resize (BufferedImage original, Dimension newSize, boolean forJpeg) {
        BufferedImage bi = new BufferedImage(newSize.width, 
                newSize.height, forJpeg ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g = bi.createGraphics();
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        //g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
          //      RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        
    	g.drawImage(original, 0, 0, newSize.width, newSize.height, null); 
    	g.dispose();
        
        return bi;
    }
    
    public static BufferedImage createResizedImageCopy(ImageIcon originalImage, int maxEdgeSize){
    	BufferedImage bi = new BufferedImage(originalImage.getIconWidth(), 
                originalImage.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
    	Graphics g = bi.createGraphics();
        
    	g.drawImage(originalImage.getImage(), 
                0, 0, originalImage.getIconWidth(), originalImage.getIconHeight(), null); 
    	g.dispose();
    	return createResizedImageCopy(bi, maxEdgeSize);
    }
    
    public static BufferedImage createResizedImageCopy(BufferedImage originalImage, int maxEdgeSize){
        final int w = originalImage.getWidth(), h = originalImage.getHeight();
        
        float scaleSize = ((float) maxEdgeSize) / Math.max(w, h);
        int newW = (int) (w * scaleSize), newH = (int) (h * scaleSize);
        
    	BufferedImage scaledBI = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
    	Graphics g = scaledBI.createGraphics();
        
    	g.drawImage(originalImage, 0, 0, newW, newH, null); 
    	g.dispose();
    	return scaledBI;
    }
    
    public static BufferedImage createResizedHeightImageCopy(ImageIcon originalImage, int maxEdgeSize){
    	BufferedImage bi = new BufferedImage(originalImage.getIconWidth(), 
                originalImage.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
    	Graphics g = bi.createGraphics();
        
    	g.drawImage(originalImage.getImage(), 
                0, 0, originalImage.getIconWidth(), originalImage.getIconHeight(), null); 
    	g.dispose();
    	return createResizedHeightImageCopy(bi, maxEdgeSize);
    }
    
    public static BufferedImage createResizedHeightImageCopy(BufferedImage originalImage, int maxEdgeSize){
        final int w = originalImage.getWidth(), h = originalImage.getHeight();
        
        float scaleSize = ((float) maxEdgeSize) / h;
        int newW = (int) (w * scaleSize), newH = (int) (h * scaleSize);
        
    	BufferedImage scaledBI = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
    	Graphics g = scaledBI.createGraphics();
        
    	g.drawImage(originalImage, 0, 0, newW, newH, null); 
    	g.dispose();
    	return scaledBI;
    }
}
