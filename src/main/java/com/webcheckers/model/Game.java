package com.webcheckers.model;


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
    Player p1;
    //The player who got challenged to a game
    Player p2;
    //A list of spectators for a game
    List<Player> spectators;
    //A list move moves that houses all the moves in a game
    List<Move> movesList = new ArrayList<>();

    /**
     * Assigns the players to the game
     * @param p1
     * @param p2
     * @return True if they are added to the game false if not
     */
    public boolean Game(Player p1, Player p2){
        if(p2.isInGame()) {
            return false;
        }
        this.p1 = p1;
        this.p2 = p2;
        p1.assignGame(this);
        p2.assignGame(this);
        return true;
    }

    public Player getP1()
    {
        return p1;
    }

    public Player getP2(){
        return p2;
    }
}
