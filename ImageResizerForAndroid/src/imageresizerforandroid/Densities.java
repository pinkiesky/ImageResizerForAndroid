package imageresizerforandroid;

import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author fonter
 */
public class Densities {    
    private static final ArrayList<Density> densities = new ArrayList<Density>();
    static {        
        densities.add(new Density(0.75, "ldpi", "low"));
        densities.add(new Density(1.00, "mdpi", "medium"));
        densities.add(new Density(1.50f, "hdpi", "high"));
        densities.add(new Density(2.00f, "xhdpi", "e-high"));
        densities.add(new Density(3.00f, "xxhdpi", "e-e-high"));
        densities.add(new Density(4.00f, "xxxhdpi", "e-e-e-high"));
    }

    public static ArrayList<Density> getDensities () {
        return densities;
    }
    
    public static Density getDensityByFolder (String folder) {
        for (Density density : densities) {
            if (density.folder.equals(folder)) {
                return density;
            }
        }
        
        return null;
    }
    
    public static Dimension getScaleDimension (Density request, Dimension size) {
        return getScaleDimension(densities.get(0), request, size);
    }
    
    public static Dimension getScaleDimension (Density baseline, Density request, Dimension size) {
        if (baseline == null || request == null || size == null) {
            throw new NullPointerException();
        }
        
        double delta = request.mult / baseline.mult;
        
        return new Dimension((int)(size.width * delta), (int)(size.height * delta));
    }
    
    public static class Density {
        public final double mult;
        public final String name, folder;

        public Density (double mult, String folder, String name) {
            this.mult = mult;
            this.name = name;
            this.folder = folder;
        }

        @Override
        public String toString () {
            return String.format("Density@[%f, %s, %s]", mult, name, folder);
        }
    }
}
