import greenfoot.*;

/**
 * Write a description of class wall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wall extends Actor
{
    /**
     * Act - do whatever the wall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public static final int SIZE = 16;
    public Wall(){
        super();
        getImage().scale(Wall.SIZE,Wall.SIZE);
    
    }
    public void act() 
    {
        
    }    
}
