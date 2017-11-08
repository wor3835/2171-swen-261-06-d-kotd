package com.webcheckers.ui;

import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Player;
import org.junit.Before;
import org.junit.Test;
import spark.*;

import static com.webcheckers.ui.PostStartRoute.*;
import static org.junit.Assert.*;

import java.util.logging.Logger;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by wor3835 on 11/7/2017.
 */
public class PostStartRouteTest {

    private PostStartRoute CuT;

    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private GameLobby gameLobby;
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

        templateEngine = mock(TemplateEngine.class);
        playerLobby = mock(PlayerLobby.class);
        gameLobby = mock(GameLobby.class);

        // create a unique CuT for each test
        CuT = new PostStartRoute(templateEngine, playerLobby, gameLobby);
    }

    @Test(expected = HaltException.class)
    public void inGame() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        player2.assignGame(new Game());
        when(playerLobby.pullByName(request.queryParams(OPPONENT_ATTR))).thenReturn(player2);

        CuT.handle(request, response);

        assertEquals(session.attribute("err"), "Player selected is already in a game, choose another player.");
    }

    @Test(expected = HaltException.class)
    public void notInGame(){
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        BoardView bv = new BoardView(MasterEnum.Color.RED);

        session.attribute(BOARD_VIEW_KEY, mock(BoardView.class));
        when(playerLobby.pullByName(request.queryParams(OPPONENT_ATTR))).thenReturn(player2);
        when(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).thenReturn(player1);
        when(session.attribute(BOARD_VIEW_KEY)).thenReturn(bv);
        when(session.attribute(RED_PLAYER)).thenReturn(player1);
        when(session.attribute(WHITE_PLAYER)).thenReturn(player2);

        CuT.handle(request, response);

        assertNull(session.attribute("err"));
    }
}
