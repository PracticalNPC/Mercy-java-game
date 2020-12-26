/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercy;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author josep
 */
public class BattleIcon 
{
    public static ImageIcon setBattleIcon(Fighter fighter)
    {
        if(fighter.getName()=="_")
        {
            return null;
        }
        String fighterSprite=fighter.getSprite();
        String effectSprite=null;
        if(fighter.getEffects2().containsKey("freeze"))
        {
            effectSprite="freeze_effect.png";
        }
        else
            effectSprite=null;

        ImageIcon icon=null;
        try {
             icon=MergedIcons.createCombinedIcon(fighterSprite, effectSprite);
        } catch (Exception ex) {
            Logger.getLogger(BattleIcon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return icon;
    }
    
}
