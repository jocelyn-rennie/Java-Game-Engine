package org.uob.a2.utils;

import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.uob.a2.gameobjects.*;

/**
 * Utility class for parsing a game state from a file.
 * 
 * <p>
 * This class reads a structured text file to create a {@code GameState} object,
 * including the player, map, rooms, items, equipment, features, and exits.
 * </p>
 */

//Utility class for parsing a game state from a file.
public class GameStateFileParser 
{
    public static GameState parse(String file)
    {
        GameState gameState = new GameState();
        
        try(BufferedReader scanner = new BufferedReader(new FileReader(file))){

        /*Path myFile = Paths.get(file);
        
        InputStream input = new BufferedInputStream(Files.newInputStream(myFile));
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();*/


        //cant split arraylists
        String[] arraySplit1; //= {"",""};
        String[] arraySplit2; //= {"","","","","","","","",""};
        
        ArrayList<String> colonSplit = new ArrayList<String>();
        ArrayList<String> commaSplit = new ArrayList<String>();

        String object;
        boolean boolVal1 = false;
        boolean boolVal2 = false;
    
        Player player = new Player();
        Map map = new Map();
        Room room = new Room();
        //needs to be a feature argument 
        Feature container = new Container("","","",false);
        Feature feature = new Feature("","","",false);
        UseInformation useInformation = new UseInformation(false,"","","","");
        Equipment equipment = new Equipment("","","",false,useInformation); 
        Item item = new Item("","","",false);
        Exit exit = new Exit("","","","",false);
        Score score = new Score(0,0);

        //for combine
        String item1 = "";
        String item2 = "";   

        String startRoom = "";

        String line;

        while((line = scanner.readLine())!= null)
        {
            // gets rid of spaces
            line = line.trim();
            arraySplit1 = line.split(":");
            object = arraySplit1[0];
        
            switch(object)
            {
                case "player": 
                    player = new Player(arraySplit1[1]); 
                    break;
                case "map":
                    arraySplit2 = arraySplit1[1].split(",");
                    commaSplit = new ArrayList<>(Arrays.asList(arraySplit2));
                    if(commaSplit.size() > 1)
                    {
                        // have map id always after :
                        map = new Map(Integer.parseInt(commaSplit.get(1)), Integer.parseInt(commaSplit.get(2)), commaSplit.get(3).charAt(0),Integer.parseInt(commaSplit.get(4)),Integer.parseInt(commaSplit.get(5)), commaSplit.get(6));
                    }
                    else
                    {
                    map = new Map();
                    }
                    // saves start room
                    startRoom = commaSplit.get(0);
                    break;
                case "score":
                    arraySplit2 = arraySplit1[1].split(",");
                    commaSplit = new ArrayList<>(Arrays.asList(arraySplit2));
                    score = new Score(Integer.parseInt(commaSplit.get(0)),Integer.parseInt(commaSplit.get(1)));
                    player.setScoreObject(score);
                    break;
                case "room":
                    arraySplit2 = arraySplit1[1].split(",");
                    commaSplit = new ArrayList<>(Arrays.asList(arraySplit2));
                    boolVal1 = Boolean.valueOf(commaSplit.get(3));
                    room = new Room(commaSplit.get(0),commaSplit.get(1),commaSplit.get(2),boolVal1);
                    map.addRoom(room);
                    //sets current room once room created
                    if(room.getId().equals(startRoom))
                    {
                        map.setCurrentRoom(startRoom);
                    }
                    break;
                case "container":
                    arraySplit2 = arraySplit1[1].split(",");
                    commaSplit = new ArrayList<>(Arrays.asList(arraySplit2));
                    //room only takes features and container is a subclass
                    boolVal1 = Boolean.valueOf(commaSplit.get(3));
                    container = new Container(commaSplit.get(0),commaSplit.get(1),commaSplit.get(2),boolVal1);
                    room.addFeature(container); 
                    //System.out.println("container: " + container);
                    break;
                case "equipment":
                    arraySplit2 = arraySplit1[1].split(",");
                    commaSplit = new ArrayList<>(Arrays.asList(arraySplit2));
                    // this is a game object
                    boolVal1 = Boolean.valueOf(commaSplit.get(3));
                    boolVal2 = Boolean.valueOf(commaSplit.get(4));
                    // have different attribute lengths
                    if(commaSplit.size() == 11)
                    {
                        useInformation = new UseInformation(boolVal2,commaSplit.get(5),commaSplit.get(6),commaSplit.get(7),commaSplit.get(8));
                        item1 = commaSplit.get(9);
                        item2 = commaSplit.get(10);
                        equipment = new Equipment(commaSplit.get(0),commaSplit.get(1),commaSplit.get(2),boolVal1,useInformation,item1,item2);
                    }
                    // for tests
                    else if(commaSplit.size() == 8)
                    {
                        useInformation = new UseInformation(false,commaSplit.get(4),commaSplit.get(5),commaSplit.get(6),commaSplit.get(7));
                        equipment = new Equipment(commaSplit.get(0),commaSplit.get(1),commaSplit.get(2),boolVal1,useInformation);
                    }
                    else
                    {
                        useInformation = new UseInformation(boolVal2,commaSplit.get(5),commaSplit.get(6),commaSplit.get(7),commaSplit.get(8));
                        equipment = new Equipment(commaSplit.get(0),commaSplit.get(1),commaSplit.get(2),boolVal1,useInformation);
                    }
                    room.addEquipment(equipment); 
                    //System.out.println("equipment: " + equipment);
                    break;
                case "item":
                    arraySplit2 = arraySplit1[1].split(",");
                    commaSplit = new ArrayList<>(Arrays.asList(arraySplit2));
                    boolVal1 = Boolean.valueOf(commaSplit.get(3));
                    item = new Item(commaSplit.get(0),commaSplit.get(1),commaSplit.get(2),boolVal1);
                    room.addItem(item); 
                    //System.out.println("item: " + item);
                    break;
                case "feature":
                    arraySplit2 = arraySplit1[1].split(",");
                    commaSplit = new ArrayList<>(Arrays.asList(arraySplit2));
                    boolVal1 = Boolean.valueOf(commaSplit.get(3));
                    feature = new Feature(commaSplit.get(0),commaSplit.get(1),commaSplit.get(2),boolVal1);
                    room.addFeature(feature); break;
                case "exit":
                    arraySplit2 = arraySplit1[1].split(",");
                    commaSplit = new ArrayList<>(Arrays.asList(arraySplit2));
                    boolVal1 = Boolean.valueOf(commaSplit.get(4));
                    exit = new Exit(commaSplit.get(0),commaSplit.get(1),commaSplit.get(2),commaSplit.get(3),boolVal1);
                    room.addExit(exit); 
                    //System.out.println("exit: " + exit);
                    break;
                default:
                    map.setCurrentRoom(null);
                    break;
            }

        }
            gameState = new GameState(map,player);
        }

        // needed for file reading
        catch(Exception e)
        {
            e.printStackTrace();
        }


        return gameState;
            
    }
  
}
