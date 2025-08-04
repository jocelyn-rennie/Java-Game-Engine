package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

// needed to access if game loop is quit
import org.uob.a2.Game;

/**
 * Represents the quit command, allowing the player to exit the game.
 * 
 * <p>
 * The quit command signals the end of the game session and provides a summary of the player's
 * current status before termination.
 * </p>
 */
public class Quit extends Command 
{
    public Quit()
    {
        super();
        this.commandType = CommandType.valueOf("QUIT");
    }

    public String execute(GameState gameState)
    {
        // also needs to exit game loop
        String inventory = "";
        int score = 0;
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

        Game.x = true;

        if (gameState.getPlayer().getScoreObject().getPuzzleScore() == 0)
        {
        }
        else
        {
            score = gameState.getPlayer().getScoreObject().getScore();
        }

        return "Game over:\n" + "Player status\n" + "Player name: " + name + "\nInventory:" + inventory + "\nScore: " + score;
    }   
 
}
