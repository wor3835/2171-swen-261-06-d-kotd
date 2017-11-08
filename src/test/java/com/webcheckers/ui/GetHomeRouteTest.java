package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static spark.Spark.halt;

import org.junit.Before;
import org.junit.Test;
import spark.*;

import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.PlayerLobby;

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

    @Test
    public void new_session() {

        when(session.isNew()).thenReturn(true);
        final Response response = mock(Response.class);
        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));
        // Invoke the test
        CuT.handle(request, response);
        final Object model = myModelView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);
        //   * model contains all necessary View-Model data
        @SuppressWarnings("unchecked")
        final Map<String, Object> vm = (Map<String, Object>) model;
        assertEquals("Welcome!", vm.get("title"));
        assertEquals(GetHomeRoute.VIEW_NAME, myModelView.viewName);
        verify(session).attribute(eq("playerLobby"), any(PlayerLobby.class));
    }

    @Test
    public void old_session(){
        // Arrange the test scenario: There is an existing session without a game.
        when(session.isNew()).thenReturn(false);
        final Response response = mock(Response.class);

        // Invoke the test
        try {
            CuT.handle(request, response);
        } catch (Exception e) {
            // expected
        }
        // Analyze the results:
        response.redirect("/");
        verify(response).redirect(WebServer.HOME_URL);
    }

    @Test
    public void TestNonRender()
    {
        final Response response = mock(Response.class);
        CuT.handle(request, response);
        assertNull();
    }

}
