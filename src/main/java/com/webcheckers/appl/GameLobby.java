package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.ArrayList;

/**
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class GameLobby {
    //List of all games going on in the webserver
    private ArrayList<Game> gamesList = new ArrayList<>();

    /**
     * Adds a game object to the gameList
     * @param game
     */
    public void addGame(Game game){
        gamesList.add(game);
    }

    /**
     * Removes a game object from the gameList
     * @param game
     */
    public void removeGame(Game game){
        gamesList.remove(game);
    }

    /**
     * @return the list of games on the webserver
     */
    public ArrayList<Game> getGamesList() {
        return gamesList;
    }

    /**
     * Checks through the list of games to see if a player is in a game or not
     * @param p The player being looked for
     * @return The game that the player is in
     */
    public Game inGame(Player p){
        for(int i = 0; i < gamesList.size(); i++){
            if(p.equals(gamesList.get(i).getP1()) || p.equals(gamesList.get(i).getP2()))
                return gamesList.get(i);
        }
        return null;
    }
}
