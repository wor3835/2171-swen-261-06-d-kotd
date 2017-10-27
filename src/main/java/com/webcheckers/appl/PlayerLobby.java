package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by arthu on 10/8/2017.
 * @author <a href='mailto:gep2494@rit.edu'>George-Edward Pinal</a>
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 */

/**
 * Keeps track of the players logged in using an ArrayList.
 */
public class PlayerLobby {
    ArrayList<Player> playerList = new ArrayList<Player>();

    /**
     * Signs a player in to play.
     * @param name name of player
     * @return player object
     */
    public Player playerSignin(String name){

        Player p = new Player(name);
        playerList.add(p);
        return p;
    }

    /**
     * Adds player to the playerList.
     * @param player player to add
     */
    public void add(Player player){
        playerList.add(player);
    }

    /**
     * Gets the number of players logged in.
     * @return size of playerList
     */
    public int getPlayerCount(){
        return playerList.size();
    }

    /**
     * Removes player from the playerList.
     * @param p player to remove
     * @return player removed
     */
    public Player removePlayer(Player p){
        Player player;
        for(int i = 0; i < playerList.size(); i++){
            if(playerList.get(i).equals(p)){
                return playerList.remove(i);
            }
        }
        return null;
    }

    /**
     * Pulls a player from playerList by name.
     * @param name name of player to pull
     * @return player object associated with name
     */
    public Player pullByName(String name){
        for(int i = 0; i < playerList.size(); i++){
            if(playerList.get(i).getName().equals(name))
                return playerList.get(i);
        }
        throw new RuntimeException("No Player found with name: "+name);
    }


    /**
     * Checks in name is in playerlist
     * @param s comparing string
     * @return true if in playerList, false if else
     */
    public boolean contains(String s){
        for(int i = 0; i < playerList.size(); i++){
            if(playerList.get(i).getName().equals(s))
                return true;
        }
        return false;
    }

    /**
     * iterator to iterate over playerList
     * @return an iterator
     */
    public Iterator<Player> iterator(){
        return playerList.iterator();
    }

}
