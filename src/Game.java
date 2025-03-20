package org.uob.a2;

import java.util.Scanner;

import org.uob.a2.commands.*;
import org.uob.a2.gameobjects.*;
import org.uob.a2.parser.*;
import org.uob.a2.utils.*;

//for tests
import java.io.*;

/**
 * Main class for the game application. Handles game setup, input parsing, and game execution.
 * 
 * <p>
 * This class initializes the game state, reads user input, processes commands, and maintains the game loop.
 * </p>
 */
public class Game 
{

    //if user wants to leave
    public static boolean x;
    public static Stopwatch stopwatch;
    
    public static void main(String[] args)
    {
        // used to collect user input
        Scanner inputDevice = new Scanner(System.in);
        String input = "";
        x = false;

        // needed objects
        Tokeniser tokeniser = new Tokeniser();
        GameState gameState = new GameState();
        Parser parser = new Parser();


        gameState = GameStateFileParser.parse("/jupyter/work/OOP/Assignment2/jxr416/src/org/uob/a2/Game.txt");

        //creates stopwatch
        stopwatch = new Stopwatch();

        // Start the stopwatch
        stopwatch.start();

        //intro to game
        System.out.println("Welcome to the game!\nYou have been kidnapped by the old lady next door for always being too loud. She lives in a huge old house filled with all sorts.\n");
        System.out.println("You are represented by " + gameState.getMap().getPlayerSymbol() + " on the map.");
        System.out.println("\nMax score: 290 \nThe quicker you complete the game the higher your score, keep an eye on the timer!\n");
        
        while(x!=true)
        {
            try{
            System.out.println("Timer: " + stopwatch.getElapsedTimeSeconds() + "\n");
            System.out.println("Enter a command: ");
            
            //takes input
            input = inputDevice.nextLine();
            
            // sanitises
            input = tokeniser.sanitise(input);
            
            //tokenises input
            tokeniser.tokenise(input);
            
            //parses tokens
            //executes command
            //outputs what happened
            System.out.println(parser.parse(tokeniser.getTokens()).execute(gameState)); 

            }
            catch(CommandErrorException e)
            {
                System.out.println(e.getMessage());
            }
        }

        stopwatch.stop();
    }
}
