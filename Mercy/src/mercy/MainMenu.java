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


public class MainMenu 
{
    private JFrame frame;
    private JPanel mainPanel;
    private JLayeredPane mapAndBackGround;
    private JPanel optionsPanel;
    private JLabel background;
    private JPanel bottomPanel;
    private boolean newGame;
    private boolean optionClicked;

    public MainMenu()
    {
        this.frame = new JFrame("GOONS DUNGEON: The Game");
        frame.setSize(1440, 864);
        frame.setMinimumSize(new Dimension(1440, 864));
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));// makes everything in panel go from top to bottom
        

        
        mainPanel.setMaximumSize(new Dimension(1440, 864));
        mapAndBackGround = new JLayeredPane();
        mapAndBackGround.setMaximumSize(new Dimension(1440, 864));
        
        optionsPanel = new JPanel();
        //optionsPanel.setLayout(new GridLayout(7, 20));
        optionsPanel.setMaximumSize(new Dimension(1440, 864));
        optionsPanel.setOpaque(false);
        
        background=new JLabel();
        background.setMaximumSize(new Dimension(1440, 864));
        background.setForeground(Color.red);
        
        optionsPanel.setBounds(0, 0, 1440, 864);
        background.setBounds(0, 0, 1440, 864);
        mapAndBackGround.add(optionsPanel, 1, 0);
        mapAndBackGround.add(background, 0, 0);
        
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        
        
        mainPanel.add(mapAndBackGround);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);//moves mainpanel to center of frame
        
        bottomPanel=new JPanel();//bottom panel
        bottomPanel.setMaximumSize(new Dimension(1440, 0));
        mainPanel.add(bottomPanel);
        mainPanel.setBackground(Color.black);
        



        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /////////////////////////
        

    }
    public void runGUI()
    {
        setBackground(background);
        setOptionsPanel();
        frame.setVisible(true);
        Music.sound("main_menu_song.wav");
    }
    public JPanel getMainPanel()
    {
        setBackground(background);
        setOptionsPanel();
       // Music.music("main_menu_song.wav");
        return mainPanel;
    }
    public void setBackground(JLabel background)
    {
       
        try 
        {
            Image image = ImageIO.read(getClass().getResource("images/main menu.png"));
            this.background.setIcon(new ImageIcon(image));
        }
        catch (Exception ex) 
        {
            System.out.println(ex);
            
        }
    }
    public void setOptionsPanel()
    {
        optionClicked=false;
        JPanel spaceFiller=new JPanel();
        spaceFiller.setMaximumSize(new Dimension(1440, 350));
        JPanel spaceFiller2=new JPanel();
        spaceFiller2.setMaximumSize(new Dimension(100, 200));
        optionsPanel.setBackground(Color.red);
        
        spaceFiller.setBackground(Color.PINK);
        spaceFiller.setOpaque(false);
        spaceFiller2.setOpaque(false);
        spaceFiller2.setBackground(Color.blue);
        optionsPanel.add(spaceFiller);

        optionsPanel.add(spaceFiller2);
        JPanel panel=new JPanel();
        JButton newGameButton=new JButton("NEW GAME");
        JButton loadGameButton=new JButton("LOAD GAME");
        
       // spaceFiller2.setLayout(new BoxLayout(spaceFiller2, BoxLayout.Y_AXIS));
        
        spaceFiller2.add(newGameButton);
        spaceFiller2.add(loadGameButton);
        
        newGameButton.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent e)
        { 
            optionClicked=true;
            newGame=true;
            System.out.println(newGame);
        }
        });
        loadGameButton.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent e)
        { 
            optionClicked=true;
            newGame=false;
            System.out.println(newGame);
        }
        });
    }
    public boolean getNewGameStatus()
    {
        return newGame;
    }
    public boolean getOptionClicked()
    {
        return optionClicked;
    }
    public void setOptionClicked(boolean clicked)
    {
        optionClicked=clicked;
    }
    
}
