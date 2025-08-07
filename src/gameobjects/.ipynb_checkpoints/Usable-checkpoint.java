package org.uob.a2.gameobjects;

/**
 * Represents an interface for objects that can be used within the game.
 * 
 * <p>
 * Objects implementing this interface must define methods to manage their use
 * information and provide their name.
 * </p>
 */
public interface Usable 
{
    //abstract methods so cannot have body
    public String getName();


    public UseInformation getUseInformation();


    public void setUseInformation(UseInformation useInfo);


}
