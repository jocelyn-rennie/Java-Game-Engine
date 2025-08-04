package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the status command, allowing the player to retrieve information
 * about their inventory, specific items, or their overall status.
 * 
 * <p>
 * The status command can display a list of items in the player's inventory, 
 * provide details about a specific item, or show the player's general status.
 * </p>
 */
public class Status extends Command 
{

    protected String topic;
    
    public Status(String topic)
    {
        super();
        this.commandType = CommandType.valueOf("STATUS");
        this.topic = topic;
    }

    public String execute(GameState gameState)
    {
        /*
        Executes the status command. Retrieves and displays information based on the specified topic.
        If the topic is "inventory", it lists all items in the player's inventory.
        If the topic matches an item name, it displays the item's description.
        If the topic is "player", it displays the player's general status.
        */

        String combined = "";

        //could go through arraylists like in player class, can maybe take it from player class using get
        if(topic.equals("inventory"))
        {
            for (Equipment e : gameState.getPlayer().getEquipment()) 
            {
                combined = combined + " " + e.getName();
            }
            for (Item e : gameState.getPlayer().getInventory()) 
            {
                combined = combined + " " + e.getName();
            }

            return "Inventory: " + combined;
        }
        //specific item in inventory
        else if(gameState.getPlayer().hasItem(topic) == true)
        {
            return gameState.getPlayer().getItem(topic).getDescription();
        }
        //specific equipment in inventory
        else if(gameState.getPlayer().hasEquipment(topic) == true)
        {
            return gameState.getPlayer().getEquipment(topic).getDescription();
        }
        else if(topic.equals("player"))
        {
            return "Player name: " + gameState.getPlayer().getName();
        }
        //shows map to player
        // all commands are printed anyway
        else if(topic.equals("map"))
        {
            //separates from previous so you can see map clearer
            
            return "\n" + gameState.getMap().displayMap() + "\n";
        }
        //returns players score
        else if(topic.equals("score"))
        {
            return "Score: " + gameState.getPlayer().getScoreObject().getScore();
        }
        // if invalid topic
        else
        {
            return "";
        }
    }

    public String toString()
    {
        return "Status command of type " + commandType + " and topic " + topic;
    }
  
}
