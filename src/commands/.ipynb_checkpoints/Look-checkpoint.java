package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the look command, allowing the player to examine various elements of the game world.
 * 
 * <p>
 * The look command can provide details about the current room, its exits, features, or specific items and equipment.
 * Hidden objects are not included in the output unless explicitly revealed.
 * </p>
 */
public class Look extends Command 
{
    protected String target;

    public Look(String target)
    {
        super();
        this.commandType = CommandType.valueOf("LOOK");
        this.target = target;
    }

    public String execute(GameState gameState)
    {

        //needed for specific item look
        Item itemObject = new Item("","","",false);
            
            for (Item e : gameState.getMap().getCurrentRoom().getItems()) 
            {
                if(e.getId().equals(target))
                {
                    itemObject = e;
                }
            }

        //could maybe search items etc using Arraylists
        if(target.equals("room"))
        {
            String output = "";
            // room desc
            output = gameState.getMap().getCurrentRoom().getDescription() + "\n";
            
            // adds item descs
            for (Item e : gameState.getMap().getCurrentRoom().getItems()) 
            {
                if(e.getHidden() == false)
                {
                    output = output + e.getDescription() + "\n";
                }
            }

            // adds equipment
            for (Equipment e : gameState.getMap().getCurrentRoom().getEquipments()) 
            {
                if(e.getHidden() == false)
                {
                    output = output + e.getDescription() + "\n";
                }
            }
            // adds features
            for (Feature e : gameState.getMap().getCurrentRoom().getFeatures()) 
            {
                if(e.getHidden() == false)
                {
                    output = output + e.getDescription() + "\n";
                }
            }

            // adds exits (my own, easier to tell where to go)
            for (Exit e : gameState.getMap().getCurrentRoom().getExits()) 
            {
                if(e.getHidden() == false)
                {
                    output = output + e.getDescription() + "\n";
                }
            }

            return output;
        }

        if(target.equals("exits"))
        {
            String exitOutput = "";
            
            for (Exit e : gameState.getMap().getCurrentRoom().getExits()) 
            {
                if(e.getHidden() == false)
                {
                    exitOutput = exitOutput + e.getDescription() + "\n";
                }
            }

            return  "The available exits are: \n" + exitOutput;
        }

        if(target == "features")
        {
            String featureOutput = null;
            
            for (Feature e : gameState.getMap().getCurrentRoom().getFeatures()) 
            {
                if(e.getHidden() == false)
                {
                    featureOutput = featureOutput + e.getDescription() + "\n";
                }
            }

            return "You also see:\n" + featureOutput;
        }

        //look at specific item but uses item id
        if(gameState.getMap().getCurrentRoom().hasItem(itemObject.getName()) == true)
        {

            // test wants you to use item id
            
            if(itemObject.getHidden() == false)
            {
                return itemObject.getDescription();
            }
            else
            {
                return "";
            }

        }

        //look at specific equipment but uses name
        if(gameState.getMap().getCurrentRoom().hasEquipment(target) == true)
        {
            String equipmentOutput = null;
            
            if(gameState.getMap().getCurrentRoom().getEquipmentByName(target).getHidden() == false)
            {
                equipmentOutput = gameState.getMap().getCurrentRoom().getEquipmentByName(target).getDescription();
            }

            return equipmentOutput;
        }

        //look at specific feature but uses name
        if(gameState.getMap().getCurrentRoom().hasFeature(target) == true)
        {
            String featureOutput = null;
            
            if(gameState.getMap().getCurrentRoom().getFeatureByName(target).getHidden() == false)
            {
                featureOutput = gameState.getMap().getCurrentRoom().getFeatureByName(target).getDescription();
            }

            return featureOutput;
        }           
        else
        {
            return null;
        }
    }

    public String toString()
    {
        return "Look command of type " + commandType + " with target " + target;
    }

   
}
