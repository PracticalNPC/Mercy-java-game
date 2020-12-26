/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class FighterManager {
    
    private Fighter fighter;
    public static Fighter createNewFighter(String fighterName)//creates a fighter and corresponding .txt file for fighter
    {
         
        Fighter createdFighter=new Fighter(fighterName);
        try
        {
            String filename = "src/fighters/"+fighterName+".txt";
            String textToWrite = "name=" + fighterName +"\n" +
                                "displayedName=test\n" +
                                "health=1000\n" +
                                "stamina=1\n" +
                                "strength=1\n" +
                                "dexterity=1\n" +
                                "witchery=1\n" +
                                "endurance=1\n";
            Files.write(Paths.get(filename), textToWrite.getBytes());
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }
        return createdFighter;
    }
    public static void getData(String fighterName)
    {
        //Fighter fighterFromData=new Fighter(fighterName);
        ArrayList<String> data=new ArrayList<String>();
        HashMap<String, String> attributetoValue = new HashMap<String, String>();
        try
        {
            String filename = "src/fighters/"+fighterName+".txt";
            for(String line : Files.readAllLines(Paths.get(filename)))
            {
                    //data.add(line);
                attributetoValue.put(line.substring(0, line.indexOf("=")), line.substring(line.indexOf("=")+1));
            }
        } 
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
                System.out.println(attributetoValue);
            
    }
    public static void saveFighter(Fighter fighter)
    {
        String fighterName=fighter.getName();
        try
        {
        String filename = "src/fighters/"+fighterName+".txt";
            String textToWrite = "name=" + fighterName +"\n" +
                                "displayedName=test\n" +
                                "health="+fighter.getHealth()+"\n" +
                                "stamina="+fighter.getStamina()+"\n" +
                                "strength="+fighter.getStrength()+"\n" +
                                "dexterity="+fighter.getDexterity()+"\n" +
                                "witchery="+fighter.getWitchery()+"\n" +
                                "endurance="+fighter.getEndurance()+"\n";
        Files.write(Paths.get(filename), textToWrite.getBytes());
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    public static Fighter setFighter(String fighterName)
    {
        Fighter fighter=new Fighter(fighterName);

        HashMap<String, String> data = new HashMap<String, String>();
        try
        {
            String filename = "src/fighters/"+fighterName+".txt";
            for(String line : Files.readAllLines(Paths.get(filename)))
            {
                //data.add(line);
                data.put(line.substring(0, line.indexOf("=")), line.substring(line.indexOf("=")+1));
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        fighter.setName(data.get("name"));
        System.out.println(data);
        fighter.setHealth(Integer.parseInt(data.get("health")));
        fighter.setStamina(Integer.parseInt(data.get("stamina")));
        fighter.setStength(Integer.parseInt(data.get("strength")));
        fighter.setDexterity(Integer.parseInt(data.get("dexterity")));
        fighter.setWitchery(Integer.parseInt(data.get("witchery")));
        fighter.setEndurance(Integer.parseInt(data.get("endurance")));
        
        //fighterFromData.setStrength(Integer.parseInt(data.get("strength")));
      // System.out.println(data.get("name"));
        return fighter;
    }
    
}