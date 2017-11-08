package com.webcheckers.ui;

import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.*;
import org.junit.Before;
import org.junit.Test;
import spark.*;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
    private Game game;

    private Request request;
    private Session session;
    private TemplateEngine engine;

    private Player p;
    private Player o;
    private BoardView boardView;
    private Board board;

    static final String VIEW_NAME = "game.ftl";

    /**
     * Setup new mock objects for each test.
     */
    @Before
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        engine = mock(TemplateEngine.class);

        board = new Board();
        boardView = new BoardView(board);

        p = new Player("current");
        o = new Player("opponent");
        p.assignPos(board, MasterEnum.Color.WHITE);

        game = new Game();
        gameLobby = new GameLobby();
        playerLobby = new PlayerLobby();
        CuT = new GetGameRoute(engine, playerLobby, gameLobby);
    }

    @Test(expected = HaltException.class)
    /**
     * Tests to see if the moves list is zero
     */
    public void moves_list_equals_0() {
        final Response response = mock(Response.class);
        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

        when(session.attribute(GetGameRoute.GAME_ATTR)).thenReturn(game);
        when(session.attribute(GetGameRoute.BOARD_VIEW_KEY)).thenReturn(boardView);
        when(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).thenReturn(p);
        when(session.attribute(GetGameRoute.OPPONENT_ATTR)).thenReturn(o);

        game.applyGame(p, o);

        assertFalse(game.isGameOver());

        assertNull(CuT.handle(request, response));


        assertEquals(session.attribute(GetEndGameRoute.WINNER_ATTR), p.getName());
        final Object model = myModelView.model;
        assertNull(model);
        assertTrue(game.isGameOver());

    }

    @Test(expected = HaltException.class)
    public void is_game_over() {
        final Response response = mock(Response.class);
        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

        when(session.attribute(GetGameRoute.GAME_ATTR)).thenReturn(game);
        when(session.attribute(GetGameRoute.BOARD_VIEW_KEY)).thenReturn(boardView);
        when(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).thenReturn(p);
        when(session.attribute(GetGameRoute.OPPONENT_ATTR)).thenReturn(o);

        game.applyGame(p, o);
        game.endGame();

        assertTrue(game.isGameOver());

        assertNull(CuT.handle(request, response));

        assertEquals(session.attribute(GetEndGameRoute.WINNER_ATTR), p.getName());
    }

    @Test(expected = HaltException.class)
    public void is_pos_list_empty() {
        {
            final Response response = mock(Response.class);
            final MyModelAndView myModelView = new MyModelAndView();
            when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

            when(session.attribute(GetGameRoute.GAME_ATTR)).thenReturn(game);
            when(session.attribute(GetGameRoute.BOARD_VIEW_KEY)).thenReturn(boardView);
            when(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).thenReturn(p);
            when(session.attribute(GetGameRoute.OPPONENT_ATTR)).thenReturn(o);

            board.addPiece(new Pawn(MasterEnum.Color.WHITE), 4, 4);
            p.assignPos(board, MasterEnum.Color.WHITE);

            game.applyGame(p, o);

            assertFalse(game.isGameOver());

            assertNull(CuT.handle(request, response));

            assertTrue(game.isGameOver());
            assertEquals(session.attribute(GetEndGameRoute.WINNER_ATTR), p.getName());
        }
    }

    @Test
    public void test_elements_in_map() {

        final Response response = mock(Response.class);
        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

        when(session.attribute(GetGameRoute.GAME_ATTR)).thenReturn(game);
        when(session.attribute(GetGameRoute.BOARD_VIEW_KEY)).thenReturn(boardView);
        when(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).thenReturn(p);
        when(session.attribute(GetGameRoute.OPPONENT_ATTR)).thenReturn(o);

        board.addPiece(new Pawn(MasterEnum.Color.WHITE), 4, 4);
        p.assignPos(board, MasterEnum.Color.WHITE);
        board.addPiece(new Pawn(MasterEnum.Color.RED), 4, 6);
        o.assignPos(board, MasterEnum.Color.RED);

        game.applyGame(p, o);

        assertFalse(game.isGameOver());

        CuT.handle(request, response);

        final Object model = myModelView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);
    }
}
