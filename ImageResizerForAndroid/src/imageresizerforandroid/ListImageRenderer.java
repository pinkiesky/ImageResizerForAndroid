package imageresizerforandroid;

import imageselect.UTKUI;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

/**
 *
 * @author fonter
 */
public class ListImageRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent (JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        ImageContainer ic = (ImageContainer) value;
        
        if (!ic.isCacheCreate()) {
            ic.setCache(new ImageIcon(UTKUI.createResizedImageCopy(ic.getImage(), 48)));
        }
        
        this.setIcon(ic.getCache());
        this.setText(String.format("[%dx%d] %s", ic.getImage().getWidth(), ic.getImage().getHeight(), ic.getFile().getName()));
        
        return this;
    }
    
}
