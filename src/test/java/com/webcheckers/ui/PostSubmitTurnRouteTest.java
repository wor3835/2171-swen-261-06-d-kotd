package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.*;
import com.webcheckers.model.*;
import org.junit.Before;
import org.junit.Test;
import spark.*;

import java.util.ArrayList;
import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Bobby on 11/7/2017.
 */
public class PostSubmitTurnRouteTest {

    private final GameLobby gameLobby = mock(GameLobby.class);
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    private Player player;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;
    private Request request;
    private Response response;
    private Session session;
    private PostSubmitTurnRoute CuT;

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
        CuT = new PostSubmitTurnRoute();
    }

    @Test
    public void null_move(){
        Gson gson = new Gson();
        when(session.attribute(PostValidateMoveRoute.MOVE_ATTR)).thenReturn(null);
        Game game = mock(Game.class);
        when(session.attribute(GetGameRoute.GAME_ATTR)).thenReturn(game);
        when(game.isGameOver()).thenReturn(false);

        assertEquals(gson.toJson(new Message("move is null", MasterEnum.MessageType.error)), CuT.handle(request, response));
    }

    @Test
    public void redmove_whitemove(){
        Gson gson = new Gson();
        Game game = new Game();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        game.applyGame(player1, player2);
        Board board1 = new Board(MasterEnum.Color.RED);
        Board board2 = new Board(MasterEnum.Color.WHITE);
        game.applyBoard(board1, board2);
        when(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).thenReturn(player1);
        when(session.attribute(GetGameRoute.OPPONENT_ATTR)).thenReturn(player2);
        when(session.attribute(GetGameRoute.ACTIVE_COLOR)).thenReturn(MasterEnum.Color.RED);
        when(session.attribute(PostValidateMoveRoute.MOVE_ATTR)).thenReturn(new Move(new Position(5,4), new Position(4,3)));
        when(session.attribute(GetGameRoute.GAME_ATTR)).thenReturn(game);

        assertEquals(gson.toJson(new Message("turn processed", MasterEnum.MessageType.info)), CuT.handle(request, response));

        when(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).thenReturn(player2);
        when(session.attribute(GetGameRoute.OPPONENT_ATTR)).thenReturn(player1);
        when(session.attribute(GetGameRoute.ACTIVE_COLOR)).thenReturn(MasterEnum.Color.WHITE);
        when(session.attribute(PostValidateMoveRoute.MOVE_ATTR)).thenReturn(new Move(new Position(5,4), new Position(4,3)));

        assertEquals(gson.toJson(new Message("turn processed", MasterEnum.MessageType.info)), CuT.handle(request, response));
    }
}
