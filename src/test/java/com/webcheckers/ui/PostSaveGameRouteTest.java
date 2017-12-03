package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.*;
import org.junit.Before;
import org.junit.Test;
import spark.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by wor3835 on 11/28/2017.
 */
public class PostSaveGameRouteTest {
    private PostSaveGameRouteTest CuT;

    // friendly objects
    private GameLobby gameLobby;
    private PlayerLobby playerLobby;

    // mock objects
    private Request request;
    private Session session;
    private TemplateEngine engine;

    /**
     * Setup new mock objects for each test.
     */
    @Before
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        engine = mock(TemplateEngine.class);

        // create a unique CuT for each test
        // the GameCenter is friendly but the engine mock will need configuration
        gameLobby = new GameLobby();
        playerLobby = new PlayerLobby();
        CuT = new PostSaveGameRouteTest();
    }

    @Test
    public void change_not_available() {
    }

    @Test
    public void change_available() {
    }
}
