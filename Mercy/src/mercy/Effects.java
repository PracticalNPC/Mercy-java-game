/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercy;

import java.util.HashMap;

/**
 *
 * @author josep
 */
public class Effects 
{
    HashMap<String, Integer> currentEffects;
    public Effects()
    {
        currentEffects=new HashMap<String, Integer>();
    }
    public void enableEffects(Fighter fighter)
    {
       HashMap<String, Integer> effects=fighter.getEffects2();
        for(String effect:effects.keySet())
        {
            if(currentEffects.containsKey(effect))
            {
            }
            else
            {
                currentEffects.put(effect, effects.get(effect));
                if(effect=="brace")
                {
                    fighter.setDefense(fighter.getDefense()+20);
                }
            }
            if(effect=="bleed")
            {
                fighter.setHealth(fighter.getHealth()-5);
            }
        }
      //  for(String effect:currentEffects.keySet())
        {
            if(effects.containsKey("brace")==false&&currentEffects.containsKey("brace"))
            {
                currentEffects.remove("brace");
                fighter.setDefense(fighter.getDefense()-20);
            }
            if(effects.containsKey("bleed")==false&&currentEffects.containsKey("bleed"))
                currentEffects.remove("bleed");
        }
            
    }

    
}
