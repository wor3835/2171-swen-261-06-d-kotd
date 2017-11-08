package com.webcheckers.ui;

import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import java.util.logging.Logger;

import com.google.gson.Gson;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The unit test suite for the {@link PostSignInRoute} component.
 *
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 */
public class PostBackUpMoveRouteTest {

    private PostBackUpMoveRoute CuT;


    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    private Player player;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;
    private Request request;
    private Response response;
    private Session session;

    @Before
    public void setup() {
        //set up mock objects
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        engine = mock(TemplateEngine.class);

        playerLobby = mock(PlayerLobby.class);

        // create a unique CuT for each test
        CuT = new PostBackUpMoveRoute();
    }

    @Test
    public void failure(){
        Gson gson = new Gson();
        when(session.attribute(PostValidateMoveRoute.MOVE_ATTR)).thenReturn(null);
        assertEquals(gson.toJson(new Message("back up move failure", MasterEnum.MessageType.error)), CuT.handle(request, response));
    }

    @Test
    public void success(){
        Gson gson = new Gson();
        when(session.attribute(PostValidateMoveRoute.MOVE_ATTR)).thenReturn("move");
        assertEquals(gson.toJson(new Message("back up move successful", MasterEnum.MessageType.info)), CuT.handle(request, response));
    }

}
