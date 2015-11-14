import greenfoot.*;


public class Ghost extends MovingObject
{
    private int direction;
    public Ghost(){
        super();
        getImage().scale(Wall.SIZE, Wall.SIZE);
        direction = 0;// 0==east, 1==south,2==west,3==north
    }
    private int numberOfWalls(){
        int output = 0;
        if(wallInFront(0)){
            output++;
        }
        if(wallInFront(90)){
            output++;
        }
        if(wallInFront(180)){
            output++;
        }
        if(wallInFront(270)){
            output++;
        }
        return output;
    }
    private void moveInDirection(){
        if(direction == 0){
            setLocation(getX() +1, getY());
        }else if(direction == 1){
            setLocation(getX(), getY() + 1);
        }else if(direction == 2){
            setLocation(getX() -1, getY());
        }else{//if( direction == 3)
            setLocation(getX(), getY()-1);
        }
        if(getX() == 0 && direction == 2){
            setLocation(getWorld().getWidth(),getY());
        }
        
        if(getX() == getWorld().getWidth() && direction == 0){
            setLocation(0,getY());
        }
    }
    
    protected void moveTowardThis(int targetX,int targetY){
        if(numberOfWalls()==2){//lane or corner
            if(wallInFront(90 * direction)){//corner
                if(wallInFront(90 * ((direction + 1) % 4))){//look on clockwise side
                    direction = (direction - 1) % 4;
                }else{//if(wallInFront(90 * ((direction - 1) % 4)))//other side
                    direction = (direction + 1) % 4;
                }
            }//else in lane and do nothing
        }else{//intersection
            double[] distanceChoices = new double[4];
            int directionOfMinDist = 0;
            for(int i = 0; i < distanceChoices.length;i++){
                distanceChoices[i] = Math.pow(getX() 
                                       + (int)(Math.cos(i * Math.PI / 2))//changing quadrant
                                       - targetX,2)
                                   + Math.pow(getY()
                                       + (int)(Math.sin(i * Math.PI / 2))//changing quadrant
                                       - targetY,2);
                if(wallInFront(90 * i/* the choice we are testing */)){//doesn't go through walls
                    distanceChoices[i] = 200000;
                }
                if((direction + 2) % 4 == i){//doesn't turn around
                    distanceChoices[i] = 200000;
                }
                if(distanceChoices[i] < distanceChoices[directionOfMinDist]){
                    directionOfMinDist = i;
                }

            }
            getWorld().showText(distanceChoices[0] + " " + distanceChoices[1] + " "
                            + distanceChoices[2] + " " + distanceChoices[3],
                            getWorld().getWidth()/2,getWorld().getHeight()-1);// testing code
            
            direction = directionOfMinDist;
            
        }
        moveInDirection();

    }
    
    public void act() 
    {

    }    
}
