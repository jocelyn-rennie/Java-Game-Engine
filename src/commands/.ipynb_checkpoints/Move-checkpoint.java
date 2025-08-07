package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the move command, allowing the player to navigate to a different room in the game world.
 * 
 * <p>
 * The move command checks if the specified direction corresponds to an available exit in the current room.
 * If a matching exit is found, the player's location is updated to the connected room.
 * </p>
 */
public class Move extends Command {

    protected String direction;

    public Move(String direction)
    {
        super();
        this.commandType = CommandType.valueOf("MOVE");
        this.direction = direction;
    }

    public String execute(GameState gameState)
    {
        Exit rightExit = new Exit("","","","",false);
        int xPlayerPos = 0;
        int yPlayerPos = 0;
        String nextRoom = "";
        String returnStatement = "";
        
        // finds right exit
        for (Exit e : gameState.getMap().getCurrentRoom().getExits()) 
        {
            if(e.getName().equals(direction))
            {
                rightExit = e;
            }
        }
        //right exit and exit not hidden
        if((rightExit.getName().equals(direction)) && (rightExit.getHidden() == false))
        {
            // moves player position
            if(direction.equals("north"))
            {
                yPlayerPos = gameState.getMap().getYPlayerPos();
                yPlayerPos--;
                gameState.getMap().setYPlayerPos(yPlayerPos);
                returnStatement = "Moving towards north\n";
            }
            else if(direction.equals("south"))
            {
                yPlayerPos = gameState.getMap().getYPlayerPos();
                yPlayerPos++;
                gameState.getMap().setYPlayerPos(yPlayerPos);
                returnStatement = "Moving towards south\n";
            }
            else if(direction.equals("east"))
            {
                xPlayerPos = gameState.getMap().getXPlayerPos();
                xPlayerPos++;
                gameState.getMap().setXPlayerPos(xPlayerPos);
                returnStatement = "Moving towards east\n";
            }
            else if(direction.equals("west"))
            {
                xPlayerPos = gameState.getMap().getXPlayerPos();
                xPlayerPos--;
                gameState.getMap().setXPlayerPos(xPlayerPos);
                returnStatement = "Moving towards west\n";
            }

            // updates map
            gameState.getMap().updateMap();
            
            // need to return description before change room, put in if above to display move direction
            
            //changes to next room
            nextRoom = rightExit.getNextRoom();
            gameState.getMap().setCurrentRoom(nextRoom);

            //room completed so update score
            gameState.getPlayer().getScoreObject().roomVisited();

            return returnStatement;            
        }
        //right direction but exit is hidden so can't go through
        else if((rightExit.getName().equals(direction)) && (rightExit.getHidden() == false))
        {
            return "No exit found in that direction.";
        }
        // not right exit direction
        else
        {
            return "No exit found in that direction.";
        }
    }

    public String toString()
    {
        return "Move command of type " + commandType + " and direction " + direction;
    }
    
  
}
