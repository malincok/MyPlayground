package malincok.playground.escape;

import java.io.IOException;
import java.util.List;

public class Movement
{
    private static int pipeCounter = 0;
    private int finalPipeCount;
    private Direction lastDirection;
    private List<Coordinate> spaces;
    private Coordinate finish;
    private Coordinate newCoordinate;
    private Coordinate currentCoordinate;
    
    public Movement(List<Coordinate> spaces, Coordinate finish, int pipeCount)
    {
        this.spaces = spaces;
        this.finish = finish;
        this.currentCoordinate = new Coordinate(0,0);
        this.finalPipeCount = pipeCount;
        pipeCounter++;
    }
    
    /**
     * I'm careful, so I'll check if there's wall or not
     * @param direction
     * @return
     */
    public boolean canMove(Direction direction)
    {
        return canMove(direction, false);
    }
    
    /**
     * I don't care if there's some wall. I'll not be checking it! I'll be just running...
     * @param direction
     * @throws IOException
     */
    public void run(Direction direction) throws IOException
    {
        if(canMove(direction, true))
        {
            System.out.println("running to the " + direction.toString());
            while (canMove(direction, true))
            {
                currentCoordinate = newCoordinate;
            }
            lastDirection = direction;
            if(finish.equals(currentCoordinate))
            {
                System.out.println("Congratulation! You passed though pipe #" + pipeCounter);
                if(pipeCounter < finalPipeCount)
                {
                    Escape.solution(PipeLoader.loadPipe(pipeCounter + 1, finalPipeCount));
                }
                else if(pipeCounter == finalPipeCount)
                {
                    System.out.println("WOHOOO, YOU'RE GREAT PROGRAMMER!!!");
                    System.exit(0);
                }
            }
        }
        else
        {
            System.out.println("I'm dead X_X");
            System.exit(0);
        }
    }
    
    /**
     * I'll write you where I came from
     * @return
     */
    public Direction getLastDirection()
    {
        return lastDirection;
    }
    
    private Coordinate getNewCoordinate(Direction direction)
    {
        int x = currentCoordinate.getX();
        int y = currentCoordinate.getY();
        switch (direction)
        {
            case NORTH: y = y+1; break;
            case SOUTH: y = y-1; break;
            case EAST: x = x+1; break;
            case WEST: x = x-1; break;
        }
       return new Coordinate(x,y);
    }
    
    private boolean canMove(Direction direction, boolean changeCoordinate)
    {
        newCoordinate = getNewCoordinate(direction);
        boolean can = spaces.contains(newCoordinate) || finish.equals(newCoordinate);
        if(!can || !changeCoordinate)
            newCoordinate = currentCoordinate;
        return can;
    }
}
