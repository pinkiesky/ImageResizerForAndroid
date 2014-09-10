/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import imageresizerforandroid.Densities;
import java.awt.Dimension;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fonter
 */
public class DenTest {
    
    public DenTest () {
    }
    
    @Test
    public void test () {
        Dimension original = new Dimension(48, 48);
        Densities.Density baseline = Densities.getDensityByFolder("mdpi");
        
        assertEquals(new Dimension(36, 36), Densities.getScaleDimension(baseline, Densities.getDensityByFolder("ldpi"), original));
        assertEquals(new Dimension(48, 48), Densities.getScaleDimension(baseline, Densities.getDensityByFolder("mdpi"), original));
        assertEquals(new Dimension(96, 96), Densities.getScaleDimension(baseline, Densities.getDensityByFolder("xhdpi"), original));
        assertEquals(new Dimension(144, 144), Densities.getScaleDimension(baseline, Densities.getDensityByFolder("xxhdpi"), original));
        assertEquals(new Dimension(192, 192), Densities.getScaleDimension(baseline, Densities.getDensityByFolder("xxxhdpi"), original));
        
    }
}
