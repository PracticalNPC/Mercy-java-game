/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

class MergedIcons {

//    public static void main(String[] args) throws Exception 
//    {
//        File path = new File("./src/mercy/images/");
//        BufferedImage image = ImageIO.read(new File(path, bottom));
//        BufferedImage overlay = ImageIO.read(new File(path, top));
//        URL urlBG = new URL("http://i.stack.imgur.com/gJmeJ.png");
//        URL urlFG = new URL("http://i.stack.imgur.com/8BGfi.png");
//        final BufferedImage imgBG = ImageIO.read(urlBG);
//        final BufferedImage imgFG = ImageIO.read(urlFG);
//        // For simplicity we will presume the images are of identical size
//        final BufferedImage combinedImage = new BufferedImage( 
//                imgBG.getWidth(), 
//                imgBG.getHeight(), 
//                BufferedImage.TYPE_INT_ARGB );
//        Graphics2D g = combinedImage.createGraphics();
//        g.drawImage(imgBG,0,0,null);
//        g.drawImage(imgFG,0,0,null);
//        g.dispose();
//        Runnable r = new Runnable() {
//
//            @Override
//            public void run() {
//                JPanel gui = new JPanel(new GridLayout(1,0,5,5));
//
//                gui.add(new JLabel(new ImageIcon(imgBG)));
//                gui.add(new JLabel(new ImageIcon(imgFG)));
//                gui.add(new JLabel(new ImageIcon(combinedImage)));
//
//                JOptionPane.showMessageDialog(null, gui);
//            }
//        };
//        // Swing GUIs should be created and updated on the EDT
//        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
//        SwingUtilities.invokeLater(r);
//    }
     public static ImageIcon createCombinedIcon(String bottom, String top) throws Exception 
    {
        File path = new File("./src/mercy/images/");
        final BufferedImage imgBG = ImageIO.read(new File(path, bottom));
        
        if(top==null)
        {
                final BufferedImage combinedImage = new BufferedImage( 
                imgBG.getWidth(), 
                imgBG.getHeight(), 
                BufferedImage.TYPE_INT_ARGB );
                Graphics2D g = combinedImage.createGraphics();
                g.drawImage(imgBG,0,0,null);
                g.dispose();
                return new ImageIcon(combinedImage);
        }
        final BufferedImage imgFG = ImageIO.read(new File(path, top));
        // For simplicity we will presume the images are of identical size
        final BufferedImage combinedImage = new BufferedImage( 
                imgBG.getWidth(), 
                imgBG.getHeight(), 
                BufferedImage.TYPE_INT_ARGB );
        Graphics2D g = combinedImage.createGraphics();
        g.drawImage(imgBG,0,0,null);
        g.drawImage(imgFG,0,0,null);
        g.dispose();
//        Runnable r = new Runnable() {
//
//            @Override
//            public void run() {
//                JPanel gui = new JPanel(new GridLayout(1,0,5,5));
//
//                gui.add(new JLabel(new ImageIcon(imgBG)));
//                gui.add(new JLabel(new ImageIcon(imgFG)));
//                gui.add(new JLabel(new ImageIcon(combinedImage)));
//
//                JOptionPane.showMessageDialog(null, gui);
//            }
//        };
//  //       Swing GUIs should be created and updated on the EDT
////         http://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
//        SwingUtilities.invokeLater(r);
    return new ImageIcon(combinedImage);

    }
}