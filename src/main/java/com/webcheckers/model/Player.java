package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;

import java.util.ArrayList;


/**
 * Holds the unique player name.
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

    //A list of all the players piece positions
    private ArrayList<Position> posList;
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
        setInGame();
    }

    public void assignPos(Board b, MasterEnum.Color color){
        if(posList == null)
            posList = new ArrayList<>();
        for(int i = 0; i < b.board.length; i++){
            for(int j = 0; j < b.board[0].length; j++){
                if(b.board[i][j].getPiece() != null &&
                    b.board[i][j].getPiece().getColor() == color)
                    posList.add(new Position(i, j));
            }
        }
    }

    public ArrayList<Position> getPosList(){
        return posList;
    }

    public boolean movePiece(Move m){
        for(int i = 0; i < posList.size(); i++)
            if(posList.get(i).equals(m.getStart())) {
                posList.remove(i);
                posList.add(new Position(m.getEnd().getRow(), m.getEnd().getCol()));
                return true;
            }
        return false;
    }

    public Position removePiece(Position p) {
        for (int i = 0; i < posList.size(); i++)
            if (p.equals(posList.get(i)))
                return posList.remove(i);
        return null;
    }

    /**
     * Sets the player to be in the game
     */
    private void setInGame()
    {
        this.inGame = true;
    }

    /**
     * Lets the player leave the game
     */
    public void leaveGame(){
        this.inGame = false;
        this.game = null;
    }

    /**
     * returns the game object this player is in (for testing purposes)
     * @return Game
     */
    public Game getGame(){return this.game;}

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
}
