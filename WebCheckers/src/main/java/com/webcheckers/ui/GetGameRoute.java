package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.BoardView;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;
import spark.Session;

import com.webcheckers.model.Player;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 */
public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    static final String VIEW_NAME = "game.ftl";
    static final String BOARD_VIEW_KEY = "board";

    static final String VIEW_MODE = "viewMode"; // mode of the Game View
    static final String RED_PLAYER = "redPlayer";
    static final String WHITE_PLAYER = "whitePlayer";
    static final String ACTIVE_COLOR = "activeColor";

    private enum ViewMode {
        PLAY, SPECTATOR, REPLAY
    }

    private enum activeColor {
        RED, WHITE
    }

    /**
     * Create the Spark Route (UI controller) for the
     * {@code GET /} HTTP request.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetGameRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
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

        vm.put(GetHomeRoute.CUR_PLAYER_ATTR, httpSession.attribute(GetHomeRoute.CUR_PLAYER_ATTR));

        httpSession.attribute(GetHomeRoute.PLAYER_LOBBY_KEY, playerLobby);
        vm.put(GetHomeRoute.PLAYER_LOBBY_KEY, httpSession.attribute(GetHomeRoute.PLAYER_LOBBY_KEY));

        if(httpSession.attribute(BOARD_VIEW_KEY)==null) {
            httpSession.attribute(BOARD_VIEW_KEY, new BoardView());
        }
        vm.put(BOARD_VIEW_KEY, httpSession.attribute(BOARD_VIEW_KEY));

        httpSession.attribute(VIEW_MODE, ViewMode.PLAY);
        vm.put(VIEW_MODE, httpSession.attribute(VIEW_MODE));

        httpSession.attribute(RED_PLAYER, httpSession.attribute(GetHomeRoute.CUR_PLAYER_ATTR));
        vm.put(RED_PLAYER, httpSession.attribute(RED_PLAYER));

        httpSession.attribute(WHITE_PLAYER, httpSession.attribute(GetHomeRoute.CUR_PLAYER_ATTR));
        vm.put(WHITE_PLAYER, httpSession.attribute(WHITE_PLAYER));
        /*if(!((Player)httpSession.attribute(WHITE_PLAYER)).isInGame()) {
            httpSession.attribute(WHITE_PLAYER, httpSession.attribute(GetHomeRoute.CUR_PLAYER_ATTR));
            vm.put(WHITE_PLAYER, httpSession.attribute(WHITE_PLAYER));
        } else {
            LOG.finer("Player selected is already in a game.");
            response.redirect(WebServer.HOME_URL);
        }*/

        httpSession.attribute(ACTIVE_COLOR, activeColor.RED);
        vm.put(ACTIVE_COLOR, httpSession.attribute(ACTIVE_COLOR));

        LOG.finer("GetGameRoute is invoked.");
        //

        return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
    }

}