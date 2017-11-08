package com.webcheckers.appl;

import static org.junit.Assert.*;

import com.webcheckers.model.Player;
import org.junit.Test;

import com.webcheckers.appl.PlayerLobby;

import java.util.Iterator;


/**
 * Created by arthu on 10/26/2017.
 * Tests to see if playerLobby can be created
 */
public class PlayerLobbyTest {

    @Test
    public void test_add_remove_player(){
        final PlayerLobby CuT = new PlayerLobby();

        final Player player = new Player("name");

        //Invoke Test
        CuT.add(player);

        //Analyze Results
        //Returns that the player is in PlayerLobby
        assertTrue(CuT.contains(player.getName()));
        assertFalse(CuT.contains("NO_NAME"));
        //Checks that there is now a player signed in as well
        assertEquals(1, CuT.getPlayerCount());

        //Invoke test and analyze
        //Checks if the player was returned and removed
        assertNotNull(CuT.removePlayer(player));
        //Checks that no players are signed in
        assertEquals(0, CuT.getPlayerCount());
        //Check that player can't be removed when not there
        assertNull(CuT.removePlayer(player));
    }

    @Test
    /**
     * tests that player can sign in and join lobby
     */
    public void test_signin_player(){
        final PlayerLobby CuT = new PlayerLobby();

        final String NAME = "name";

        //invoke test
        Player player = CuT.playerSignin(NAME);

        //Analyze the results
        //Player is not null
        assertNotNull(player);
        //Player is the same as the one attempted to signin
        assertTrue(player.getName().equals(NAME));
    }

    @Test
    /**
     * tests that playerLobby count goes up after player joins
     */
    public void test_player_count(){
        final PlayerLobby CuT = new PlayerLobby();
        final String[] names1 = {"name", "name2", "name3"};

        //Analyze size before test
        //Check for empty
        assertEquals(0, CuT.getPlayerCount());

        //Invoke test
        for(String n: names1)
            CuT.playerSignin(n);
        //analyze that number signed in matches number of names
        assertEquals(names1.length, CuT.getPlayerCount());
    }

    @Test(expected = RuntimeException.class)
    /**
     * tests pulling a player by name in the lobby
     */
    public void test_pull_by_name(){
        final PlayerLobby CuT = new PlayerLobby();
        final Player player = new Player("name");

        //Invoke test
        CuT.add(player);
        final Player p2 = CuT.pullByName(player.getName());

        //Analyze results
        //See if player pulled is the same as the original player
        assertTrue(p2.equals(player));

        Player test = CuT.pullByName("NO_NAME");
    }

    @Test
    /**
     * tests the iterator method 
     */
    public void test_iterator(){
        final PlayerLobby CuT = new PlayerLobby();
        final String NAME = "name";

        //Invoke test
        CuT.playerSignin(NAME);
        Iterator<Player> iterator= CuT.iterator();

        //Analyze results
        //Assert iterator returned is nonnull
        assertNotNull(iterator);
    }
}
