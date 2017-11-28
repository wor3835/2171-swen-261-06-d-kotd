package com.webcheckers.ui;

import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.model.Player;
import org.junit.Before;
import org.junit.Test;
import spark.*;

import java.util.Map;

import static com.webcheckers.ui.GetEndGameRoute.WINNER_ATTR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by arthu on 11/7/2017.
 */
public class GetEndGameRouteTest {
    private GetEndGameRoute CuT;

    private TemplateEngine engine;
    private Request request;
    private Session session;
    private Game game;

    private GameLobby gameLobby;

    static final String WINNER = "ME";


    @Before
    public void set_up(){
        request = mock(Request.class);
        engine = mock(TemplateEngine.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        game = mock(Game.class);
        gameLobby = mock(GameLobby.class);

        CuT = new GetEndGameRoute(engine, gameLobby);

    }

    @Test
    public void test_win_msg(){
        Player p1 = new Player(WINNER);
        final MyModelAndView myModelAndView = new MyModelAndView();
        final Response response = mock(Response.class);

        when(engine.render(any(ModelAndView.class))).
                thenAnswer(MyModelAndView.makeAnswer(myModelAndView));

        when(session.attribute(WINNER_ATTR)).thenReturn(WINNER);
        when(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).thenReturn(p1);
        when(session.attribute(GetGameRoute.GAME_ATTR)).thenReturn(game);
        when(game.getResigner()).thenReturn(null);
        CuT.handle(request,response);

        final Object model = myModelAndView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);

        @SuppressWarnings("unchecked")
        Map<String, Object> vm = (Map<String, Object>) model;

        assertEquals(String.format(GetEndGameRoute.WIN_MSG, p1.getName()), vm.get(GetEndGameRoute.GAME_OVER_ATTR));
    }

    @Test
    public void test_lose_msg(){
        Player p1 = new Player(WINNER);
        final MyModelAndView myModelAndView = new MyModelAndView();
        final Response response = mock(Response.class);

        when(engine.render(any(ModelAndView.class))).
                thenAnswer(MyModelAndView.makeAnswer(myModelAndView));

        when(session.attribute(WINNER_ATTR)).thenReturn(WINNER);
        Player p2 = new Player("Loser");

        when(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).thenReturn(p2);

        when(session.attribute(GetGameRoute.GAME_ATTR)).thenReturn(game);
        when(game.getResigner()).thenReturn(null);
        CuT.handle(request,response);

        CuT.handle(request,response);

        final Object model = myModelAndView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);

        @SuppressWarnings("unchecked")
        Map<String, Object> vm = (Map<String, Object>) model;

        vm = (Map<String, Object>) model;

        assertEquals(String.format(GetEndGameRoute.LOSE_MSG, p1.getName()), vm.get(GetEndGameRoute.GAME_OVER_ATTR));
    }

}
