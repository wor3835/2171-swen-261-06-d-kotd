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
 * Created by Bobby on 11/7/2017.
 */
public class PostCheckTurnRouteTest {

    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    private Player player;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;
    private GameLobby gameLobby;
    private Request request;
    private Response response;
    private Session session;

    PostCheckTurnRoute CuT;

    @Before
    public void set_up() {
        request = mock(Request.class);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);

        CuT = new PostCheckTurnRoute();

    }

    @Test
    public void testTrue(){
        Game game = new Game();
        Gson gson = new Gson();
        Player player = new Player("player1");
        Player player2 = new Player("player2");
        game.applyGame(player, player2);
        when(session.attribute(GetGameRoute.GAME_ATTR)).thenReturn(game);
        when(session.attribute("currentPlayer")).thenReturn(new Player("player1"));
        when(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).thenReturn(player);
        when(session.attribute(GetGameRoute.RED_PLAYER)).thenReturn(player);
        when(session.attribute(GetGameRoute.WHITE_PLAYER)).thenReturn(player2);

        assertEquals(gson.toJson(new Message("true", MasterEnum.MessageType.info)), CuT.handle(request, response));

        Gson gson2 = new Gson();

        when(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).thenReturn(player2);

        game.switchActive();

        assertEquals(gson2.toJson(new Message("true", MasterEnum.MessageType.info)), CuT.handle(request, response));
    }

    @Test
    public void testFalse(){
        Game game = new Game();
        Gson gson = new Gson();
        Player player = new Player("player1");
        Player player2 = new Player("player2");
        game.applyGame(player, player2);

        when(session.attribute(GetGameRoute.RED_PLAYER)).thenReturn(player);

        when(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).thenReturn(player2);
        when(session.attribute(GetGameRoute.GAME_ATTR)).thenReturn(game);
        when(session.attribute(GetGameRoute.WHITE_PLAYER)).thenReturn(player2);

        assertEquals(gson.toJson(new Message("false", MasterEnum.MessageType.info)), CuT.handle(request, response));

        when(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).thenReturn(player);

        game.switchActive();
        assertEquals(gson.toJson(new Message("false", MasterEnum.MessageType.info)), CuT.handle(request, response));
    }
}
