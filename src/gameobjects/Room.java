package org.uob.a2.gameobjects;

import java.util.ArrayList;

/**
 * Represents a room in the game, which is a type of {@code GameObject}.
 * 
 * <p>
 * Rooms can have items, equipment, features, and exits. They also manage navigation
 * and interactions within the game world.
 * </p>
 */
public class Room extends GameObject {

    ArrayList<Item> items;
    ArrayList<Equipment> equipment;
    ArrayList<Feature> features;
    ArrayList<Exit> exits;
    ArrayList<GameObject> gameObjects;
    
    public Room()
    {
        this.items = new ArrayList<Item>();
        this.equipment = new ArrayList<Equipment>();
        this.features = new ArrayList<Feature>();
        this.exits = new ArrayList<Exit>();
        this.gameObjects = new ArrayList<GameObject>();
    }

    public Room(String id, String name, String description, boolean hidden)
    {
        super(id, name, description, hidden);
        this.items = new ArrayList<Item>();
        this.equipment = new ArrayList<Equipment>();
        this.features = new ArrayList<Feature>();
        this.exits = new ArrayList<Exit>();
        this.gameObjects = new ArrayList<GameObject>();
    }

    public void addEquipment(Equipment equipment)
    {
        this.equipment.add(equipment);
    }

    public void addExit(Exit exit)
    {
        exits.add(exit);
    }

    public void addFeature(Feature feature)
    {
        features.add(feature);
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    //returns all rooms? but it has GameObject type so do they need to be initialised as game objects
    // still need to do, 
    public ArrayList<GameObject> getAll()
    {
        return gameObjects;
    }

    public String getDescription()
    {
        return description;
    }

    public Equipment getEquipment(String id)
    {
        for (Equipment e : this.equipment) 
        {
            if(e.getId().equals(id))
            {
                return e;
            }
        }

        throw new IllegalArgumentException("Equipment with id " + id + " not found.");
    }

    public Equipment getEquipmentByName(String name)
    {
        for (Equipment e : this.equipment) 
        {
            if(e.getName().equals(name))
            {
                return e;
            }
        }

        throw new IllegalArgumentException("Equipment with name " + name + " not found.");
    }

    public ArrayList<Equipment> getEquipments()
    {
        return equipment;
    }

    public Exit getExit(String id)
    {
        for (Exit e : this.exits) 
        {
            if(e.getId().equals(id))
            {
                return e;
            }
        }

        throw new IllegalArgumentException("Exit with id " + id + " not found.");
    }

    public ArrayList<Exit> getExits()
    {
        return exits;
    }

    public Feature getFeature(String id)
    {
        for (Feature e : this.features) 
        {
            if(e.getId().equals(id))
            {
                return e;
            }
        }

        return null;
    }

    public Feature getFeatureByName(String name)
    {
        for (Feature e : this.features) 
        {
            if(e.getName().equals(name))
            {
                return e;
            }
        }

        throw new IllegalArgumentException("Feature with name " + name + " not found.");
    }

    public ArrayList<Feature> getFeatures()
    {
        return features;
    }

    public Item getItem(String id)
    {
        for (Item e : this.items) 
        {
            if(e.getId().equals(id))
            {
                return e;
            }
        }

        throw new IllegalArgumentException("Item with id " + id + " not found.");
    }

    public Item getItemByName(String name)
    {
        for (Item e : this.items) 
        {
            if(e.getName().equals(name))
            {
                return e;
            }
        }

        throw new IllegalArgumentException("Item with name" + name + " not found.");
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public String getName()
    {
        return name;
    }

    public boolean hasEquipment(String name)
    {
        boolean returnVal = false;
        for (Equipment e : this.equipment) 
        {
            if(e.getName().equals(name))
            {
                returnVal = true;
            }
        }

        return returnVal;
    }

    public boolean hasItem(String itemName)
    {
        boolean returnVal = false;
        
        for (Item e : this.items) 
        {
            if(e.getName().equals(itemName))
            {
                returnVal = true;
            }
        }

        return returnVal;
    }

    //to check for feature (my addition)
    public boolean hasFeature(String featureName)
    {
        boolean returnVal = false;
        
        for (Feature e : this.features) 
        {
            if(e.getName().equals(featureName))
            {
                returnVal = true;
            }
        }

        return returnVal;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns a string representation of the room, including its contents and description.
     *
     * @return a string describing the room
     */
    
    @Override
    public String toString() {
        String out = "[" + id + "] Room: " + name + "\nDescription: " + description + "\nIn the room there is: ";
        for (Item i : this.items) {
            out += i + "\n";
        }
        for (Equipment e : this.equipment) {
            out += e + "\n";
        }
        for (Feature f : this.features) {
            out += f + "\n";
        }
        for (Exit e : this.exits) {
            out += e + "\n";
        }
        return out + "\n";
    }
}
