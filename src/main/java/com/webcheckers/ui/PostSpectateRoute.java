package com.webcheckers.ui;

import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.PlayerLobby;
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
public class PostSpectateRoute implements Route {

    private final GameLobby gameLobby;
    private final PlayerLobby playerLobby;

    final static String WATCHING_ATTR = "watching";

    public PostSpectateRoute(PlayerLobby playerLobby, GameLobby gameLobby){
        this.gameLobby = gameLobby;
        this.playerLobby = playerLobby;
    }

    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        String name = request.queryParams(PostStartRoute.OPPONENT_ATTR);

        Player watching = playerLobby.pullByName(name);

        Game game = gameLobby.inGame(watching);

        Player opponent = game.getP1().equals(watching) ? game.getP2(): game.getP1();

        if(game == null)
            throw new RuntimeException("Game not found");


        session.attribute(GetGameRoute.VIEW_MODE, MasterEnum.ViewMode.SPECTATOR);

        session.attribute(GetGameRoute.ACTIVE_COLOR, game.getActiveColor());

        session.attribute(GetGameRoute.RED_PLAYER, game.getP1());

        session.attribute(GetGameRoute.BOARD_VIEW_KEY,
                game.getP1().equals(watching)?new BoardView(game.getB1()):new BoardView(game.getB2()));

        session.attribute(GetGameRoute.WHITE_PLAYER, game.getP2());

        session.attribute(GetGameRoute.OPPONENT_ATTR, opponent);

        session.attribute(GetGameRoute.GAME_ATTR, game);

        game.addSpectator(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR));

        response.redirect(WebServer.GAME_URL);
        halt();
        return null;
    }
}
