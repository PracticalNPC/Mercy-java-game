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
import java.util.Timer;
import java.util.TimerTask;


public class BattleGUI 
{
    private JFrame frame;
    private JLayeredPane mainAndBackground;
    private JLabel gameBrackground;
    private JPanel mainPanel;
    private JLayeredPane mapAndBackGround;
    private JPanel mapPanel;
    private JLabel background;
    private JLabel gameBackground;
    private JPanel bottomPanel;
    private JPanel movementPanel;
    
    private JPanel skillPanel;
    private JPanel statsPanel;
    
    private String lastSkill;
    
    /////////////////////////
    private JLabel playerHealth;
    private JLabel playerStamina;
    //////////////////
    
    
    private ArrayList<ArrayList<JButton>> buttonField=new ArrayList<ArrayList<JButton>>();
    
    private BattleManager battleManager=new BattleManager();
    private ArrayList<ArrayList<Fighter>> playerField=battleManager.getField().getPlayerField();
    
    private int yCounter=0;
    private int xCounter=0;
    private int xBorder=0;
    private int yBorder=0;
     private Fighter player=battleManager.getPlayer();
     private ArrayList<Fighter> fighters=battleManager.getFighterArrayList();
    
    
    public BattleGUI()
    {
        this.frame = new JFrame("GOONS DUNGEON: The Game");
        frame.setSize(1440, 864);
        frame.setMinimumSize(new Dimension(1440, 864));
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));// makes everything in panel go from top to bottom
        
        statsPanel=new JPanel();
        statsPanel.setMaximumSize(new Dimension(1056, 96));
        mainPanel.add(statsPanel);
        
        mainPanel.setMaximumSize(new Dimension(1056, 672));
        mapAndBackGround = new JLayeredPane();
        mapAndBackGround.setMaximumSize(new Dimension(1056, 480));
        
        mapPanel = new JPanel();
        mapPanel.setLayout(new GridLayout(5, 11));
        mapPanel.setMaximumSize(new Dimension(1056, 480));
        mapPanel.setOpaque(false);
        
        background=new JLabel();
        background.setMaximumSize(new Dimension(1056, 480));
        background.setForeground(Color.red);
        
        mapPanel.setBounds(0, 0, 1056, 480);
        background.setBounds(0, 0, 1056, 480);
        mapAndBackGround.add(mapPanel, 1, 0);
        mapAndBackGround.add(background, 0, 0);
        
        
        mainPanel.add(mapAndBackGround);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);//moves mainpanel to center of frame
        
        bottomPanel=new JPanel();//bottom panel
        bottomPanel.setMaximumSize(new Dimension(1056, 96));
        mainPanel.add(bottomPanel);
        mainPanel.setBackground(Color.black);
        bottomPanel.setLayout(new BorderLayout());
        
        
        movementPanel=new JPanel();//movement panel added to left of bottom panel
        movementPanel.setMaximumSize(new Dimension(200, 96));
        movementPanel.setLayout(new BoxLayout(movementPanel, BoxLayout.Y_AXIS));
        movementPanel.setBackground(Color.black);
        bottomPanel.add(movementPanel, BorderLayout.WEST);
        
        skillPanel=new JPanel();// skill panel added to the middle of bottom panel
        skillPanel.setMaximumSize(new Dimension(1056, 200));
        skillPanel.setBackground(Color.black);
        bottomPanel.add(skillPanel, BorderLayout.CENTER);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /////////////////////////
        
        playerHealth=new JLabel("Health: "+battleManager.getPlayer().getHealth());
        playerStamina=new JLabel("Effects: " + battleManager.getPlayer().getEffects2()+"");
        
       

    }
    public void runGUI() throws InterruptedException
    {
        battleManager.startBattle();
        runBattleMap();
        setBackground(background);
        setMovementPanel();
        setSkillPanel();
        setStatsPanel();
        
        frame.setVisible(true);
    }
    public JPanel getBattleGUI() throws InterruptedException// from time wait
    {
        
        battleManager.startBattle();
        runBattleMap();
        setBackground(background);
        setMovementPanel();
        setSkillPanel();
        setStatsPanel();

        return mainPanel;
    }
    public void runBattleMap()
    {
        setMap();
    }
    public void setMap()//renders data from BattleManager
    {
        Border emptyBorder = BorderFactory.createBevelBorder(1);
        for(int y=0;y<playerField.size();y++)
        {
            buttonField.add(new ArrayList<JButton>());
            
            for(int x=0;x<playerField.get(y).size();x++)
            {
                buttonField.get(y).add(createButton(x, y));
                buttonField.get(y).get(x).setBorder(emptyBorder);
                
                if(playerField.get(y).get(x)!=null)
                {
                    if(playerField.get(y).get(x).getName()==battleManager.getPlayer().getName())
                    {
                        try 
                        {
                            Image img = ImageIO.read(getClass().getResource("images/"+battleManager.getPlayer().getSprite()));
                            buttonField.get(y).get(x).setIcon(new ImageIcon(img));
                        }
                        catch (Exception ex) 
                        {
                            System.out.println(ex);
                        }
                       // buttonField.get(y).get(x).setBackground(Color.BLACK);
                       // buttonField.get(y).get(x).setBorder(emptyBorder);
                        mapPanel.add(buttonField.get(y).get(x)); 
                    }
                    else if((playerField.get(y).get(x).getName()=="X"))
                    {
                        try 
                        {
                            Image img = ImageIO.read(getClass().getResource("images/foe_level_1.png"));
                            buttonField.get(y).get(x).setIcon(new ImageIcon(img));
                        }
                        catch (Exception ex) 
                        {
                            System.out.println(ex);
                        }
                        mapPanel.add(buttonField.get(y).get(x)); 
                    }
                    else if((playerField.get(y).get(x).getName()=="_"))
                    {
                        mapPanel.add(buttonField.get(y).get(x)); 
                    }
                }
                else// this should never happen
                {
                    mapPanel.add(buttonField.get(y).get(x));
                }
            }
        }
        
    }
    public void setBackground(JLabel background)
    {
       
        try 
        {
            Image image = ImageIO.read(getClass().getResource("images/background.png"));
            this.background.setIcon(new ImageIcon(image));
        }
        catch (Exception ex) 
        {
            System.out.println(ex);
            
        }
    }
    public void updateMap()
    {
             
        battleManager.checkDead();
        for(int y=0;y<playerField.size();y++)
        {
            for(int x=0;x<playerField.get(y).size();x++)
            {
                if(playerField.get(y).get(x)!=null)
                {
                    if(playerField.get(y).get(x).getName()==battleManager.getPlayer().getName())
                    {
                        try 
                        {
                            Image img = ImageIO.read(getClass().getResource("images/"+battleManager.getPlayer().getSprite()));
                            buttonField.get(y).get(x).setIcon(new ImageIcon(img));
                        }
                        catch (Exception ex) 
                        {
                            System.out.println(ex);
                        }
                    }
                    else if((playerField.get(y).get(x).getName()=="X"))
                    {
                        try 
                        {
                            Image img = ImageIO.read(getClass().getResource("images/"+battleManager.getFighterArrayList().get(1).getSprite()));
                            buttonField.get(y).get(x).setIcon(new ImageIcon(img));
                            
                        }
                        catch (Exception ex) 
                        {
                            System.out.println(ex);
                        }

                    }
                    else if((playerField.get(y).get(x).getName()=="_"))
                    {
                         buttonField.get(y).get(x).setIcon(null);
                    }
                }
                else//should never happen
                {
                    buttonField.get(y).get(x).setIcon(null);
                }
            }
        }
        
        for(int i=0; i<fighters.size();i++)
        {   
           try{
            if(fighters.get(i).getEffects2().size()>0)
            {
                try {
                    buttonField.get(fighters.get(i).getLocationY()).get(fighters.get(i).getLocationX()).setIcon(BattleIcon.setBattleIcon(fighters.get(i)));
                } catch (Exception ex) {
                    Logger.getLogger(BattleGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           }catch(Exception ex){
               //System.out.println(ex); //not all characts have an array of effects yet so you get an index out of bounds Exception
           }
        }
        
        
        
        battleManager.getField().printField();
        playerHealth.setText("Health: "+battleManager.getPlayer().getHealth()+"");
        playerStamina.setText("Effects: "+battleManager.getPlayer().getEffects2()+"");
        if(player.getStamina()<0)
        {
            battleManager.turnManager();
        }

       // FighterManager.saveFighter(battleManager.getPlayer());

    }
    public void setMovementPanel()//organizes movement buttons and panel & adds function to movement buttons
    {



        JButton cancel=new JButton(" Cancel   ");
        JButton endTurn=new JButton("End Turn");
        cancel.setBackground(Color.RED);
        
        Image image = null;
        
        cancel.setPreferredSize(new Dimension(96, 32));
        endTurn.setPreferredSize(new Dimension(96, 32));
       // movementPanel.add(panel);
        movementPanel.add(cancel);
        movementPanel.add(endTurn);

        cancel.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent e)
        {
                
            // code to be executed when the button is clicked
                
                lastSkill=null;
                updateMap();
        }
        });
        
        endTurn.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            lastSkill=null;
            battleManager.turnManager();
            updateMap();
        }
        });
        
        
    }
    public void runAI()
    {
        
    }
    private boolean wasClicked=false;
    public JButton createButton(int x, int y)
    {
        Border emptyBorder = BorderFactory.createBevelBorder(1);
        Border border = BorderFactory.createLineBorder(Color.GREEN, 1);
        JButton button=new JButton();
        button.setPreferredSize(new Dimension(96, 96));
        button.setBackground(Color.BLACK);
        
        button.setOpaque(false);
        //button.setContentAreaFilled(false);
        button.setBorder(emptyBorder);
        button.setBorderPainted(false);

        button.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent e)
        { 
            if(battleManager.getPlayer().getStamina()>0)
            {
            if(lastSkill==null)
            {
            buttonField.get(y).get(x).setBorder(emptyBorder);
            if(wasClicked&&xBorder==x&&y==yBorder)
            {
                battleManager.moveFighter(x, y,battleManager.getPlayer());
                wasClicked=false;
                updateMap();
                yCounter=0;
                xCounter=0;
                xBorder=0;
                yBorder=0;
                
                button.setBorderPainted(false);
            }
            else
            {
                button.setBorderPainted(true);
                buttonField.get(yBorder).get(xBorder).setBorder(emptyBorder);
                 buttonField.get(yBorder).get(xBorder).setBorderPainted(false);
                buttonField.get(y).get(x).setBorder(border);
                yBorder=y;
                xBorder=x;
                wasClicked=true;
            }
            }
            else
            {
                battleManager.useSkill(lastSkill,battleManager.getPlayer(),battleManager.getFighterAtLocation(x, y) );
                lastSkill=null;
                updateMap();
            }

            
            }//end of stamina if
        if(player.getStamina()<=0)
        {
            Timer t = new Timer();
            TimerTask tt = new TimerTask() 
            {
                @Override
                public void run() 
                {
                    battleManager.turnManager();
                    updateMap();
                    
                    //tt.cancel();
                    t.cancel();
                };
            };
            t.schedule(tt,500,500);
            

            
        }
            


            
        }
        });

        return button;
    }
    public void setSkillPanel()
    {
        ArrayList<JButton> skills=new ArrayList<JButton>();
        JPanel bottonBox=new JPanel();
        bottonBox.setMaximumSize(new Dimension(500, 480));
        bottonBox.setLayout(new GridLayout(1, 10));
        
        for(int i=0;i<15;i++)
        {
            skills.add(createSkillButton(i));
            bottonBox.add(skills.get(i));
        }
        skillPanel.add(bottonBox);
        
        for(int i=0;i<battleManager.getPlayer().getSkills().size();i++)
        {
            
        }
    }
    public JButton createSkillButton(int i)//number of skill from array of player skills
    {
        JButton skillButton=new JButton();
        skillButton.setPreferredSize(new Dimension(64, 64));
        try
        {
        try 
        {
            Image img = getSkillIcon(battleManager.getPlayer().getSkills().get(i));
            skillButton.setIcon(new ImageIcon(img));
        }
        catch (Exception ex) 
        {
           // System.out.println(ex);
        }
        skillButton.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent e)
        {   
            lastSkill=battleManager.getPlayer().getSkills().get(i);
        }
        });
        }        catch (Exception ex) 
        {
            System.out.println(ex);
        }
        
        return skillButton;
    }
    public Image getSkillIcon(String skill)
    {
        Image skillIcon=null;
        if(skill=="slash")
        {
            try 
            {
                skillIcon = ImageIO.read(getClass().getResource("images/icons/slash_icon.png"));
            }
            catch (Exception ex) 
            {
                System.out.println(ex);
            }
        }
        if(skill=="lunge")
        {
            try 
            {
                skillIcon = ImageIO.read(getClass().getResource("images/icons/lunge_icon.png"));
            }
            catch (Exception ex) 
            {
                System.out.println(ex);
            }
        }
        if(skill=="teleport")
        {
            try 
            {
                skillIcon = ImageIO.read(getClass().getResource("images/icons/portal_icon.png"));
            }
            catch (Exception ex) 
            {
                System.out.println(ex);
            }
        }
        if(skill=="freeze")
        {
            try 
            {
                skillIcon = ImageIO.read(getClass().getResource("images/icons/net_icon.png"));
            }
            catch (Exception ex) 
            {
                System.out.println(ex);
            }
        }
        if(skill=="blink")
        {
            try 
            {
                skillIcon = ImageIO.read(getClass().getResource("images/icons/blink_icon.png"));
            }
            catch (Exception ex) 
            {
                System.out.println(ex);
            }
        }
        if(skill=="polymorph")
        {
            try 
            {
                skillIcon = ImageIO.read(getClass().getResource("images/icons/polymorph_icon.png"));
            }
            catch (Exception ex) 
            {
                System.out.println(ex);
            }
        }
        if(skill=="brace")
        {
            try 
            {
                skillIcon = ImageIO.read(getClass().getResource("images/icons/brace_icon.png"));
            }
            catch (Exception ex) 
            {
                System.out.println(ex);
            }
        }
        
        return skillIcon;
    }
    public void setStatsPanel()
    { 
        JLayeredPane statsAndBackground=new JLayeredPane();
        statsAndBackground.setMaximumSize(new Dimension(1056, 96));
        JPanel playerStats=new JPanel();
        JLabel statsPanelBackground=new JLabel();
        playerStats.setBounds(0, 0, 1056, 96);
        statsPanelBackground.setBounds(0, 0, 1056, 96);
        playerStats.setOpaque(false);
        
        statsAndBackground.add(playerStats, 1, 0);
        statsAndBackground.add(statsPanelBackground, 0, 0);
        
    try 
        {
            Image img = ImageIO.read(getClass().getResource("images/stats_background.png"));
            statsPanelBackground.setIcon(new ImageIcon(img));
        }
        catch (Exception ex) 
        {
            System.out.println(ex);
        }
        
        
        
//        JLabel playerHealth=new JLabel("Blood: "+battleManager.getPlayer().getBlood());
//        JLabel playerStamina=new JLabel("engergy: ");
        
        playerStats.setLayout(new BoxLayout(playerStats, BoxLayout.Y_AXIS));
        playerStats.add(playerHealth);
        playerStats.add(playerStamina);
        playerHealth.setForeground(Color.RED);
        playerStamina.setForeground(Color.YELLOW);
        
        statsPanel.setLayout(new BorderLayout());
        statsPanel.setBackground(Color.black);
        playerStats.setMaximumSize(new Dimension(1056, 96));
        statsPanel.add(statsAndBackground);
        
        //statsPanel.add(playerStats);
        
        
    }

    

}
