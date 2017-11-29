package com.webcheckers.ui;

import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.Before;
import org.junit.Test;
import spark.*;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by wor3835 on 11/28/2017.
 */
public class PostDeleteRouteTest {
    private PostDeleteRoute CuT;

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
        CuT = new PostDeleteRoute();
    }

    @Test
    public void delete_game_test() throws Exception {
        final Response response = mock(Response.class);
        final Request request = mock(Request.class);

        Player p = new Player("p");

        when(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).thenReturn(p);

        String save = "save";

        session.attribute(PostSaveGameRoute.SAVEGAME_ATTR, save);

        //CuT.handle(request, response);
    }
}
