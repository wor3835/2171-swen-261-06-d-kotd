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
    private List<Player> spectators;
    //A list move moves that houses all the moves in a game
    private List<Move> movesList = new ArrayList<>();

    private boolean gameOver;

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
        gameOver = false;
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
            default:
                break;
        }

    }

    public void endGame(){
        p1.leaveGame();
        p2.leaveGame();
        gameOver = true;
    }

    public MasterEnum.Color getActiveColor() {return activeColor;}

    public Player getP1()
    {
        return p1;
    }

    public Player getP2(){
        return p2;
    }

    public Board getB1() {return b1;}

    public Board getB2() {return b2;}

    public void addMove(Move m) {
        movesList.add(m);
    }

    public MasterEnum.Color getP1color(){
        return MasterEnum.Color.RED;
    }

    public MasterEnum.Color getP2color(){
        return MasterEnum.Color.WHITE;
    }

    public List<Move> getMovesList()
    {
        return movesList;
    }

    public boolean isGameOver() {return gameOver;}

    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Game){
            return ((Game)o).getP1().equals(this.p1) && ((Game)o).getP2().equals(this.p2);
        }
        return false;
    }
}
