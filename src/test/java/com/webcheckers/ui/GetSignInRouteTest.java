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
 * The unit test suite for the {@link GetSignInRoute} component.
 *
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 */
public class GetSignInRouteTest {

    private GetSignInRoute CuT;

    private TemplateEngine engine;
    private Request request;

    static final String PLAYER_NAME_USED_ATTR = "playerExists";
    static final String VIEW_NAME = "signin.ftl";

    @Before
    public void setup(){
        request = mock(Request.class);
        engine = mock(TemplateEngine.class);

        CuT = new GetSignInRoute(engine);
    }

    @Test
    public void test_non_null_modelandView() {
        final Response response = mock(Response.class);
        final MyModelAndView myModelAndView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).
                thenAnswer(MyModelAndView.makeAnswer(myModelAndView));

        CuT.handle(request, response);

        final Object model = myModelAndView.model;
        //Assert not null Model
        assertNotNull(model);
    }

    @Test
    public void test_correct_viewName(){
        final Response response = mock(Response.class);
        final MyModelAndView myModelAndView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).
                thenAnswer(MyModelAndView.makeAnswer(myModelAndView));

        CuT.handle(request, response);

        // Test view name is correct
        assertEquals(VIEW_NAME, myModelAndView.viewName);
    }

    @Test
    public void test_correct_title(){
        final Response response = mock(Response.class);
        final MyModelAndView myModelAndView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).
                thenAnswer(MyModelAndView.makeAnswer(myModelAndView));

        CuT.handle(request, response);

        final Object model = myModelAndView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);
        //   * model contains all necessary View-Model data
        @SuppressWarnings("unchecked")
        final Map<String, Object> vm = (Map<String, Object>) model;
        //Assert title equals Sign in to play!
        assertEquals("Sign in to play!", vm.get("title"));

    }
}
