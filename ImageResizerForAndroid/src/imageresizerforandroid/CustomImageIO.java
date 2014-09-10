package imageresizerforandroid;

import imageselect.UTKUI;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

/**
 *
 * @author fonter
 */
public class CustomImageIO {
    public static void write (File saveFolder, Formats.ImageFormat[] formats, Densities.Density baseline, Densities.Density[] densitys, List<ImageContainer> imageList) throws IOException {
        for (Formats.ImageFormat imageFormat : formats) {
            for (Densities.Density density : densitys) {
                File densityFolder = new File(saveFolder.getAbsolutePath() + '/' + imageFormat.ext + '/' + density.folder);
                System.out.println(densityFolder.getAbsolutePath() + " " + densityFolder.mkdirs());
                
                for (ImageContainer imageContainer : imageList) {
                    BufferedImage image  = imageContainer.getImage();
                    
                    Dimension scaleDimension = Densities.getScaleDimension(baseline, density, new Dimension(image.getWidth(), image.getHeight()));
                    BufferedImage resize = Scalr.resize(image, scaleDimension.width, scaleDimension.height);
                    
                    ImageIO.write(resize, imageFormat.ext, 
                            new File(densityFolder.getAbsolutePath() + '/' + imageContainer.getNormalizeName() + '.' + imageFormat.ext));
                }
            }
        }
    }
}
