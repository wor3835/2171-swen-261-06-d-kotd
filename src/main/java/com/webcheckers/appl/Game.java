package com.webcheckers.appl;


import com.webcheckers.appl.MasterEnum;
import com.webcheckers.model.Board;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;


/**
 * This class is held at server level, because of this p1 is ALWAYS the challenging player
 * Thus p2 is ALWAYS the challenged player
 *
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 */
public class Game {

    /**
     * Variables to keep track of the players and the list of spectators
     */

    //The player who initiated the game
    private Player p1;
    //The board for p1
    private Board b1;
    //The player who got challenged to a game
    private Player p2;
    //The board for p2
    private Board b2;
    //A list of spectators for a game
    private List<Player> spectators = new ArrayList<>();
    //A list move moves that houses all the moves in a game
    private List<Move> movesList = new ArrayList<>();
    //The player who resigned
    private String resigner;
    //The player who won
    private String winner;
    //The status of the game either OVER, RESIGN or SIGNOUT when over or PLAYING when not
    private MasterEnum.GameStatus status;
    //The active color
    private MasterEnum.Color activeColor;

    /**
     * Assigns the players to the game
     * @param p1 The challenging player
     * @param p2 The challenged player
     * @return True if they are added to the game false if not
     */
    public boolean applyGame(Player p1, Player p2){
        //Check to see if p2 is in a game
        status = MasterEnum.GameStatus.PLAYING;
        if(p2.isInGame()) {
            return false;
        }
        //If not start assigning players
        this.p1 = p1;
        this.p2 = p2;

        //Assign the game object to each player
        p1.assignGame(this);
        p2.assignGame(this);

        //Set active color to RED
        activeColor = MasterEnum.Color.RED;

        return true;
    }

    /**
     * Assigns boards
     * @param b1 the board for p1
     * @param b2 the board for p2
     */
    public void applyBoard(Board b1, Board b2) {
        this.b1 = b1;
        //Create p1's posList
        p1.assignPos(b1, MasterEnum.Color.RED);
        this.b2 = b2;
        //Create p2's posList
        p2.assignPos(b2, MasterEnum.Color.WHITE);
    }

    /**
     * Assigns only a single board
     * @param b the board to be assigned
     */
    public void applyBoard(Board b){this.b1 = b;}

    /**
     * Adds a spectator to the game
     * @param p The player to be added as a spectator
     */
    public void addSpectator(Player p){
        spectators.add(p);
    }

    /**
     * Removes a spectator from the game
     * @param p The player to be removed
     */
    public void removeSpecator(Player p){
        spectators.remove(p);
    }

    /**
     * Switch the active color of the game
     */
    public void switchActive(){
        switch(activeColor){
            case RED:
                activeColor = MasterEnum.Color.WHITE;
                break;
            case WHITE:
                activeColor = MasterEnum.Color.RED;
                break;
        }

    }

    /**
     * ends the game by making each player leave
     */
    public void endGame(MasterEnum.GameStatus status, String name){
        if(this.status!= MasterEnum.GameStatus.PLAYING)
            throw new RuntimeException("Game already over");
        //Forces players to leave game
        p1.leaveGame();
        p2.leaveGame();
        //Makes boards null
        b1 = null;
        b2 = null;
        //Assign the status
        this.status = status;
        //Assign the name of the resigner(if no resign then null)
        this.resigner = name;
    }

    /**
     * gest the active player's color
     * @return active color
     */
    public MasterEnum.Color getActiveColor() {return activeColor;}

    /**
     * @return player 1
     */
    public Player getP1() {return p1;}

    /**
     * @return player 2
     */
    public Player getP2(){return p2;}

    /**
     * @return board 1
     */
    public Board getB1() {return b1;}

    /**
     * @return board 2
     */
    public Board getB2() {return b2;}

    public Board getB(){return b1;}

    /**
     * Add move to Game's moveList
     * @param m move to add
     */
    public void addMove(Move m) {movesList.add(m);}

    /**
     * Get a move at location i
     * @param i The index for the move to get
     * @return The move at location i
     */
    public Move getMove(int i){return movesList.get(i);}

    /**
     * @return player 1 color
     */
    public MasterEnum.Color getP1color(){return MasterEnum.Color.RED;}

    /**
     * @return player 2 color
     */
    public MasterEnum.Color getP2color(){return MasterEnum.Color.WHITE;}

    /**
     * Gets the name of the winner
     * @return The winner's name
     */
    public String getWinner() {return winner;}

    /**
     * Set the winner to a name
     */
    public void setWinner(String player){
        if(winner!=null)
            throw new RuntimeException("Winner already assigned");
        this.winner = player;
    }

    /**
     * Gets the number of moves made in a game
     * @return The size of the moves list
     */
    public int movesListSize(){return movesList.size();}

    /**
     * checks to see if gave is over
     */
    public boolean isGameOver() {return status!=MasterEnum.GameStatus.PLAYING;}

    /**
     * returns the game status
     */
    public MasterEnum.GameStatus getStatus(){return status;}

    /**
     * Gets the name of the player who resigned
     * @return The resigner's name
     */
    public String getResigner(){return resigner;}

    /**
     * compares two players
     * @param o player to compare
     * @return true if they're the same, false otherwise
     */
    public boolean equals(Object o)
    {
        if(o instanceof Game){
            return ((Game)o).getP1().equals(this.p1) && ((Game)o).getP2().equals(this.p2);
        }
        return false;
    }
}
