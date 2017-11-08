package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Session;
import spark.TemplateEngine;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by wor3835 on 11/7/2017.
 */
public class WebServerTest {
    private WebServer CuT;

    private Request request;
    private Session session;

    private TemplateEngine templateEngine;
    private Gson gson;
    private PlayerLobby playerLobby;
    private GameLobby gameLobby;


    @Before
    public void set_up(){
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);

        templateEngine = mock(TemplateEngine.class);
        gson = mock(Gson.class);
        playerLobby = mock(PlayerLobby.class);
        gameLobby = mock(GameLobby.class);

        CuT = new WebServer(templateEngine, gson, playerLobby, gameLobby);

    }

    @Test
    public void servernotNull() {
        assertNotNull(new WebServer(templateEngine, gson, playerLobby, gameLobby));
    }

    @Test
    public void
}
