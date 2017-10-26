package com.webcheckers.model;

/**
 * Holds the unique player name
 *
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 * @author <a href='mailto:gep2494@rit.edu'>George-Edward Pinal</a>
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 */
public class Player {

    /**
     * Variables to keep track of the name, is the player is in the game, and the game
     */
    private boolean inGame;
    private String name;
    private Game game;

    /**
     * Creates the player
     * @param name
        *Name value
     */
    public Player(String name) {
        this.name = name;
        this.inGame = false;
    }


    /**
     * Sets the player name
     * @param name
     *  Player name
     */
    public void setName(String name)
    {
        if(this.name == null)
            this.name = name;
        else
            throw new RuntimeException("Player already has name");
    }

    /**
     * Returns the player name
     * @return String of the name
     */
    public String getName(){return name;}

    /**
     * Checks if the player is already in a game
     * @return True if they are in the game false if not
     */
    public boolean isInGame(){
        return this.inGame;
    }

    public void assignGame(Game game){
        this.game = game;
        inGame = true;
    }


    /**
     * Sets the player to be in the game
     */
    public void setInGame()
    {
        this.inGame = true;
    }

    /**
     * Lets the player leave the game
     */
    public void leaveGame(){
        this.inGame = false;
    }

    @Override
    /**
     * Checks of the players are the same
     */
    public boolean equals(Object o){
        if(!(o instanceof Player))
            return false;
        return o.hashCode() == this.hashCode();
    }

    @Override
    /**
     * Gets the hashcode from the players name
     */
    public int hashCode(){
        return this.getName().hashCode();
    }

    @Override
    /**
     * toString of the players name
     */
    public String toString(){
        return "<a href='/game' onclick=\"playerLobby.pullByHashCode("+hashCode()+")\">" +getName() + "</a>" + "\n";
    }
}
