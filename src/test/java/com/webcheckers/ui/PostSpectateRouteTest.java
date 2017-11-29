package com.webcheckers.ui;

import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.PlayerLobby;

import com.google.gson.Gson;
import com.webcheckers.appl.*;
import com.webcheckers.model.*;
import org.junit.Before;
import org.junit.Test;
import spark.*;

import java.util.ArrayList;
import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Bobby on 11/28/2017.
 */
public class PostSpectateRouteTest {

    private Request request;
    private Response response;
    private Session session;
    private TemplateEngine templateEngine;
    private GameLobby gameLobby;
    private PlayerLobby playerLobby;
    private PostSpectateRoute CuT;

    @Before
    public void setup() {
        //set up mock objects
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);

        templateEngine = mock(TemplateEngine.class);
        playerLobby = mock(PlayerLobby.class);
        gameLobby = mock(GameLobby.class);

        // create a unique CuT for each test
        CuT = new PostSpectateRoute(playerLobby, gameLobby);
    }

    @Test
    public  void handling(){
        Game game = mock(Game.class);
        when(request.session()).thenReturn(session);
        playerLobby.add(new Player("opponent"));
        game.applyGame(new Player("opponent"), new Player("p2"));
        when(request.queryParams(PostStartRoute.OPPONENT_ATTR)).thenReturn("opponent");
        when(gameLobby.inGame(new Player("jim"))).thenReturn(game);
        when(game.getP1()).thenReturn(new Player("opponent"));
        CuT.handle(request, response);
    }
}
