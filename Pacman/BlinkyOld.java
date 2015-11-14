import greenfoot.*;
import java.util.*;

/**
 * Write a description of class Blinky here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlinkyOld extends Ghost
{
    private List<int[]> path = new ArrayList<int[]>();
    private Pacman samePacman;
    private int frame;
    public BlinkyOld(){
        super();
        getImage().scale(Wall.SIZE, Wall.SIZE);
        frame = 0;
    }
        public void addedToWorld(World world){//needed so that we don't have getWorld() return null in the constructor
            super.addedToWorld(world);
            samePacman = (Pacman)(world.getObjects(Pacman.class).toArray()[0]);
            int i = 0;
            for(;i < 17; i++){
                path.add(new int[2]);
            }
            for(i = 0;i < 4; i++){
                path.get(i)[0] = 13;
                path.get(i)[1] = 15 - i;
            }
            for(;i < 8;i++){
                path.get(i)[0] = 13 - i + 3;
                path.get(i)[1] = 12;
            }
            for(;i < 14;i++){
                path.get(i)[0] = 9;
                path.get(i)[1] = 12 + i - 7;
            }
            
            for(;i < 17; i++){
                path.get(i)[0] = 9 + i - 13;
                path.get(i)[1] = 18;
            }
 
    }
    private void updatePacmanPath(){
        path.add(new int[2]);
        path.get(path.size()-1)[0] = samePacman.getX();
        path.get(path.size()-1)[1] = samePacman.getY();
        int index = 0;
        boolean deleteRest = false;
        //would it be better to do this with a linked list, a list iterator, and declaring the array first?
        while( index < path.size() - 1 && !deleteRest){
            if(path.get(path.size()-1)[0] == path.get(index)[0] && 
                   path.get(path.size()-1)[1] == path.get(index)[1]){
                deleteRest = true;
                //does this need to be in an if statement?
            }
            index++;
        }
        
        while(deleteRest && index < path.size()){
            path.remove(index);
        }       
    }
//     private void updatePacmanPath2(){
//         int[] toAdd = new int[2];
//         toAdd[0] = samePacman.getX();
//         toAdd[1] = samePacman.getY();
//         Iterator<int[]> stepInPath = path.iterator();
//         boolean deleteRest = false;
//         while(stepInPath.hasNext() && !deleteRest ){
//             deleteRest = (toAdd[0] == stepInPath.next()[0] &&
//                             toAdd[1] == stepInPath.previous()[1]);// does this work as expected
//             stepInPath.next();   
//         }
//         if(deleteRest){
//             int lastIndexToRemove = stepInPath.nextIndex();
//             while(lastIndexToRemove < path.size()){//or would hasNext() work.
//                 path.removeLast();
//             }
//         }else{//if no looping occurs
//             path.add(toAdd);
//         }
//     
//     }
    private void moveAlongPath(){
         if(!path.isEmpty() && frame > 20){
            setLocation(path.get(0)[0],path.get(0)[1]);
            if(frame % 10 % 4 != 0){//Needed to slow down blinky so 3/10 times he doesn't move
                path.remove(0);
            }   
        }
    }
    public void act() 
    {   
        frame++;
        updatePacmanPath();
        moveAlongPath();
    }    
}
