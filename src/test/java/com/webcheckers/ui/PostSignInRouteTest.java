package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;

import spark.*;

import static org.junit.Assert.*;
import static spark.Spark.halt;

import java.util.Map;
import java.util.logging.Logger;


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

    private static final String VALID_NAME = "Robert";
    private static final String EMPTY_NAME = "";
    private static final String INVALID_NAME = "_!''";

    private PostSignInRoute CuT;


    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    private Player player;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;
    private Request request;
    private Session session;

    @Before
    public void setup() {
        //set up mock objects
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        engine = mock(TemplateEngine.class);

        playerLobby = mock(PlayerLobby.class);

        // create a unique CuT for each test
        CuT = new PostSignInRoute(engine, playerLobby);
    }

    //Tests for a name containing an invalid character
    @Test
    public void invalid_name(){
        final Response response = mock(Response.class);
        when(request.queryParams(eq(PostSignInRoute.NAME))).thenReturn(INVALID_NAME);
        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

        CuT.handle(request, response);

        final Object model = myModelView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);
        //   * model contains all necessary View-Model data
        @SuppressWarnings("unchecked")
        final Map<String, Object> vm = (Map<String, Object>) model;
        assertEquals("Name must be alphanumeric, try another name", vm.get("error"));
        assertEquals(Boolean.FALSE, vm.get(GetSignInRouteTest.PLAYER_NAME_USED_ATTR));
        //   * test view name
        assertEquals(GetSignInRouteTest.VIEW_NAME, myModelView.viewName);
    }

    //Tests for a name containing nothing
    @Test
    public void empty_name(){
        final Response response = mock(Response.class);
        when(request.queryParams(eq(PostSignInRoute.NAME))).thenReturn(EMPTY_NAME);

        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

        CuT.handle(request, response);

        final Object model = myModelView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);
        //   * model contains all necessary View-Model data
        @SuppressWarnings("unchecked")
        final Map<String, Object> vm = (Map<String, Object>) model;
        assertEquals("Name must not be empty", vm.get("error"));
        assertEquals(Boolean.FALSE, vm.get(GetSignInRouteTest.PLAYER_NAME_USED_ATTR));
        //   * test view name
        assertEquals(GetSignInRouteTest.VIEW_NAME, myModelView.viewName);
    }

    //Tests for a valid name
    @Test
    public void valid_name(){
        final Response response = mock(Response.class);
        when(playerLobby.playerSignin(VALID_NAME)).thenReturn(new Player(VALID_NAME));
        when(request.queryParams(eq(PostSignInRoute.NAME))).thenReturn(VALID_NAME);

        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

        CuT.handle(request, response);

        final Object model = myModelView.model;
        //model is null because a valid name causes a redirect to home, nothing is returned to MyModelView
        assertNull(model);
        //   * model cannot contain anything because it is null

        assertEquals(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR), player);
    }

    //Tests for when a duplicate name is entered
    @Test
    public void dublicate_name(){
        final Response response = mock(Response.class);
        when(playerLobby.contains(VALID_NAME)).thenReturn(Boolean.TRUE);
        when(playerLobby.playerSignin(VALID_NAME)).thenReturn(new Player(VALID_NAME));
        when(request.queryParams(eq(PostSignInRoute.NAME))).thenReturn(VALID_NAME);

        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

        CuT.handle(request, response);


        final Object model = myModelView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);
        //   * model contains all necessary View-Model data
        @SuppressWarnings("unchecked")
        final Map<String, Object> vm = (Map<String, Object>) model;

        assertEquals("Name taken, try another name", vm.get("error"));
    }
}
