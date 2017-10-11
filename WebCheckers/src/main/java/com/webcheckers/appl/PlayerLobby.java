package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.ArrayList;
/**
 * Created by arthu on 10/8/2017.
 */
public class PlayerLobby {
    ArrayList<Player> playerList = new ArrayList<Player>();

    public Player playerSignin(String name){
        return new Player(name);
    }

    public void add(Player player){
        playerList.add(player);
    }

    public int getPlayerCount(){
        return playerList.size();
    }

}
