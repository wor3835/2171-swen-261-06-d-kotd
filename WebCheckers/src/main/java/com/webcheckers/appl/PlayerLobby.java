package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by arthu on 10/8/2017.
 * @author <a href='mailto:gep2494@rit.edu'>George-Edward Pinal</a>
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 */
public class PlayerLobby {
    ArrayList<Player> playerList = new ArrayList<Player>();
    int a;

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

    public Player pullByHashCode(int hashCode){
        for(int i = 0; i < playerList.size(); i++){
            if(playerList.get(i).hashCode() == hashCode)
                return playerList.get(i);
        }
        throw new RuntimeException("No Player found with hashcode: "+hashCode);
    }

    public boolean contains(String s){
        for(int i = 0; i < playerList.size(); i++){
            if(playerList.get(i).getName().equals(s))
                return true;
        }
        return false;
    }

    public Iterator<Player> iterator(){
        return playerList.iterator();
    }

}
