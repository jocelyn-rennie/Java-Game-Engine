package org.uob.a2.parser;

import java.util.ArrayList;

import org.uob.a2.commands.*;

//need to change tostring
import org.uob.a2.commands.Use;

/**
 * The {@code Parser} class processes a list of tokens and converts them into {@code Command} objects
 * that can be executed by the game.
 * 
 * <p>
 * The parser identifies the type of command from the tokens and creates the appropriate command object.
 * If the command is invalid or incomplete, a {@code CommandErrorException} is thrown.
 * </p>
 */
public class Parser 
{
    
    public Parser(){}

    public Command parse(ArrayList<Token> tokens) throws CommandErrorException
    {
        //CommandErrorException error;
        Command use = new Use("","");
        Command get = new Get("");
        Command drop = new Drop("");
        Command look = new Look("");
        Command status = new Status("");
        Command help = new Help("");
        Command quit = new Quit();
        Command move = new Move("");
        Command combine = new Combine("","","");

        String testString = "";
        
        // not needed
        /*
        for (Token e : tokens)
        {
            if(e.getTokenType() == TokenType.valueOf("ERROR"))
            {
                    //throw new CommandErrorException("Invalid command");
                
            }
        }*/

        if(tokens.get(0).getTokenType() == TokenType.USE)
        {
            if(tokens.size()== 5 || tokens.size()== 4 )
            {
            if(tokens.get(1).getTokenType() == TokenType.valueOf("VAR"))
            {   
                if(tokens.get(2).getTokenType() == TokenType.valueOf("PREPOSITION"))
                {
                    if(tokens.get(3).getTokenType() == TokenType.valueOf("VAR"))
                    {
                        use = new Use(tokens.get(1).getValue(),tokens.get(3).getValue());
                        //wasnt working?
                        return use;
                    }
                    else
                    {
                       throw new CommandErrorException("Invalid command");
                    }
                }
                else
                {
                    throw new CommandErrorException("Invalid command");
                }
            }
            else
            {
                throw new CommandErrorException("Invalid command");
            }
            }
            else
            {
                throw new CommandErrorException("Invalid command");
            }
        }
        else if(tokens.get(0).getTokenType() == TokenType.GET)
        {
            if(tokens.size() > 1)
            {
                if(tokens.get(1).getTokenType() == TokenType.valueOf("VAR"))
                {
                    get = new Get(tokens.get(1).getValue()); 
                    return get;
                }
                else
                {
                    throw new CommandErrorException("Invalid command");
                }
            }
            else
            {
                throw new CommandErrorException("Invalid command");
            }
        }
        else if(tokens.get(0).getTokenType() == TokenType.DROP)
        {
            if(tokens.size() > 1)
            {
                if(tokens.get(1).getTokenType() == TokenType.valueOf("VAR"))
                {
                    drop = new Drop(tokens.get(1).getValue()); 
                    return drop;
                }
                else
                {
                    throw new CommandErrorException("Invalid command");
                }
            }
            else
            {
                throw new CommandErrorException("Invalid command");
            }
        }
        else if(tokens.get(0).getTokenType() == TokenType.LOOK)
        {
            if(tokens.size() > 1)
            {
                if(tokens.get(1).getTokenType() == TokenType.valueOf("VAR"))
                {
                    look = new Look(tokens.get(1).getValue());
                    return look;
                }
                else
                {
                    throw new CommandErrorException("Invalid command");
                }
            }
            else
            {
                throw new CommandErrorException("Invalid command");
            }
        }
        else if(tokens.get(0).getTokenType() == TokenType.STATUS)
        {
            if(tokens.size() > 1)
            {
                if(tokens.get(1).getTokenType() == TokenType.valueOf("VAR"))
                {
                    status = new Status(tokens.get(1).getValue());
                    return status;
                }
                else
                {
                    throw new CommandErrorException("Invalid command");
                }
            }
            else
            {
                throw new CommandErrorException("Invalid command");
            }
        }
        else if(tokens.get(0).getTokenType() == TokenType.HELP)
        {
            //can give general info
            if(tokens.size() == 2 || tokens.size() == 1)
            {
                
                help = new Help("null");
                return help;
            }
            else if(tokens.size() == 3)
            {
                help = new Help(tokens.get(1).getValue());
                return help;
            }
            else
            {
                throw new CommandErrorException("Invalid command");
            }

        }
        else if(tokens.get(0).getTokenType() == TokenType.QUIT)
        {
            if(tokens.size() == 2)
            {
                quit = new Quit();
                return quit;
            }
            else
            {
                throw new CommandErrorException("Invalid command");
            }
        }
        else if(tokens.get(0).getTokenType() == TokenType.MOVE)
        {
            if(tokens.size() > 1)
            {
                if(tokens.get(1).getTokenType() == TokenType.valueOf("VAR"))
                {
                    move = new Move(tokens.get(1).getValue());
                    return move;
                }
                else
                {
                    throw new CommandErrorException("Invalid command");
                }
            }
            else
            {
              throw new CommandErrorException("Invalid command"); 
            }
        }
        else if(tokens.get(0).getTokenType() == TokenType.COMBINE)
        {
            if(tokens.size()==8)
            {
                if(tokens.get(1).getTokenType() == TokenType.VAR)
                {
                    if(tokens.get(2).getTokenType() == TokenType.PREPOSITION)
                    {
                        if(tokens.get(3).getTokenType() == TokenType.VAR)
                        {
                            if(tokens.get(4).getTokenType() == TokenType.PREPOSITION)
                            {
                                if(tokens.get(5).getTokenType() == TokenType.PREPOSITION)
                                {
                                    if(tokens.get(6).getTokenType() == TokenType.VAR)
                                    {
                                        combine = new Combine(tokens.get(1).getValue(),tokens.get(3).getValue(),tokens.get(6).getValue());
                                        return combine;
                                    }
                                    else
                                    {
                                        throw new CommandErrorException("Invalid command, last token not var");
                                    }
                                }
                                else
                                {
                                    throw new CommandErrorException("Invalid command, not preposition2");
                                }
                            }
                            else
                            {
                                throw new CommandErrorException("Invalid command, not preposition1");
                            }
                        }
                        else
                        {
                            throw new CommandErrorException("Invalid command, not var2");
                        }
                    }
                    else
                    {
                        throw new CommandErrorException("Invalid command, not and");
                    }
                }
                else
                {
                    throw new CommandErrorException("Invalid command, not var1");
                }
            }
            else
            {
               throw new CommandErrorException("Invalid command, not right size combine");
            }
        }
        else if(tokens.get(0).getTokenType() == TokenType.VAR)
        {
            throw new CommandErrorException("Invalid command.");
        }
        else
        {
            throw new CommandErrorException("No tokens found in input.");
        }

        //throw new CommandErrorException("No tokens found in input.");
        //Throws: CommandErrorException - if the command cannot be parsed or is invalid
 
    }
}