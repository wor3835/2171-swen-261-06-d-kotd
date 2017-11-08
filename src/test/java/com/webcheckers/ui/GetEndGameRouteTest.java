package com.webcheckers.ui;

import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.MasterEnum;
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
 * Created by arthu on 11/7/2017.
 */
public class GetEndGameRouteTest {
    private GetEndGameRoute CuT;

    private TemplateEngine engine;
    private Request request;
    private Session session;

    private GameLobby gameLobby;

    static final String WINNER = "ME";


    @Before
    public void set_up(){
        request = mock(Request.class);
        engine = mock(TemplateEngine.class);
        when(request.session()).thenReturn(session);
        session = mock(Session.class);

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

        session.attribute(GetEndGameRoute.WINNER_ATTR, WINNER);
        session.attribute(GetHomeRoute.CUR_PLAYER_ATTR, p1);
        CuT.handle(request,response);

        final Object model = myModelAndView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);

        @SuppressWarnings("unchecked")
        Map<String, Object> vm = (Map<String, Object>) model;

        assertEquals(String.format(GetEndGameRoute.WIN_MSG, p1.getName()), vm.get(GetEndGameRoute.GAME_OVER_ATTR));

        Player p2 = new Player("Loser");

        session.attribute(GetHomeRoute.CUR_PLAYER_ATTR, p2);

        CuT.handle(request,response);
        vm = (Map<String, Object>) model;

        assertEquals(String.format(GetEndGameRoute.LOSE_MSG, p1.getName()), vm.get(GetEndGameRoute.GAME_OVER_ATTR));
    }

}
