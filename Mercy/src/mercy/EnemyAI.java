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

public class EnemyAI extends Fighter
{
    private Fighter target;
    public EnemyAI(String name, int x, int y)
    {
        this.name=name;
        locationX=x;
        locationY=y;
    }
    public EnemyAI(String name)
    {
        this.name=name;
    }
    public void setTartget(Fighter target)
    {
        this.target=target;
    }
}
