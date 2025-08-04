package org.uob.a2.gameobjects;

import java.util.ArrayList;

/**
 * Represents the player in the game, including their name, inventory, and equipment.
 * 
 * <p>
 * The player can carry items and equipment, interact with the game world, and perform
 * actions using their inventory or equipment.
 * </p>
 */
public class Player {

    String name;
    ArrayList<Item> inventory;
    ArrayList<Equipment> equipment;
    Score score = new Score(0,0);

    public Player()
    {
        this.inventory = new ArrayList<Item>();
        this.equipment = new ArrayList<Equipment>();
    }

    public Player(String name)
    {
        this.name = name;
        this.inventory = new ArrayList<Item>();
        this.equipment = new ArrayList<Equipment>();
    }

    public void addEquipment(Equipment equipment)
    {
        this.equipment.add(equipment);
    }

    public void addItem(Item item)
    {
        inventory.add(item);
    }

    public ArrayList<Equipment> getEquipment()
    {
        return equipment;
    }

    public Equipment getEquipment(String equipmentName)
    {
        for (Equipment e : this.equipment) 
        {
            if(e.getName().equals(equipmentName))
            {
                return e;
            }
        }

        throw new IllegalArgumentException("Equipment with name " + equipmentName + " not found.");
    }

    public ArrayList<Item> getInventory()
    {
        return inventory;
    }
    
    public Item getItem(String itemName)
    {
        for (Item e : this.inventory) 
        {
            if(e.getName().equals(itemName))
            {
                return e;
            }
        }

        throw new IllegalArgumentException("Item with name " + itemName + " not found.");
    }

    public String getName()
    {
        return name;
    }

    public boolean hasEquipment(String equipmentName)
    {
        boolean returnVal = false;
        for (Equipment e : this.equipment) 
        {
            if(e.getName().equals(equipmentName))
            {
                returnVal = true;
            }
        }

        return returnVal;
    }

    public boolean hasItem(String itemName)
    {
        boolean returnVal = false;
        for (Item e : this.inventory) 
        {
            if(e.getName().equals(itemName))
            {
                returnVal = true;
            }
        }

        return returnVal;
    }

    //returns score object
    public Score getScoreObject()
    {
        return score;
    }
    
    //sets score object
    public void setScoreObject(Score score)
    {
        this.score = score;
    }

        /**
     * Returns a string representation of the player's current state, including their name,
     * inventory, and equipment descriptions.
     *
     * @return a string describing the player, their inventory, and equipment
     */
    
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Player Name: " + this.name + "\nInventory:\n");
        for (Item i : this.inventory) {
            out.append("- ").append(i.getDescription()).append("\n");
        }
        out.append("Equipment:\n");
        for (Equipment e : this.equipment) {
            out.append("- ").append(e.getDescription()).append("\n");
        }
        return out.toString();
    }
}
