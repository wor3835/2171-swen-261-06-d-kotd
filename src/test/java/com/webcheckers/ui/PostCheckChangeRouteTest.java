package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.*;
import org.junit.Before;
import org.junit.Test;
import spark.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by wor3835 on 11/28/2017.
 */
public class PostCheckChangeRouteTest {
    private PostCheckChangeRoute CuT;

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
        CuT = new PostCheckChangeRoute();
    }

    @Test
    public void change_not_available() {
        final Response response = mock(Response.class);
        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

        Game g = mock(Game.class);
        gameLobby.addGame(g);

        when(session.attribute(GetGameRoute.GAME_ATTR)).thenReturn(g);
        when(g.getActiveColor()).thenReturn(MasterEnum.Color.RED);

        when(session.attribute(GetGameRoute.ACTIVE_COLOR)).thenReturn(MasterEnum.Color.RED);


        CuT.handle(request, response);
        assertNotNull(CuT.handle(request, response));
        Gson gson = new Gson();
        Message msg = new Message("false", MasterEnum.MessageType.info);
        assertEquals(gson.toJson(msg), CuT.handle(request,response));
    }

    @Test
    public void change_available() {
        final Response response = mock(Response.class);
        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

        Game g = mock(Game.class);
        gameLobby.addGame(g);

        when(g.getActiveColor()).thenReturn(MasterEnum.Color.RED);

        when(session.attribute(GetGameRoute.ACTIVE_COLOR)).thenReturn(MasterEnum.Color.WHITE);

        when(session.attribute(GetGameRoute.GAME_ATTR)).thenReturn(g);

        //when(session.attribute(GetGameRoute.ACTIVE_COLOR)).thenReturn(MasterEnum.Color.RED);

        CuT.handle(request, response);
        assertNotNull(CuT.handle(request, response));
        Gson gson = new Gson();
        Message msg = new Message("true", MasterEnum.MessageType.info);
        assertEquals(gson.toJson(msg), CuT.handle(request, response));
    }
}
