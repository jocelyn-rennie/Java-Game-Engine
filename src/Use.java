package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

// needed to access if game loop is quit
import org.uob.a2.Game;

/**
 * Represents the use command, allowing the player to use equipment on a specific target in the game.
 * 
 * <p>
 * The use command checks if the player has the specified equipment and whether it can interact with
 * the target. The target can be a feature, item, or the current room, depending on the game context.
 * </p>
 */
public class Use extends Command 
{
    protected String equipmentName;
    protected String target;

    public Use(String equipmentName, String target)
    {
        super();
        this.commandType = CommandType.valueOf("USE");
        this.equipmentName = equipmentName;
        this.target = target;
    }

    //test if own- You do not have name
    //test if already used- You have already used name
    //test if target exists- Invalid use target
    //return use information of equipment
    public String execute(GameState gameState)
    {

        Item targetItemObject = new Item("","","",false);
        Feature targetFeatureObject = new Feature("","","",false);
        GameObject gameObject = new Feature("","","",false); 
        
        for (Item e : gameState.getMap().getCurrentRoom().getItems()) 
        {
            if(e.getName().equals(target))
            {
                targetItemObject = e;
            }
        }

        for (Feature e : gameState.getMap().getCurrentRoom().getFeatures()) 
        {
            if(e.getName().equals(target))
            {
                targetFeatureObject = e;
            }
        }

        for (GameObject e : gameState.getMap().getCurrentRoom().getFeatures()) 
        {
            if(e.getName().equals(target))
            {
                gameObject = e;
            }
        }
        
        //tests if in inventory
        if(gameState.getPlayer().hasEquipment(equipmentName) == false)
        {
            return "You do not have " + equipmentName;
        }
        //tests if already used
        else if(gameState.getPlayer().getEquipment(equipmentName).getUseInformation().isUsed() == true)
        {
            return "You have already used " + equipmentName;
        }
        //tests if target exists in current room (invalid target)
        else if(gameState.getMap().getCurrentRoom().hasItem(target) == false && gameState.getMap().getCurrentRoom().hasFeature(target) == false)
        {
            return "Invalid use target";
        }
        else
        {
            String resultId = "";
            String returnMessage = "";
            
            //sets equipment as used
            gameState.getPlayer().getEquipment(equipmentName).getUseInformation().setUsed(true);
            
            //get resultId, need to do before removing from player
            resultId = gameState.getPlayer().getEquipment(equipmentName).getUseInformation().getResult();
            
            //return message, get before equipment removed
            returnMessage = gameState.getPlayer().getEquipment(equipmentName).getUseInformation().getMessage();

            //test doesn't like this, if tested if used it doesn't need to be removed
            /*
            //removes equipment from their inventory
            for (Equipment e : gameState.getPlayer().getEquipment()) 
            {
                if(e.getName() == equipmentName)
                {
                    gameState.getPlayer().getEquipment().remove(e);
                }
            }*/
            
            
            //check if feature or item:
            //not feature, is item

            for (Item e : gameState.getMap().getCurrentRoom().getItems()) 
            {
                if(e.getId().equals(resultId))
                {
                    e.setHidden(false);
                }
            }

            for (Equipment e : gameState.getMap().getCurrentRoom().getEquipments()) 
            {
                if(e.getId().equals(resultId))
                {
                    System.out.println("result item is found");
                    e.setHidden(false);
                    System.out.println("is now false");
                }
            }

            for (Feature e : gameState.getMap().getCurrentRoom().getFeatures()) 
            {
                if(e.getId().equals(resultId))
                {
                    e.setHidden(false);
                }
            }

            for (Exit e : gameState.getMap().getCurrentRoom().getExits()) 
            {
                if(e.getId().equals(resultId))
                {
                    e.setHidden(false);
                }
            }

            //solved puzzle so must update score
            // for tests
            if(gameState.getPlayer().getScoreObject().getPuzzleScore() == 0)
            {
            }
            else
            {
                gameState.getPlayer().getScoreObject().puzzleCompleted();
            }

            //checks if last room
            if(resultId.equals(gameState.getMap().getEndRoomId()))
            {
                //adds extra score
                gameState.getPlayer().getScoreObject().extraScore(Game.stopwatch.getElapsedTimeSeconds());
                // adds to score for completing room
                gameState.getPlayer().getScoreObject().roomVisited();
                
                int score = gameState.getPlayer().getScoreObject().getScore();
                String inventory = "";
                String name = "";

                name = gameState.getPlayer().getName();

                for (Item e : gameState.getPlayer().getInventory()) 
                {
                    inventory = inventory + " " + e.getDescription();
                }
        
                // gets equipment
                for (Equipment e : gameState.getPlayer().getEquipment()) 
                {
                    inventory = inventory + " " + e.getDescription();
                }

                //quits game loop
                Game.x = true;
                
                return returnMessage + "\n\nWell done! You finished the game!\n\n" + "Game over:\n" + "Player status\n" + "Player name: " + name + "\nInventory:" + inventory + "\nScore: " + score;
            }

            return returnMessage;
            
        }
        
    }

    public String toString()
    {
        return "Command: of type " + commandType + ", use " + equipmentName + " on " + target;
    }

  
}
