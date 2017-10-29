package com.webcheckers.model;


import com.webcheckers.appl.MasterEnum;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;


/**
 * Created by arthu on 10/14/2017.
 */
public class Game {

    /**
     * Variables to keep track of the players and the list of spectators
     */

    //The player who initiated the game
    private Player p1;
    private Board b1;
    //The player who got challenged to a game
    private Player p2;
    private Board b2;
    //A list of spectators for a game
    private List<Player> spectators;
    //A list move moves that houses all the moves in a game
    public List<Move> movesList = new ArrayList<>();

    public MasterEnum.Color activeColor;

    /**
     * Assigns the players to the game
     * @param p1
     * @param p2
     * @return True if they are added to the game false if not
     */
    public boolean applyGame(Player p1, Player p2){
        if(p2.isInGame()) {
            return false;
        }
        this.p1 = p1;
        this.p2 = p2;
        p1.assignGame(this);
        p2.assignGame(this);

        activeColor = MasterEnum.Color.RED;

        return true;
    }

    public void applyBoard(Board b1, Board b2) {
        this.b1 = b1;
        this.b2 = b2;
    }

    public void switchActive(){
        switch(activeColor){
            case RED:
                activeColor = MasterEnum.Color.WHITE;
                break;
            case WHITE:
                activeColor = MasterEnum.Color.WHITE;
                break;
            default:
                break;
        }

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
}
