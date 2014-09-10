package imageresizerforandroid;

import imageselect.ImageSelect;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

/**
 *
 * @author fonter
 */
public class MainFrame extends javax.swing.JFrame implements ActionListener {
    private ArrayList<ImageContainer> imageList = new ArrayList<ImageContainer>();

    public MainFrame () {
        initComponents();
        
        reloadFormatList();
        
        reloadDensity();
        reloadImageList();
        
        reloadConvertAbility();
    }
    
    public void reloadFormatList () {
        //Out
        panelFormats.removeAll();
        
        panelFormats.setLayout(new GridLayout(Formats.getFormats().size(), 1));
        
        for (Formats.ImageFormat imageFormat : Formats.getFormats()) {
            JCheckBox jcb = new JCheckBox(String.format("%s (*.%s)", imageFormat.name, imageFormat.ext));
            jcb.setSelected(true);
            jcb.getModel().addActionListener(this);
            
            panelFormats.add(jcb);
        }
    }
    
    public void reloadDensity () {
        //Out
        panelOutDensities.removeAll();
        
        panelOutDensities.setLayout(new GridLayout(Densities.getDensities().size(), 1));
        
        for (Densities.Density density : Densities.getDensities()) {
            JCheckBox jcb = new JCheckBox(String.format("%s (%s)", density.name, density.folder));
            jcb.setSelected(true);
            
            jcb.getModel().addActionListener(this);
            
            panelOutDensities.add(jcb);
        }
        
        //In
        panelInDensity.removeAll();
        
        panelInDensity.setLayout(new GridLayout(Densities.getDensities().size(), 1));
        
        boolean first = true;
        ButtonGroup buttonGroup = new ButtonGroup();
        for (Densities.Density density : Densities.getDensities()) {
            JRadioButton jrb = new JRadioButton(String.format("%s (%s)", density.name, density.folder));
            jrb.getModel().addActionListener(this);
            
            if (first) {
                jrb.setSelected(true);
                
                first = false;
            }
            buttonGroup.add(jrb);
            
            panelInDensity.add(jrb);
        }
    }
    
    public void reloadImageList () {
        DefaultListModel model = new DefaultListModel();
        
        for (ImageContainer imageContainer : imageList) {
            model.add(model.size(), imageContainer);
        }
        
        listImage.setModel(model);
        listImage.setCellRenderer(new ListImageRenderer());
    }
    
    public Formats.ImageFormat[] getSelectedFormats () {
        ArrayList<Formats.ImageFormat> formats = Formats.getFormats();
        
        ArrayList<Formats.ImageFormat> al = new ArrayList<Formats.ImageFormat>();
        
        for (int i = 0; i < panelFormats.getComponentCount(); i++) {
            JToggleButton component = (JToggleButton) panelFormats.getComponent(i);
            if (component.isSelected()) {
                al.add(formats.get(i));
            }
        }
        
        return al.toArray(new Formats.ImageFormat[al.size()]);
    }
    
    public Densities.Density[] getSelectedOutDensities () {
        ArrayList<Densities.Density> densities = Densities.getDensities();
        
        ArrayList<Densities.Density> al = new ArrayList<Densities.Density>();
        
        for (int i = 0; i < panelOutDensities.getComponentCount(); i++) {
            JToggleButton component = (JToggleButton) panelOutDensities.getComponent(i);
            if (component.isSelected()) {
                al.add(densities.get(i));
            }
        }
        
        return al.toArray(new Densities.Density[al.size()]);
    }
    
    public Densities.Density getSelectedInDensity () {
        ArrayList<Densities.Density> densities = Densities.getDensities();
        
        for (int i = 0; i < panelInDensity.getComponentCount(); i++) {
            JToggleButton component = (JToggleButton) panelInDensity.getComponent(i);
            if (component.isSelected()) {
                return densities.get(i);
            }
        }
        
        return null;
    }
    
    protected boolean isConvertAbility () {
        return !imageList.isEmpty() && getSelectedFormats().length != 0 && 
                getSelectedOutDensities().length != 0 && getSelectedInDensity() != null;
    }
    
    public void reloadConvertAbility () {
        boolean convertAbility = isConvertAbility();
        
        menuButtonConvert.setEnabled(convertAbility);
    }
    
    protected void addNewImage () {
        File[] files = ImageSelect.showSelectImageDialog();
        
        for (File file : files) {
            try {
                BufferedImage read = ImageIO.read(file);
                
                if (read == null) throw new IOException();
                
                ImageContainer imageContainer = new ImageContainer(read, file);
                
                imageList.add(imageContainer);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error opening image: " + file.getName(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        reloadImageList();
        reloadConvertAbility();
    }
    
    protected void removeSelectedImage () {
        List selectedValuesList = listImage.getSelectedValuesList();
        for (Object object : selectedValuesList) {
            imageList.remove(object);
        }
        
        reloadImageList();
        reloadConvertAbility();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        menuButtonConvert = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listImage = new javax.swing.JList();
        buttonAdd = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        panelFormats = new javax.swing.JPanel();
        panelOutDensities = new javax.swing.JPanel();
        panelInDensity = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ImageResizer");

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        menuButtonConvert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageresizerforandroid/convert.png"))); // NOI18N
        menuButtonConvert.setText("Convert");
        menuButtonConvert.setFocusable(false);
        menuButtonConvert.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        menuButtonConvert.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuButtonConvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButtonConvertActionPerformed(evt);
            }
        });
        toolBar.add(menuButtonConvert);

        jScrollPane1.setViewportView(listImage);

        buttonAdd.setText("Add");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        panelFormats.setBorder(javax.swing.BorderFactory.createTitledBorder("Formats"));

        javax.swing.GroupLayout panelFormatsLayout = new javax.swing.GroupLayout(panelFormats);
        panelFormats.setLayout(panelFormatsLayout);
        panelFormatsLayout.setHorizontalGroup(
            panelFormatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
        );
        panelFormatsLayout.setVerticalGroup(
            panelFormatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 67, Short.MAX_VALUE)
        );

        panelOutDensities.setBorder(javax.swing.BorderFactory.createTitledBorder("Out Densities"));

        javax.swing.GroupLayout panelOutDensitiesLayout = new javax.swing.GroupLayout(panelOutDensities);
        panelOutDensities.setLayout(panelOutDensitiesLayout);
        panelOutDensitiesLayout.setHorizontalGroup(
            panelOutDensitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelOutDensitiesLayout.setVerticalGroup(
            panelOutDensitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelInDensity.setBorder(javax.swing.BorderFactory.createTitledBorder("In Density"));

        javax.swing.GroupLayout panelInDensityLayout = new javax.swing.GroupLayout(panelInDensity);
        panelInDensity.setLayout(panelInDensityLayout);
        panelInDensityLayout.setHorizontalGroup(
            panelInDensityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelInDensityLayout.setVerticalGroup(
            panelInDensityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(">TO>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(removeButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelFormats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(341, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelInDensity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelOutDensities, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonAdd)
                            .addComponent(removeButton)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelOutDensities, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelInDensity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelFormats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        addNewImage();
    }//GEN-LAST:event_buttonAddActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        removeSelectedImage();
    }//GEN-LAST:event_removeButtonActionPerformed

    private void menuButtonConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButtonConvertActionPerformed
        try {
            CustomImageIO.write(new File("./images/"), getSelectedFormats(), getSelectedInDensity(), getSelectedOutDensities(), imageList);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuButtonConvertActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton buttonAdd;
    protected javax.swing.JLabel jLabel1;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JList listImage;
    protected javax.swing.JButton menuButtonConvert;
    protected javax.swing.JPanel panelFormats;
    protected javax.swing.JPanel panelInDensity;
    protected javax.swing.JPanel panelOutDensities;
    protected javax.swing.JButton removeButton;
    protected javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed (ActionEvent e) {
        reloadConvertAbility();
    }
}
