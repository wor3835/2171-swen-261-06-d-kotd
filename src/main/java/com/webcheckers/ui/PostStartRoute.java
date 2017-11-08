package com.webcheckers.ui;

import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.BoardView;
import com.webcheckers.appl.Game;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;
import spark.Session;

import com.webcheckers.model.Player;

import static spark.Spark.halt;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 */
public class PostStartRoute implements Route {

    /**
     * Global variables to keep track of the players, routes, and templates
     */
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;
    private final GameLobby gameLobby;

    static final String VIEW_NAME = "game.ftl";
    static final String BOARD_VIEW_KEY = "board";

    static final String VIEW_MODE = "viewMode"; // mode of the Game View
    static final String RED_PLAYER = "redPlayer";
    static final String WHITE_PLAYER = "whitePlayer";
    static final String ACTIVE_COLOR = "activeColor";

    static final String OPPONENT_ATTR = "opponent";

    public PostStartRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameLobby gameLobby) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameLobby = gameLobby;
        //
        LOG.config("PostStartRoute is initialized.");
    }

    /**
     * Handles the route request and response
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public Object handle(Request request, Response response) {
        final Session httpSession = request.session();

        httpSession.attribute(GetHomeRoute.PLAYER_LOBBY_KEY, playerLobby);

        Player opponent = playerLobby.pullByName(request.queryParams(OPPONENT_ATTR));
        if(!opponent.isInGame()) {
            httpSession.attribute(VIEW_MODE, MasterEnum.ViewMode.PLAY);

            httpSession.attribute(ACTIVE_COLOR, MasterEnum.Color.RED);

            httpSession.attribute(RED_PLAYER, httpSession.attribute(GetHomeRoute.CUR_PLAYER_ATTR));

            httpSession.attribute(BOARD_VIEW_KEY, new BoardView(MasterEnum.Color.RED));

            httpSession.attribute(WHITE_PLAYER, opponent);

            httpSession.attribute(OPPONENT_ATTR, httpSession.attribute(WHITE_PLAYER));

            Game game = new Game();
            game.applyGame(httpSession.attribute(RED_PLAYER), httpSession.attribute(WHITE_PLAYER));

            game.applyBoard(((BoardView)httpSession.attribute(BOARD_VIEW_KEY)).getBoard(),
                    new Board(MasterEnum.Color.WHITE));
            httpSession.attribute(GetGameRoute.GAME_ATTR, game);
            gameLobby.addGame(game);
        } else {
            LOG.finer("Player selected is already in a game.");
            httpSession.attribute("err", "Player selected is already in a game, choose another player.");
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        }



        LOG.finer("GetGameRoute is invoked.");
        //
        if (httpSession.attribute("err") != null)
            httpSession.removeAttribute("err");
        response.redirect(WebServer.GAME_URL);
        halt();
        return null;
    }
}


