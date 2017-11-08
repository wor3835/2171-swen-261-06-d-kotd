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
        when(request.session()).thenReturn(session);
        session = mock(Session.class);


        CuT = new PostCheckTurnRoute();

    }

    @Test
    public void testTrue(){
        Game game = new Game();
        Gson gson = new Gson();
        Player player = new Player("player1");
        when(session.attribute(GetGameRoute.GAME_ATTR)).thenReturn(game);
        when(session.attribute("currentPlayer")).thenReturn(player);
        when(session.attribute(GetGameRoute.RED_PLAYER)).thenReturn(player);
        when(((Game)session.attribute(GetGameRoute.GAME_ATTR)).getActiveColor()).thenReturn(MasterEnum.Color.RED);

        assertEquals(gson.toJson(new Message("true", MasterEnum.MessageType.info)), CuT.handle(request, response));

        Game game2 = new Game();
        Gson gson2 = new Gson();
        when(session.attribute(GetGameRoute.GAME_ATTR)).thenReturn(game2);
        when(session.attribute(GetGameRoute.WHITE_PLAYER)).thenReturn(player);
        when(((Game)session.attribute(GetGameRoute.GAME_ATTR)).getActiveColor()).thenReturn(MasterEnum.Color.WHITE);

        assertEquals(gson2.toJson(new Message("true", MasterEnum.MessageType.info)), CuT.handle(request, response));
    }
}
