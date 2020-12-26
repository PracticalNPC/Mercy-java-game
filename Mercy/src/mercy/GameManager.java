/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercy;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.Border;
import java.awt.geom.AffineTransform;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.AffineTransformOp;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GameManager 
{
    private JFrame frame;
    private JPanel mainPanel;
    private MainMenu menu;
    private BattleGUI battleGUI;

    public GameManager()
    {
        this.frame = new JFrame("GOONS DUNGEON: The Game");
        frame.setSize(1440, 864);
        frame.setMinimumSize(new Dimension(1460, 905));
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));// makes everything in panel go from top to bottom
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /////////////////////////
        menu=new MainMenu();
        battleGUI= new BattleGUI();
    }
    public void runGUI() throws InterruptedException
    {
        //mainPanel=battleGUI.getBattleGUI();
        mainPanel=menu.getMainPanel();
        //Music.sound("main_menu_song.wav");
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        int i=0;
        int delay = 1000; //milliseconds
         ActionListener taskPerformer = new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
                 if(menu.getOptionClicked()==true)
                 {
                        frame.getContentPane().removeAll();
                     try {
                         mainPanel=battleGUI.getBattleGUI();
                     } catch (InterruptedException ex) {
                         Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
                     }
                        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
                        frame.setVisible(true);
                        menu.setOptionClicked(false);
                        
                      

                 }
             }
         };
         new Timer(delay, taskPerformer).start();
        
        
        
    }
    



    

}
