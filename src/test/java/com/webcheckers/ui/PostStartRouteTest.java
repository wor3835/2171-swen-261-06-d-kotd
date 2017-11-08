package com.webcheckers.ui;

import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Session;
import spark.TemplateEngine;

import java.util.logging.Logger;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by wor3835 on 11/7/2017.
 */
public class PostStartRouteTest {

    private PostStartRoute CuT;

    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameLobby gameLobby;
    private Request request;
    private Session session;

    @Before
    public void setup() {
        //set up mock objects
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);

        templateEngine = mock(TemplateEngine.class);
        playerLobby = mock(PlayerLobby.class);
        gameLobby = mock(GameLobby.class);

        // create a unique CuT for each test
        CuT = new PostStartRoute(templateEngine, playerLobby, gameLobby);
    }

    @Test
    public void notinGame() {
    }
}
