package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.*;
import com.webcheckers.model.Player;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import static org.junit.Assert.*;
import static spark.Spark.halt;

import java.util.Map;
import java.util.logging.Logger;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.logging.Logger;

/**
 * Created by arthu on 11/26/2017.
 */
public class PostResignRouteTest {
    private static final Logger LOG = Logger.getLogger(PostResignRoute.class.getName());
    private Player player;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;
    private GameLobby gameLobby;
    private Request request;
    private Response response;
    private Session session;

    PostResignRoute CuT;

    @Before
    public void setup(){
        request = mock(Request.class);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        gameLobby = mock(GameLobby.class);

        CuT = new PostResignRoute(gameLobby);
    }

    @Test
    public void testResign(){
        Game game = new Game();
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        game.applyGame(p1, p2);
        when(session.attribute(GetGameRoute.GAME_ATTR)).thenReturn(game);
        when(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).thenReturn(p1);


        CuT.handle(request,response);
        assertEquals(p1.getName(), session.attribute(GetEndGameRoute.RESIGN_GUY_ATTR));
        assertTrue(game.getStatus()==MasterEnum.GameStatus.RESIGN);
    }
}
