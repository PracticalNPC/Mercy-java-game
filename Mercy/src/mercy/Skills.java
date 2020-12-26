/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercy;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author josep
 */

public class Skills
{
    private Field field;
    private ArrayList<Fighter> fighters;
    public Skills(Field field,ArrayList<Fighter> fighters )
    {
        this.field=field;
        this.fighters=fighters;
    }
    public  double getDistance(Fighter attacker,Fighter target)
    {
        
        int AttackerX=attacker.getLocationX();
        int AttackerY=attacker.getLocationY();
        int targetX=target.getLocationX();
        int targetY=target.getLocationY();
        double distance= Math.sqrt((targetY - AttackerY) * (targetY - AttackerY) + (targetX - AttackerX) * (targetX - AttackerX));
        return distance;
    }
    public  void attack(Fighter attacker,Fighter target)
    {
        double distance=getDistance(attacker,target);
        if(distance<=1.5)
        {
        int tempHealth=target.getHealth();
        int tempStrength=attacker.getStrength();
        tempHealth=tempHealth-tempStrength;
        target.setHealth(tempHealth);
        attacker.setStamina(attacker.getStamina()-1);
        Music.sound("swing.wav");
        Music.sound("sword.wav");
        System.out.println(target.getHealth());
        }
        
        else
            System.out.println("attack too far");
    }
    public  void slash(Fighter attacker,Fighter target)
    {
        double distance=getDistance(attacker,target);
        if(distance<=1.5)
        {
            double chance=Math.random();
            if(.3<chance)
            {
            int tempHealth=target.getHealth();
            int tempStrength=attacker.getStrength();
            tempHealth=tempHealth-tempStrength;
            target.setHealth(tempHealth);
            Music.sound("swing.wav");
            Music.sound("sword.wav");
            System.out.println(target.getHealth());
            }
            else
            {
                Music.sound("swing.wav");
            }
            attacker.setStamina(attacker.getStamina()-1);
        }
        
        else
            System.out.println("attack too far");
    }
    public  void lunge(Fighter attacker,Fighter target)
    {
        double distance=getDistance(attacker,target);
        if(distance<=1.5)
        {
            double chance=Math.random();
            if(.1<chance)
            {
            int tempHealth=target.getHealth();
            int attackerDamage=attacker.getStrength();
           // tempHealth=tempHealth-tempStrength;
          //  target.setHealth(tempHealth);
            target.damagePlayer(attackerDamage);
            Music.sound("swing.wav");
            Music.sound("sword.wav");
           // System.out.println(target.getHealth());
            }
            else
            {
                Music.sound("swing.wav");
            }
            attacker.setStamina(attacker.getStamina()-1);
        }
        
        else
            System.out.println("attack too far");
    }
    public void teleportTest(int x,int y, Fighter fighter)//test code
    {
        int tempX=fighter.getLocationX();
        int tempY=fighter.getLocationY();
        Fighter cellMoveTo=getFighterAtLocation(x,y);
        field.setFighterLocation(x, y, fighter);
        field.setFighterLocation(tempX, tempY, cellMoveTo);
        fighter.setStamina(fighter.getStamina()-1);
    }
    public void freezePlayer(Fighter caster,Fighter target)
    {
        target.setStamina(-1);
        target.addEffect2("freeze", 2);
    }
    public void brace(Fighter caster)
    {
        caster.addEffect2("brace", 5);
    }
    public Fighter getFighterAtLocation(int x, int y)
    {
        
        return field.getPlayerField().get(y).get(x);
    }
    
}
