package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.ArrayList;
/**
 * Created by arthu on 10/8/2017.
 * @author <a href='mailto:gep2494@rit.edu'>George-Edward Pinal</a>
 */
public class PlayerLobby {
    ArrayList<Player> playerList = new ArrayList<Player>();

    public Player playerSignin(String name){
        Player p = new Player(name);
        playerList.add(p);
        return p;
    }

    public void add(Player player){
        playerList.add(player);
    }

    public int getPlayerCount(){
        return playerList.size();
    }

    public Player removePlayer(Player p){
        int i = 0;
        for(i = 0; i < playerList.size(); i++){
            if(playerList.get(i).equals(p)){
                playerList.remove(i);
                break;
            }
        }
        if(i == playerList.size())
            return null;
        else
            return p;
    }

    public boolean contains(String s){
        for(int i = 0; i < playerList.size(); i++){
            if(playerList.get(i).getName().equals(s))
                return true;
        }
        return false;
    }

}
