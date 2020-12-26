package mercy;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;
public class BattleManager 
{
    private Field field;
    private ArrayList<Fighter> fighters;
    private Fighter player;
    private int playerX;
    private int playerY;
    private int playersDead=0;
    private Skills skills;
    private Boolean enemyTurn;
    public BattleManager()
    {
        
        this.field=new Field();
        this.fighters=new ArrayList<Fighter>();
        this.player=FighterManager.setFighter("player");
        this.player.setSprite("sprite.png");
        
       // Fighter foe=new EnemyAI("X");
        Fighter foe=generateFighter.createNormalFoe(player,"X");
        foe.setSprite("foe_level_1.png");
      //  Fighter foe2=new Fighter("X");
        fighters.add(player);
        fighters.add(foe);
       // fighters.add(foe2);
        
        int i=0;
        for(int y=0;y<field.getPlayerField().size();y++)
        {
            for(int x=0;x<field.getPlayerField().get(y).size();x++)
            {
                fighters.add(new Fighter ("_"));
                fighters.get(i+2).setHealth(99999999);//for every foes you need to add that number to x
                i++;
            }
        }

        
    }
    public void startBattle() throws InterruptedException//remember too many empty cells -fix this// extra cells are used to replace dead enemies
    {
        int i=0;
        for(int y=0;y<field.getPlayerField().size();y++)
        {
            for(int x=0;x<field.getPlayerField().get(y).size();x++)
            {
                this.field.setFighterLocation(x,y,fighters.get(i));
                i++;
            }
        }
        setFighterLocation(3,2, this.player);
        
        setFighterLocation(7,2, fighters.get(1));
        //setFighterLocation(7,3, fighters.get(2));
        this.field.printField();
        System.out.println();
//        while(fighters.size()!=1)
//        {
//        playerTurn();
//        }
//        System.out.println();
        this.field.printField();
        
       // enemyAI(fighters.get(1));
        
        this.field.printField();
        
        player.addSkill("slash");
        player.addSkill("lunge");
        player.addSkill("teleport");
        player.addSkill("freeze");
        player.addSkill("polymorph");
        player.addSkill("blink");
        player.addSkill("brace");
    }
    public void playerMove(String direction)
    {
        playerX=player.getLocationX();
        playerY=player.getLocationY();
        if(direction.equals("up"))
            playerY=player.getLocationY()-1;
        if(direction.equals("down"))
            playerY=player.getLocationY()+1;
        if(direction.equals("right"))
            playerX=player.getLocationX()+1;
        if(direction.equals("left"))
            playerX=player.getLocationX()-1;
        if(this.field.getFighterFromLocation(playerX,playerY)==null)
        {
            this.field.setFighterLocation(playerX,playerY,player); 
        }
    }
    public void playerMove(int x,int y)
    {
        
        int tempX=player.getLocationX();
        int tempY=player.getLocationY();
        Fighter cellMoveTo=getFighterAtLocation(x,y);
        
        double distance=getDistance(player,cellMoveTo);
        if(distance<=1.5)
        {
        field.setFighterLocation(x, y, player);
        field.setFighterLocation(tempX, tempY, cellMoveTo);
        }
        else 
            System.out.println("player tried to move too far");
    }
    public void setFighterLocation(int x,int y, Fighter fighter)//test code
    {
        int tempX=fighter.getLocationX();
        int tempY=fighter.getLocationY();
        Fighter cellMoveTo=getFighterAtLocation(x,y);
        field.setFighterLocation(x, y, fighter);
        field.setFighterLocation(tempX, tempY, cellMoveTo);
    }
    public void moveFighter(int x,int y, Fighter fighter)//test code
    {
        if(fighter.getStamina()>0)
        {
            int tempX=fighter.getLocationX();
            int tempY=fighter.getLocationY();
            Fighter cellMoveTo=getFighterAtLocation(x,y);
            double distance=getDistance(fighter,cellMoveTo);
            if(distance<=1.5)
            {
                field.setFighterLocation(x, y, fighter);
                field.setFighterLocation(tempX, tempY, cellMoveTo);
                fighter.setStamina(fighter.getStamina()-1);
                Music.sound("walk_sand.wav");
                
            }
            else
                System.out.println("player tried to move too far");
        }
        else
            System.out.println("out of stamina");
    }
    public Field getField()
    {
        return this.field;
    }
    public int getPlayerX()// get x coord
    {
        return playerX;
    }
        public int getPlayerY()// get y coord
    {
        return playerY;
    }
    public Fighter getPlayer()
    {
        return this.player;
    }
    public Fighter getFighterAtLocation(int x, int y)
    {
        return field.getPlayerField().get(y).get(x);
    }
    public ArrayList<Fighter> getFighterArrayList()
    {
        return fighters;
    }
    public void checkDead()
    {
        for(int i=0;i<fighters.size();i++)
        {
           if(fighters.get(i).getHealth()<=0)
           {
            int x=fighters.get(i).getLocationX();
            int y=fighters.get(i).getLocationY();
           // fighters.get(i).setHealth(99);
            field.removeFromField(x, y);
            //this.field.setFighterLocation(x,y,fighters.get(55+playersDead));
            playersDead++;
           }
        }
    }
    public double getDistance(Fighter attacker,Fighter target)
    {
        int AttackerX=attacker.getLocationX();
        int AttackerY=attacker.getLocationY();
        int targetX=target.getLocationX();
        int targetY=target.getLocationY();
        double distance= Math.sqrt((targetY - AttackerY) * (targetY - AttackerY) + (targetX - AttackerX) * (targetX - AttackerX));
        return distance;
    }
    public void useSkill(String skill,Fighter attacker,Fighter target)
    {
        skills=new Skills(field,fighters);
        if(skill=="attack")
        {        
            skills.attack( attacker, target);
        }
        if(skill=="slash")
        {        
            skills.slash( attacker, target);
        }
        if(skill=="lunge")
        {        
            skills.lunge( attacker, target);
        }
        if(skill=="teleport")
        {
            skills.teleportTest( target.getLocationX(),target.getLocationY(),attacker);
        }
        if(skill=="freeze")
        {
            skills.freezePlayer(attacker, target);
        }
        if(skill=="brace")
        {
            skills.brace(attacker);
        }
    }
    private int cycleNumber=0;
    public void turnPassed()
    {
        //System.out.println(turnsPassed);
       // turnsPassed++;
    }
    public void enemyAI(Fighter foe) //throws InterruptedException
    {

        Fighter target=player;
        //foe.setStamina(2);
        //while(foe.getStamina()>0)
        {
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(BattleManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(foe.getDistance(foe, target)>=1.5)
            {

               if(foe.getLocationX()>target.getLocationX())
               {
                  // moveFighter(foe.getLocationX()-1,foe.getLocationY(), foe);
                   if(foe.getLocationY()<target.getLocationY())
                        moveFighter(foe.getLocationX()-1,foe.getLocationY()+1, foe);
                   else if(foe.getLocationY()>target.getLocationY())
                       moveFighter(foe.getLocationX()-1,foe.getLocationY()-1, foe);
                   else
                        moveFighter(foe.getLocationX()-1,foe.getLocationY(), foe);
               }
               else if(foe.getLocationX()<target.getLocationX())
               {
                   //moveFighter(foe.getLocationX()+1,foe.getLocationY(), foe);
                    if(foe.getLocationY()<target.getLocationY())
                        moveFighter(foe.getLocationX()+1,foe.getLocationY()+1, foe);
                   else if(foe.getLocationY()>target.getLocationY())
                       moveFighter(foe.getLocationX()+1,foe.getLocationY()-1, foe);
                   else
                        moveFighter(foe.getLocationX()+1,foe.getLocationY(), foe);
               }
                else if(foe.getLocationY()<target.getLocationY())
               {
                   moveFighter(foe.getLocationX(),foe.getLocationY()+1, foe);
               }
                else if(foe.getLocationY()>target.getLocationY())
               {
                   moveFighter(foe.getLocationX(),foe.getLocationY()-1, foe);
               }
               
            }
            else
            {
                useSkill("attack",foe,player);
            }
                
        }
        field.printField();

        turnPassed();
    }
    public void turnManager() 
    {
        for(Fighter fighter:fighters)
        {
            fighter.getCurrentEffects().enableEffects(fighter);
            System.out.println("player defense: "+player.getDefense());
        }
        if(fighters.get(1).getHealth()>0)
        {
            player.setStamina(player.getStamina()+1);
            if (player.getStamina()>2)
            {
                player.setStamina(2);
            }
            fighters.get(1).setStamina(fighters.get(1).getStamina()+1);
            enemyAI(fighters.get(1));


            cycleNumber++;
            for(Fighter fighter:fighters)
            {
                fighter.addTurnNumber();
               // System.out.println(fighter.getTurnNumber());
            }
           // System.out.print("cycle number: "+cycleNumber);
        }
        else
            System.out.println("fight won");
    }


            
}
