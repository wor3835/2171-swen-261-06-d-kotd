package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.*;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;
import spark.Session;

import static spark.Spark.halt;

/**
 * The UI Controller to GET the Game page.
 *
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class GetGameRoute implements Route {

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

    static final String GAME_ATTR = "game";

    /**
     * Create the Spark Route (UI controller) for the
     * {@code GET /} HTTP request.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetGameRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameLobby gameLobby) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameLobby = gameLobby;
        //
        LOG.config("GetGameRoute is initialized.");
    }

    /**
     * Render the WebCheckers Home page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        final Session httpSession = request.session();

        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome!");

        Game game = httpSession.attribute(GAME_ATTR);

        ArrayList<Move> moves = ((BoardView)httpSession.attribute(BOARD_VIEW_KEY)).getBoard().getMoves(
                ((Player)httpSession.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).getPosList());
        if(game.isGameOver()){
            String name = ((Player) httpSession.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).getName();

            httpSession.attribute(GetEndGameRoute.WINNER_ATTR, name);

            response.redirect(WebServer.ENDGAME_URL);
            halt();
            return null;
        } else if(moves.size() == 0) {

            String name = ((Player) httpSession.attribute(GetGameRoute.OPPONENT_ATTR)).getName();
            httpSession.attribute(GetEndGameRoute.WINNER_ATTR, name);

            game.endGame();

            gameLobby.removeGame(game);

            response.redirect(WebServer.ENDGAME_URL);
            halt();
            return null;
        }else if (game.getP1().getPosList().isEmpty() || game.getP2().getPosList().isEmpty()){
            String name = ((Player) httpSession.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).getName();

            httpSession.attribute(GetEndGameRoute.WINNER_ATTR, name);

            response.redirect(WebServer.ENDGAME_URL);
            halt();
            return null;
        }

        vm.put(GetHomeRoute.CUR_PLAYER_ATTR, httpSession.attribute(GetHomeRoute.CUR_PLAYER_ATTR));

        httpSession.attribute(GetHomeRoute.PLAYER_LOBBY_KEY, playerLobby);
        vm.put(GetHomeRoute.PLAYER_LOBBY_KEY, httpSession.attribute(GetHomeRoute.PLAYER_LOBBY_KEY));

        /*if(httpSession.attribute(BOARD_VIEW_KEY)==null) {
            httpSession.attribute(BOARD_VIEW_KEY, new BoardView());
        }*/

//        Game game = httpSession.attribute(GAME_ATTR);
//        BoardView boardView = new BoardView(null,
//                httpSession.attribute(GetHomeRoute.CUR_PLAYER_ATTR).equals(httpSession.attribute(RED_PLAYER)) ?
//                        game.getB1() : game.getB2());
//        httpSession.attribute(BOARD_VIEW_KEY, boardView);
        vm.put(BOARD_VIEW_KEY, httpSession.attribute(BOARD_VIEW_KEY));

        vm.put(VIEW_MODE, httpSession.attribute(VIEW_MODE));

        vm.put(RED_PLAYER, httpSession.attribute(RED_PLAYER));

        vm.put(WHITE_PLAYER, httpSession.attribute(WHITE_PLAYER));

        vm.put(WHITE_PLAYER, httpSession.attribute(WHITE_PLAYER));
        
        vm.put(ACTIVE_COLOR, httpSession.attribute(ACTIVE_COLOR));

        LOG.finer("GetGameRoute is invoked.");
        //

        return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
    }

}