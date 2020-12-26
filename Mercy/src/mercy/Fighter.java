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
 * @author joseph
 */
public class Fighter 
{
    public String name;
    public String displayedName;
    public int health=300;//1600;
    public int stamina=1;
    public int stength=1;
    public int dexterity=1;
    public int witchery=1;
    public int endurance=1;
    public int defense=0;
    public int level=300;
    public int locationX=0;
    public int locationY=0;
    public Effects currentEffects=new Effects();
    public FighterLimbs fighterLimbs;
    public String Sprite;
    public ArrayList<String> skills= new ArrayList<String>();
    public ArrayList<String> effects=new ArrayList<String>();
    public HashMap<String,Integer> effects2=new HashMap<String,Integer>();
    public Effects getCurrentEffects()
    {
        return currentEffects;
    }
    public Fighter()
    {
        fighterLimbs=new FighterLimbs();
    }
    public Fighter(String name)
    {
        this.name=name;
        fighterLimbs=new FighterLimbs();
        System.out.println("head health"+fighterLimbs.getHeadHealth());
    }
    public Fighter(String name, int x, int y)
    {
        this.name=name;
        locationX=x;
        locationY=y;
    }
    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public int getLevel()
    {
        return level;
    }
    public void setHealth(int health)
    {
        this.health=health;
    }
    public void setStamina(int stamina)
    {
        this.stamina=stamina;
    }
    public void setStength(int strength)
    {
        this.stength=strength;
    }
    public void setDexterity(int dexterity)
    {
        this.dexterity=dexterity;
    }
    public void setEndurance(int endurance)
    {
        this.endurance=endurance;
    }
        public void setWitchery(int witchery)
    {
        this.witchery=witchery;
    }
        public int getHealth()
    {
        return this.health;
    }
    public int getStamina()
    {
    return this.stamina;
    }
    public int getStrength()
    {
        return this.stength;
    }
    public int getDexterity()
    {
        return this.dexterity;
    }
    public int getWitchery()
    {
        return this.witchery;
    }
    public int getEndurance()
    {
        return this.endurance;
    }
    public int getDefense()
    {
        return defense;
    }
    public void setDefense(int defense)
    {
        this.defense=defense;
    }
    public void locationUpdate(int x,int y)//fighter knows its own location
    {
        this.locationX=x;
        this.locationY=y;
    }
    //default starting location may change later
    public void placeFighter()
    {
        this.locationX=0;
        this.locationY=0;
        
    }
    public int getLocationX()
    {
        return this.locationX;
    }
    public int getLocationY()
    {
        return this.locationY;
    }
    ///
    public void addSkill(String skill)
    {
        skills.add(skill);
    }
    public void printSkills()
    {
        System.out.println(skills);
    }
    public ArrayList<String> getSkills()
    {
        return skills;
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
    public void setSprite(String fileName)
    {
        this.Sprite=fileName;
    }
    public String getSprite()
    {
        return this.Sprite;
    }
    public void addEffect2(String effect, int turns)
    {
        this.effects2.put(effect, turns);
    }
    public HashMap<String,Integer> getEffects2()
    {
        return this.effects2;
    }
    public void enableEffect()
    {
        for(String effect:effects2.keySet())
        {
            if(effects2.containsKey("Added defense"))
            {
                defense=defense+20;
            }
        }
    }
    public FighterLimbs getLimbs()
    {
        return fighterLimbs;
    }
    public void damagePlayer(int damage)
    {
        this.health=health-damage;
        fighterLimbs.damageLeftArm(damage);

    }

    public int turnNumber=0;
    public void addTurnNumber()
    {
        HashMap<String, Integer> temp=new HashMap<String, Integer>();
        turnNumber++;
        for(String effect:effects2.keySet())
        {
            effects2.put(effect, effects2.get(effect)-1);
            if(effects2.get(effect)<=0)
                temp.put(effect,0);
        }
        for(String effect:temp.keySet())
        {
            effects2.remove(effect);
        }
    }
    public int getTurnNumber()
    {
        return turnNumber;
    }
    
    
    
}


