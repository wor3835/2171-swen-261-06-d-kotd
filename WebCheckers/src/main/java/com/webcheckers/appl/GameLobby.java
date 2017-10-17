package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.ArrayList;

/**
 * Created by arthu on 10/17/2017.
 */
public class GameLobby {
    private ArrayList<Game> gamesList = new ArrayList<>();

    public void addGame(Game game){
        gamesList.add(game);
    }

    public void removeGame(Game game){
        gamesList.remove(game);
    }

    public boolean inGame(Player p){
        for(int i = 0; i < gamesList.size(); i++){
            if(p.equals(gamesList.get(i).getP2()) || p.equals(gamesList.get(i).getP2()))
                return true;
        }
        return false;
    }
}
