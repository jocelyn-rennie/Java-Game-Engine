package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the get command, allowing the player to pick up an item from the current room and add it to their inventory.
 * 
 * <p>
 * This command checks if the specified item is present in the current room. If the item exists and the player
 * does not already have it, the item is added to the player's inventory and removed from the room. Otherwise,
 * an appropriate message is returned.
 * </p>
 */
public class Get extends Command {

    protected String item;
    
    public Get(String item)
    {
        super();
        this.commandType = CommandType.valueOf("GET");
        this.item = item;
    }

    public String execute(GameState gameState)
    {       
        //if already have item
        if(gameState.getPlayer().hasItem(item) == true)
        {
            return "You already have " + item;
        }
        else if(gameState.getPlayer().hasEquipment(item) == true)
        {
            return "You already have " + item;
        }
        //if adds item if item in room
        else if(gameState.getMap().getCurrentRoom().hasItem(item) == true)
        {
            //item game object
            Item removeItem = gameState.getMap().getCurrentRoom().getItemByName(item);
            
            //adds to inventory
            gameState.getPlayer().addItem(gameState.getMap().getCurrentRoom().getItemByName(item));

            // removes from room
            // item as variable
            gameState.getMap().getCurrentRoom().getItems().remove(removeItem);
            
            return "You pick up: " + item;
        }
        //adds to equipment if in currentroom
        else if(gameState.getMap().getCurrentRoom().hasEquipment(item) == true)
        {
            //equipment game object
            Equipment removeEquipment = gameState.getMap().getCurrentRoom().getEquipmentByName(item);
            
            //adds to inventory
            gameState.getPlayer().addEquipment(gameState.getMap().getCurrentRoom().getEquipmentByName(item));
            
            // removes from room
            gameState.getMap().getCurrentRoom().getEquipments().remove(removeEquipment);
            
            return "You pick up: " + item;
        }
        // if not in room
        else
        {
            return "No " + item + " to get.";
        }

    }

    public String toString()
    {
        return "Get command of type " + commandType + " and the target item is " + item;
    }

   
}
