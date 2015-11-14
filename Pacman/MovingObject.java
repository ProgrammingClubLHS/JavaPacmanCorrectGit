import greenfoot.*;

/**
 * Write a description of class MovingObject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovingObject extends Actor
{
    /**
     * Act - do whatever the MovingObject wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
//     protected boolean wallInFront(){
//         Object[] intersectingThings = getIntersectingObjects(Wall.class).toArray();
//         boolean wallPresence = false;
//         if(intersectingThings != null && intersectingThings.length > 0){
//             for(int index = 0; index < intersectingThings.length; index++){
//                 Wall possibleWall = (Wall)(intersectingThings[index]);
//                 if      (possibleWall.getX() > getX() && getRotation() == 0){
//                     wallPresence = true;
//                 }else if(possibleWall.getX() < getX() && getRotation() == 180){
//                     wallPresence = true;
//                 }else if(possibleWall.getY() > getY() && getRotation() == 90){
//                      wallPresence = true;
//                 }else if(possibleWall.getY() < getY() && getRotation() == 270){
//                      wallPresence = true;
//                 }
//             }
//         }
//         return wallPresence;
//     }
    protected boolean wallInFront(int rotationCheck){
        Object[] inFront = (getObjectsAtOffset(
                                    (int)(Math.cos(rotationCheck * Math.PI / 180)),
                                    (int)(Math.sin(rotationCheck * Math.PI / 180)),
                                    Wall.class).toArray()
                                );
        boolean wallPresence = false;
        if(inFront != null && inFront.length > 0){
            for(int index = 0; index < inFront.length; index++){
                Wall possibleWall = (Wall)(inFront[index]);
                if(possibleWall != null){
                    wallPresence = true;
                }
            }
        }               
        return wallPresence;
    }
    public void act() 
    {
        for(int i = 0;i < 1; i++){
            try{
                if(!wallInFront(getRotation())){
                    move(1);
                }
            }catch( RuntimeException e){
            Greenfoot.ask(e.getMessage());
            }
        }
        if(Greenfoot.isKeyDown(" ")){
            move(3);
        }
    }    
}
