package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

public class Combine extends Command
{
    String item1;
    String item2;
    String equipment;
    
    public Combine(String item1, String item2, String equipment)
    {
        this.item1 = item1;
        this.item2 = item2;
        this.equipment = equipment;
    }

    public String execute(GameState gameState)
    {
        //need equipment as combine object
        /*UseInformation useInformation = new UseInformation(false,"","","","");
        Item item1test = new Item("","","",false);
        Item item2test = new Item("","","",false);
        Equipment combineObj = new Equipment("","","",true,useInformation,item1test,item2test);
        combineObj = gameState.getMap().getCurrentRoom().getEquipmentByName(equipment);*/
        
        //both not in inventory
        if(!(gameState.getPlayer().hasItem(item1) && gameState.getPlayer().hasItem(item2)))
        {
            return "You don't have both items you need.";
        }
        // if equipment in room
        else if(gameState.getMap().getCurrentRoom().hasEquipment(equipment) == false)
        {
            return "No equipment found.";
        }
        else if(!(gameState.getMap().getCurrentRoom().getEquipmentByName(equipment).getItem1().equals(item1)))
        {
            System.out.println(gameState.getMap().getCurrentRoom().getEquipmentByName(equipment).getItem1());
            return "Item 1 not compatible.";
        }
        else if(!(gameState.getMap().getCurrentRoom().getEquipmentByName(equipment).getItem2().equals(item2)))
        {
            return "Item 2 not compatible.";
        }
        else
        {
            //reveals equipment
            gameState.getMap().getCurrentRoom().getEquipmentByName(equipment).setHidden(false);
            //updates score
            if(gameState.getPlayer().getScoreObject().getPuzzleScore() == 0)
            {
            }
            else
            {
                gameState.getPlayer().getScoreObject().puzzleCompleted();
            }

            return "You combined " + item1 + " and " + item2 + ", " + equipment + " created.";
        }
    }

}