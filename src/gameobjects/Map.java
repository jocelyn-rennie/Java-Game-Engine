package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.nio.file.*;

import org.uob.a2.utils.*;

/**
 * Represents the game map, which consists of a collection of rooms and the current room the player is in.
 * 
 * <p>
 * The map allows for navigation between rooms, adding new rooms, and managing the current room context.
 * </p>
 */
public class Map {

    Room currentRoom;
    ArrayList<Room> rooms;
    int width;
    int height;
    char playerSymbol;
    int xPlayerPos;
    int yPlayerPos;
    String endRoomId;
    
    char[][] mapArray;
    
    public Map()
    {
        this.currentRoom = null;
        this.rooms = new ArrayList<Room>();
        xPlayerPos = 0;
        yPlayerPos = 0;     
    }

    //to customise game more
    //first room is id next to map
    public Map(int width, int height, char playerSymbol, int xPlayerPos, int yPlayerPos, String endRoomId)
    {
        this.currentRoom = null;
        this.rooms = new ArrayList<Room>();
        this.width = width;
        this.height = height;
        this.playerSymbol = playerSymbol;
        this.xPlayerPos = xPlayerPos;
        this.yPlayerPos = yPlayerPos;
        this.endRoomId = endRoomId;
        this.mapArray = new char[width][height];
        
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                if((x == xPlayerPos) && (y == yPlayerPos))
                {
                    // shows player on screen
                    mapArray[x][y] = playerSymbol;
                }
                else
                {
                    mapArray[x][y] = '.';
                }
            }
        }
    }

    public void addRoom(Room room)
    {
        rooms.add(room);
    }

    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    // sets current room as room object with that id
    public void setCurrentRoom(String roomId)
    {
        for (Room e : this.rooms) 
        {
            if(e.getId().equals(roomId))
            {
                currentRoom = e;
            }
        }
    }

    //displays map
    public String displayMap()
    {
        String result = "";
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                result = result + mapArray[x][y];
            }
            
            result = result + "\n";
        }
        
        return result;
    }

    public void updateMap()
    {
       for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                if((x == xPlayerPos) && (y == yPlayerPos))
                {
                    // shows player on screen
                    mapArray[x][y] = playerSymbol;
                }
                else
                {
                    mapArray[x][y] = '.';
                }
            }
        }
    }

    public int getXPlayerPos()
    {
        return xPlayerPos;
    }

    public int getYPlayerPos()
    {
        return yPlayerPos;
    }

    public void setXPlayerPos(int num)
    {
        this.xPlayerPos = num;
    }
    
    public void setYPlayerPos(int num)
    {
        this.yPlayerPos = num;
    }

    public String getEndRoomId()
    {
        return endRoomId;
    }

    public char getPlayerSymbol()
    {
        return playerSymbol;
    }

        /**
     * Returns a string representation of the map, including all rooms.
     *
     * @return a string describing the map and its rooms
     */
    
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Map:\n");
        for (Room r : this.rooms) {
            out.append(r.toString()).append("\n");
        }
        return out.toString();
    }
}

