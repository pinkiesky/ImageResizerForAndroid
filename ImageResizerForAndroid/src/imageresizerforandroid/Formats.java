package imageresizerforandroid;

import java.util.ArrayList;

/**
 *
 * @author fonter
 */
public class Formats {
    private static ArrayList<ImageFormat> formats = new ArrayList<ImageFormat>();
    static {
        formats.add(new ImageFormat("PNG", "png"));
        formats.add(new ImageFormat("GIF", "gif"));
        formats.add(new ImageFormat("JPEG", "jpg"));
    }

    public static ArrayList<ImageFormat> getFormats () {
        return formats;
    }
    
    public static class ImageFormat {
        public final String name, ext;

        public ImageFormat (String name, String ext) {
            this.name = name;
            this.ext = ext;
        }
    }
}
