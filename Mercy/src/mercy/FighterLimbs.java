/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercy;

import java.util.ArrayList;

/**
 *
 * @author josep
 */
public class FighterLimbs 
{
    private int rightArmHealth;
    private int leftArmHealth;
    private int rightLegHealth;
    private int leftLegHealth;
    private int headHealth;
    private int torsoHealth;
    public FighterLimbs()
    {
        rightArmHealth=200;
        leftArmHealth=200;
        rightLegHealth=300;
        leftLegHealth=300;
        headHealth=100;
        torsoHealth=500;
    }
    public int getRightArmHealth()
    {
        return rightArmHealth;
    }
    public int getLeftArmHealth()
    {
        return leftArmHealth;
    }
    public int getRightLegHealth()
    {
        return rightLegHealth;
    }
    public int getLeftLegHealth()
    {
        return leftLegHealth;
    }
    public int getHeadHealth()
    {
        return headHealth;
    }
    public int getTorsoHealth()
    {
        return torsoHealth;
    }
    public void damageRightArm(int damage)
    {
        rightArmHealth-=damage;
    }
    public void damageLeftArm(int damage)
    {
        leftArmHealth=leftArmHealth-damage;
        System.out.print("health from damageleftarm"+leftArmHealth);
    }
    public void damageTorso(int damage)
    {
        torsoHealth-=damage;
    }
    
}
