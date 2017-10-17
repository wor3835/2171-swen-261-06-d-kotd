package com.webcheckers.model;


import java.util.List;
import java.util.ArrayList;


/**
 * Created by arthu on 10/14/2017.
 */
public class Game {
    Player p1;
    Player p2;
    List<Player> spectators = new ArrayList<>();

    public boolean Game(Player p1, Player p2){
        if(p2.isInGame()) {
            return false;
        }
        this.p1 = p1;
        this.p2 = p2;
        p1.assignGame(this);
        p2.assignGame(this);
        p1.isInGame();
        p2.isInGame();
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
