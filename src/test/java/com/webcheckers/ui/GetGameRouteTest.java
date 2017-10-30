package com.webcheckers.ui;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.PlayerLobby;
import org.junit.Before;
import org.junit.Test;
import spark.*;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The unit test suite for the {@link GetGameRoute} component.
 *
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 */

public class GetGameRouteTest {

    private GetGameRoute CuT;

    private PlayerLobby playerLobby;
    private GameLobby gameLobby;

    private Request request;
    private Session session;
    private TemplateEngine templateEngine;

    static final String VIEW_NAME = "game.ftl";

    /**
     * Setup new mock objects for each test.
     */
    @Before
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        templateEngine = mock(TemplateEngine.class);

        gameLobby = new GameLobby();
        playerLobby = new PlayerLobby();
        CuT = new GetGameRoute(templateEngine, playerLobby, gameLobby);
    }

    @Test
    /**
     * Tests to see if the view name is correct
     */
    public void viewName() {
        final Response response = mock(Response.class);
        final MyModelAndView myModelAndView = new MyModelAndView();
        when(templateEngine.render(any(ModelAndView.class))).
                thenAnswer(MyModelAndView.makeAnswer(myModelAndView));

        CuT.handle(request, response);

        final Object model = myModelAndView.model;

        assertEquals(VIEW_NAME, myModelAndView.viewName);
        assertTrue(model instanceof Map);
    }

    @Test
    /**
     * Tests to see if the title is correct
     */
    public void checkTitle() {
        final Response response = mock(Response.class);
        final MyModelAndView myModelAndView = new MyModelAndView();
        when(templateEngine.render(any(ModelAndView.class))).
                thenAnswer(MyModelAndView.makeAnswer(myModelAndView));

        CuT.handle(request, response);

        final Object model = myModelAndView.model;

        final Map<String, Object> vm = (Map<String, Object>) model;
        assertEquals("Welcome!", vm.get("title"));
    }

    @Test
    /**
     * Tests to see if the lobby is empty
     */
    public void emptyLobby() {
        final Response response = mock(Response.class);
        final MyModelAndView myModelAndView = new MyModelAndView();
        when(templateEngine.render(any(ModelAndView.class))).
                thenAnswer(MyModelAndView.makeAnswer(myModelAndView));

        CuT.handle(request, response);

        final Object model = myModelAndView.model;

        final Map<String, Object> vm = (Map<String, Object>) model;
        assertNull(vm.get(playerLobby.getPlayerCount()));
    }
}
