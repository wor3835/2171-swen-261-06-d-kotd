package com.webcheckers.ui;

import com.webcheckers.appl.Game;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.model.Board;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static spark.Spark.halt;

/**
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class PostReplayRoute implements Route {

    public final static String CURRENT_IDX_ATTR = "currentIdx";

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();

        String name = request.queryParams(PostSaveGameRoute.SAVEGAME_ATTR);

        Player player = session.attribute(GetHomeRoute.CUR_PLAYER_ATTR);
        Game game = player.getGame(name);

        if(game == null)
            throw new RuntimeException("Game not found");

        session.attribute(GetGameRoute.VIEW_MODE, MasterEnum.ViewMode.REPLAY);

        session.attribute(GetGameRoute.ACTIVE_COLOR, game.getActiveColor());

        session.attribute(GetGameRoute.RED_PLAYER, game.getP1());

        Board board = new Board(game.getP1().equals(player)?game.getP1color():game.getP2color());
        game.applyBoard(board);

        session.attribute(GetGameRoute.BOARD_VIEW_KEY, new BoardView(game.getB()));

        session.attribute(GetGameRoute.WHITE_PLAYER, game.getP2());

        session.attribute(GetGameRoute.OPPONENT_ATTR, game.getP1().equals(player)?game.getB2():game.getP1());

        session.attribute(GetGameRoute.GAME_ATTR, game);

        session.attribute(CURRENT_IDX_ATTR, -1);

        response.redirect(WebServer.GAME_URL);
        halt();
        return null;
    }
}
