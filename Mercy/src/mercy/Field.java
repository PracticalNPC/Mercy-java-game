package mercy;
import java.util.ArrayList;

public class Field 
{
    private ArrayList<ArrayList<Fighter>> playerField;//should be called fighter field :/
    private int fieldLength;
    private int fieldWidth;
    private ArrayList<String> fieldArray;
    //constructor
    public Field()
    {
        this.fieldLength=5;
        this.fieldWidth=11;
        this.playerField=new ArrayList<ArrayList<Fighter>>();
        for(int y=0;y<this.fieldLength;y++)
        {
            this.playerField.add(new ArrayList<Fighter>()); 
            for(int x=0;x<this.fieldWidth;x++)
            {
             this.playerField.get(y).add(x, null);
            }
        }
    }
    //field sizes
    public int getLengthSize()    //returns veritcal field size
    {
        return this.playerField.size();
    }
    public ArrayList<Fighter> getWidthField(int x)    //    returns horizontal field
    {
        return this.playerField.get(x);
    }
    public int getWidthSize(int i)    //  returns horziontal field size
    {
        return this.playerField.get(i).size();
    }
    public void printField()//prints entire field
    {
        //updateFiledArrayFormat();
        for(int y=0;y<this.fieldLength;y++)
        {
            for(int x=0;x<this.fieldWidth;x++)
            {
                if(this.playerField.get(y).get(x)!=null)
                {
                   System.out.print(" "+this.playerField.get(y).get(x).getName());
                }
                else
                System.out.print(" "+this.playerField.get(y).get(x));
            }
            System.out.println("");
        }
    }
    //location services
    public Fighter getFighterFromLocation(int x,int y)//returns the object that is in a specific tile
    {
        return this.playerField.get(y).get(x);
        
    }
    public void setFighterLocation(int x,int y, Fighter fighter)//moves fighter location and fighter class location updated
    {
        this.playerField.get(y).set(x, fighter);

            if(this.playerField.get(fighter.getLocationY()).get(fighter.getLocationX())==this.playerField.get(y).get(x))
            {
                this.playerField.get(fighter.getLocationY()).set(fighter.getLocationX(),null);
            }

        fighter.locationUpdate(x,y);
    }
    public void removeFromField(int x,int y)// makes a specific tile null
    {
        this.playerField.get(y).set(x, null);
    }
    public ArrayList<ArrayList<Fighter>> getPlayerField()//returns arraylist of enitire map, including nulls
    {
        return this.playerField;
    }
    public int getPlayerFieldSize()
    {
        return playerField.size()*playerField.get(0).size();
    }
}
