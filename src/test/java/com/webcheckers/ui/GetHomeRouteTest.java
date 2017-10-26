package com.webcheckers.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.GameLobby;

import spark.HaltException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

/**
 * Created by kzalb on 10/26/2017.
 */
public class GetHomeRouteTest {

    private GetHomeRoute CuT;

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
        CuT = new GetHomeRoute(engine, playerLobby, gameLobby);
    }

    /**
     * Test that CuT shows the Home view when the session is brand new.
     */
    @Test
    public void new_session() {
        // Arrange the test scenario: There is a new session without a game.
        when(session.isNew()).thenReturn(true);
        final Response response = mock(Response.class);

        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine
        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map
        final Object model = myModelView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);
        //   * model contains all necessary View-Model data
        @SuppressWarnings("unchecked")
        final Map<String, Object> vm = (Map<String, Object>) model;
        assertEquals(GetHomeRoute.TITLE, vm.get(GetHomeRoute.TITLE_ATTR));
        assertEquals(GameCenter.NO_GAMES_MESSAGE, vm.get(GetHomeRoute.GAME_STATS_MSG_ATTR));
        assertEquals(Boolean.TRUE, vm.get(GetHomeRoute.NEW_PLAYER_ATTR));
        //   * test view name
        assertEquals(GetHomeRoute.VIEW_NAME, myModelView.viewName);
        //   * verify that a player service object is stored on the session
        verify(session).attribute(eq(GetHomeRoute.PLAYERSERVICES_KEY), any(PlayerServices.class));
    }

    /**
     * Test that CuT redirects to the Game view when the session is not new.
     */
    @Test
    public void old_session() {
        // Arrange the test scenario: There is an existing session without a game.
        when(session.isNew()).thenReturn(false);
        final Response response = mock(Response.class);

        // Invoke the test
        try {
            CuT.handle(request, response);
            fail("Redirects invoke halt excpetions.");
        } catch (HaltException e) {
            // expected
        }

        // Analyze the results:
        //   * redirect to the Game view
        verify(response).redirect(WebServer.GAME_URL);
    }

}
