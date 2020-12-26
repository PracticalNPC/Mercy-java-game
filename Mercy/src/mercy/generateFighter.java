/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercy;

/**
 *
 * @author josep
 */
public class generateFighter 
{
    //Fighter foe=new Fighter();
    
    public static Fighter createNormalFoe(Fighter player,String fighterName)
    {
        Fighter foe=new Fighter(fighterName);
        int playerLevel=player.getLevel();
        int levelCounter=playerLevel;
        for(int i=0;i<playerLevel;i++)
        {
            double picker=Math.random();
            if(picker<.2)
                foe.setDexterity(foe.getDexterity()+1);
            else if(picker<.4)
                foe.setEndurance(foe.getEndurance()+1);
            else if(picker<.6)
                foe.setStamina(foe.getStamina()+1);
            else if(picker<.8)
                foe.setStength(foe.getStrength()+1);
            else
                foe.setWitchery(foe.getWitchery()+1);
        }
        System.out.println(foe.getDexterity());
        System.out.println(foe.getEndurance());
        System.out.println(foe.getStamina());
        System.out.println(foe.getStrength());
        System.out.println(foe.getWitchery());
       

        return foe;
    }
    public static Fighter createPlayer(String playerName)
    {
        Fighter player=new Fighter(playerName);
        return player;
    }
    
}
