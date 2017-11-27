package com.webcheckers.model;

import com.webcheckers.appl.Game;
import com.webcheckers.appl.MasterEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Holds the unique player name.
 *
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 * @author <a href='mailto:gep2494@rit.edu'>George-Edward Pinal</a>
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class Player {

    //Variables to keep track of the name, is the player is in the game, and the game
    private boolean inGame;
    private String name;
    private Game game = null;

    //A list of all the players piece positions
    private ArrayList<Position> posList;

    //A map of the games a player has played
    private Map<String, Game> games;

    private int wins = 0;
    private int gamesPlayed = 0;

    /**
     * Creates the player
     * @param name Name value
     */
    public Player(String name) {
        this.name = name;
        this.inGame = false;
        this.posList = new ArrayList<>();
        this.games = new HashMap<>();
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

    /**
     * Assigns player to game and calls setInGame()
     * @param game the game player is assigned to
     */
    public void assignGame(Game game){
        if(this.game == null) {
            this.game = game;
            setInGame();
        } else
            throw new RuntimeException("player already in a game");
    }

    /**
     * Create a position list of all players pieces
     * @param b The board used to create positions from
     * @param color The color of pieces being looked for
     */
    public void assignPos(Board b, MasterEnum.Color color){

        //Find all pieces with matching color and add their positions to the posList
        for(int i = 0; i < BoardView.BOARD_LENGTH; i++){
            for(int j = 0; j < BoardView.BOARD_LENGTH; j++){
                if(b.getPieceAt(i,j) != null &&
                    b.getPieceAt(i,j).getColor() == color)
                    posList.add(new Position(i, j));
            }
        }
    }

    /**
     *
     * @return the position list
     */
    public ArrayList<Position> getPosList(){
        return posList;
    }

    /**
     * Moves a position based on a specified move
     * @param m The move that specifies the position change
     * @return If the position was found and changed
     */
    public boolean movePiece(Move m){
        //Find the position in posList that is equal to m.start
        for(int i = 0; i < posList.size(); i++)
            if(posList.get(i).equals(m.getStart())) {
                //Replace the position found with m.end
                posList.remove(i);
                posList.add(m.getEnd());
                return true;
            }
        return false;
    }

    /**
     * Removes a given position
     * @param p The position to be removed
     */
    public void removePiece(Position p) {
        //Find the position in posLis with the same values as p
        for (int i = 0; i < posList.size(); i++)
            if (posList.get(i).equals(p)) {
                //remove the position when found
                posList.remove(i);
                break;
            }
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
        posList.clear();
        gamesPlayed++;
    }

    /**
     * returns the game object this player is in (for testing purposes)
     * @return Game
     */
    public Game getGame(){return this.game;}

    public Game getGame(String name){
        return games.get(name);
    }

    public boolean won(String name){
        Objects.requireNonNull(games.get(name));
        return games.get(name).getWinner().equals(getName());
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

    /**
     * Saves a game that is passed by the parameters
     * Should be changed in the future if another data type is used
     * @param name the name of the saved game
     *             (if null is given then assign it as "Game (moves.size()+1)" )
     * @param game the game being saved
     * @return the saved game from the collection type
     */
    public Game saveGame(String name, Game game){
        //if name is null then give a default name
        if(name == null || name.length()==0) {
            String temp = "Game " + (games.size() + 1);
            games.put(temp, game);
            return games.get(temp);
        }
        //if the game is name is already assigned return null
        else if(games.get(name) != null)
            return null;
        //just add default name is name is not null and not already in use
        games.put(name, game);
        return games.get(name);
    }

    public Game deleteGame(String name){
        return games.remove(name);
    }

    /**
     * returns the number of saved games
     * This is different then the number of games won
     * @return number of games saved
     */
    public int gamesSaved(){return games.size();}

    /**
     * Returns iterator or the games keys, this allows the writing out of games
     * @return the names of all the players saved games
     */
    public Iterator<String> gameIterator(){
        return games.keySet().iterator();
    }

    @Override
    /**
     * Gets the hashcode from the players name
     */
    public int hashCode(){
        return this.getName().hashCode();
    }
}
