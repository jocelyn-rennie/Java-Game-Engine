package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the drop command, allowing the player to drop an item from their inventory into the current room.
 * 
 * <p>
 * This command checks if the player possesses the specified item and, if so, removes it from their inventory
 * and adds it to the current room. If the player does not have the item, an error message is returned.
 * </p>
 */
public class Drop extends Command 
{
    protected String item;
    
    public Drop(String item)
    {
        super();
        this.commandType = CommandType.valueOf("DROP");
        this.item = item;
    }

    public String execute(GameState gameState)
    {
        if(gameState.getPlayer().hasItem(item) == true)
        {
            gameState.getMap().getCurrentRoom().addItem(gameState.getPlayer().getItem(item));
            gameState.getPlayer().getInventory().remove(gameState.getPlayer().getItem(item));
            return "You drop: " + item;
        }
        else if(gameState.getPlayer().hasEquipment(item) == true)
        {
            gameState.getMap().getCurrentRoom().addEquipment(gameState.getPlayer().getEquipment(item));
            gameState.getPlayer().getEquipment().remove(gameState.getPlayer().getEquipment(item));
            return "You drop: " + item;
        }
        else
        {
            return "You cannot drop nonexistent";
        }

    }

    public String toString()
    {
        return "You want to drop the " + item + "of type " + commandType;
    }
   
}
