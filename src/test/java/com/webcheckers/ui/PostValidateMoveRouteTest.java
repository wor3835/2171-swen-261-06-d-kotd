package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.Before;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import java.util.logging.Logger;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostValidateMoveRouteTest {

    private PostValidateMoveRoute CuT;


    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    private Player player;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;
    private Request request;
    private Response response;
    private Session session;

    @Before
    public void setup() {
        //set up mock objects
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        engine = mock(TemplateEngine.class);

        playerLobby = mock(PlayerLobby.class);

        // create a unique CuT for each test
        CuT = new PostValidateMoveRoute();
    }
}
