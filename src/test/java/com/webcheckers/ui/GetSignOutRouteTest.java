package com.webcheckers.ui;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static spark.Spark.halt;

import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.Before;
import org.junit.Test;
import spark.*;


/**
 * Created by Bobby on 11/7/2017.
 */
public class GetSignOutRouteTest {

    private final TemplateEngine templateEngine = mock(TemplateEngine.class);
    private final PlayerLobby playerLobby = mock(PlayerLobby.class);
    private Request request;
    private Response response;
    private Session session;
    private TemplateEngine engine;

    private GetSignOutRoute CuT;

    @Before
    public void setup() {
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        engine = mock(TemplateEngine.class);

        // create a unique CuT for each test

        CuT = new GetSignOutRoute(templateEngine, playerLobby);
    }

    @Test
    public void thetest(){
        Player player = playerLobby.playerSignin("player1");
        session.attribute(GetHomeRoute.CUR_PLAYER_ATTR, player);

        CuT.handle(request, response);

        assertNull(playerLobby.pullByName("player1"));
        assertTrue(session.attributes().isEmpty());
    }
}
