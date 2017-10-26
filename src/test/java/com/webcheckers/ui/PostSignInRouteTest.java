package com.webcheckers.ui;

import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import static spark.Spark.halt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
/**
 * The unit test suite for the {@link PostSignInRoute} component.
 *
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 */
public class PostSignInRouteTest {

    private static final String VALID_NAME = "Bobby";
    private static final String EMPTY_NAME = "";
    private static final String INVALID_NAME = "_!''";

    private PostSignInRoute CuT;


    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    private TemplateEngine engine;
    private PlayerLobby playerLobby;
    private GameLobby gameLobby;
    private Game game;
    private Request request;
    private Session session;

    @Before
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        engine = mock(TemplateEngine.class);

        gameLobby = new GameLobby();

        playerLobby = mock(PlayerLobby.class);

        // create a unique CuT for each test
        CuT = new PostSignInRoute(engine, playerLobby);
    }

    @Test
    public void invalid_name_1(){
        final Response response = mock(Response.class);
        when(request.queryParams(eq(PostSignInRoute.NAME))).thenReturn(INVALID_NAME);
        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(MyModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

        CuT.handle(request, response);

        final Object model = myModelView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);
        //   * model contains all necessary View-Model data
        @SuppressWarnings("unchecked")
        final Map<String, Object> vm = (Map<String, Object>) model;
        assertEquals("Name must be alphanumeric, try another name", vm.get("error"));
        assertEquals(Boolean.FALSE, vm.get(GetSignInRoute.PLAYER_NAME_USED_ATTR));
        //   * test view name
        assertEquals(GetSignInRoute.VIEW_NAME, myModelView.viewName);
    }
}
