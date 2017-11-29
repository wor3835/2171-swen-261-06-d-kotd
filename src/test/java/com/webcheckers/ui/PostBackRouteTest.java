package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.*;
import com.webcheckers.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
public class PostBackRouteTest {
    private PostBackRoute CuT;

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
        CuT = new PostBackRoute();
    }

    @Test
    public void index_error_test() {
        final Response response = mock(Response.class);
        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));
        when(session.attribute(PostReplayRoute.CURRENT_IDX_ATTR)).thenReturn(-2);

        assertNotNull(CuT.handle(request, response));
    }

    @Test
    public void normal_backup(){
        final Response response = mock(Response.class);
        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));
        when(session.attribute(GetGameRoute.ACTIVE_COLOR)).thenReturn(MasterEnum.Color.RED);


        Game game = mock(Game.class);
        when(session.attribute(GetGameRoute.GAME_ATTR)).thenReturn(game);
        Board b = new Board();
        when(game.getB()).thenReturn(b);

        int num = 4;

        Position start = new Position(0,0);
        Position end = new Position(1,1);
        Move move = new Move(start,end);
        when(game.getMove(num)).thenReturn(move);

        Player p1 = new Player("one");
        Player p2 = new Player("two");

        when(game.getP1()).thenReturn(p1);
        when(game.getP2()).thenReturn(p2);

        when(session.attribute(PostReplayRoute.CURRENT_IDX_ATTR)).thenReturn(num);
        assertNotNull(CuT.handle(request, response));
        assertEquals(session.attribute(GetGameRoute.ACTIVE_COLOR), MasterEnum.Color.RED);
    }
}
