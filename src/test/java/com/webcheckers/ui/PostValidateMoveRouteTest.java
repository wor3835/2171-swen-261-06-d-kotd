package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.model.Position;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The unit test suite for the {@link PostSignInRoute} component.
 *
 * @author <a href='mailto:gep2494@rit.edu'>George-Edward Pinal</a>
 */
public class PostValidateMoveRouteTest {

    private PostValidateMoveRoute CuT;

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
        CuT = new PostValidateMoveRoute();
    }

    @Test
    public void valid() throws Exception {

        Gson gson = new Gson();
        Game game = new Game();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        game.applyGame(player1, player2);
        Board board1 = new Board(MasterEnum.Color.RED);
        Board board2 = new Board(MasterEnum.Color.WHITE);
        game.applyBoard(board1, board2);
        when(session.attribute(PostValidateMoveRoute.MOVE_ATTR)).thenReturn(new Move(new Position(5,4), new Position(4,3)));
        Move m = new Move(new Position(5,4), new Position(4,3));

        when(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).thenReturn(player1);
        when(session.attribute(GetGameRoute.OPPONENT_ATTR)).thenReturn(player2);
        when(session.attribute(GetGameRoute.ACTIVE_COLOR)).thenReturn(MasterEnum.Color.RED);
        when(session.attribute(PostValidateMoveRoute.MOVE_ATTR)).thenReturn(new Move(new Position(5,4), new Position(4,3)));
        when(session.attribute(GetGameRoute.GAME_ATTR)).thenReturn(game);

        //assertEquals(gson.toJson(new Message("the move is valid", MasterEnum.MessageType.info)), CuT.handle(request, response));

        when(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).thenReturn(player2);
        when(session.attribute(GetGameRoute.OPPONENT_ATTR)).thenReturn(player1);
        when(session.attribute(GetGameRoute.ACTIVE_COLOR)).thenReturn(MasterEnum.Color.WHITE);
        when(session.attribute(PostValidateMoveRoute.MOVE_ATTR)).thenReturn(new Move(new Position(5,4), new Position(4,3)));

        //assertEquals(gson.toJson(new Message("the move is valid", MasterEnum.MessageType.info)), CuT.handle(request, response));
    }

    @Test
    public void invalid(){
        Gson gson = new Gson();
    }

}
