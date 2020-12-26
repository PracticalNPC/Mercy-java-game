/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercy;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author josep
 */
public class Mercy 
{
    public static void main(String[] args) throws Exception 
    {
     //  BattleGUI gui=new BattleGUI();
    //  gui.runGUI();       
        
//        if(1==2==false)
//        {
//            System.out.println("123");
//        }

        
       GameManager manager=new GameManager();
        manager.runGUI();


    } 

}

