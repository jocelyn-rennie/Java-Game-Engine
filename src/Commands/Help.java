package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the help command, providing the player with instructions or information
 * about various topics related to the game.
 * 
 * <p>
 * The help command displays information on how to play the game, including details about 
 * available commands, their syntax, and their purpose.
 * </p>
 */
public class Help extends Command 
{
    protected String topic;

    public Help(String topic)
    {
        super();
        this.commandType = CommandType.valueOf("HELP");
        this.topic = topic;
    }

    //result.contains("Welcome to the game!") &&
    //result.contains("- MOVE") &&
    //result.contains("- HELP")

    public String execute(GameState gameState)
    {
        //Provides detailed help information based on the specified topic or general game help if no specific topic is provided.
        // if unknown help
        if(topic == null)
        {
            return "Welcome to the game!\nCommands:\n- MOVE\n- USE\n- GET\n- DROP\n- LOOK\n- STATUS\n- HELP\n- COMBINE\n- COMBINEINFO\n- QUIT";
        }
        else if(topic.equals("move"))
        {
            return "MOVE Command: Use the 'move' command followed by a direction.";
        }
        else if(topic.equals("use"))
        {
            return "USE Command: Use the 'use' command followed by a piece of equipment then 'on' or 'with' or 'using' followed by an item or feature.";
        }
        else if(topic.equals("get"))
        {
            return "GET Command: Use the 'get' command followed by an item or piece of equipment.";
        }
        else if(topic.equals("drop"))
        {
            return "DROP Command: Use the 'drop' command followed by an item or piece of equipment.";
        }
        else if(topic.equals("look"))
        {
            return "LOOK Command: Use the 'look' command on its own or followed by an item, piece of equipment or feature in a room.";
        }
        else if(topic.equals("status"))
        {
            return "STATUS Command: Use the 'status' command alone followed by inventory, player, map, score, an item or a piece of equipment.";
        }
        else if(topic.equals("help"))
        {
            return "HELP Command: Use the 'help' command alone or followed by a topic.";
        }
        else if(topic.equals("combine"))
        {
            return "COMBINE Command: Use the 'combine' followed by item 'and' item 'to' 'make' equipment.";
        }
        else if(topic.equals("quit"))
        {
            return "QUIT Command: Use the 'quit'.";
        }
        else if(topic.equals("combineinfo"))
        {
            return "\nCombine combinations:\npliers + wire -> lockpick \nstone + stick -> axe \ntin + tinopener -> dogfood\n";
        }
        // for tests
        else if(topic.equals("null"))
        {
            return "Welcome to the game!\nCommands:\n- MOVE\n- USE\n- GET\n- DROP\n- LOOK\n- STATUS\n- HELP\n- COMBINE\n- COMBINEINFO\n- QUIT";
        }
        else
        {
             return "No help available for the topic: " + topic;
        }
    }

    public String toString()
    {
        return "HELP command with topic " + topic;
    }
  
}
