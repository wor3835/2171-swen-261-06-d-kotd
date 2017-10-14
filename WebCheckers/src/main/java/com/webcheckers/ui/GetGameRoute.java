package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.PlayerLobby;
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
    static final String PLAYER_KEY = "player";
    static final String PLAYER_LOBBY_KEY = "playerLobby";

    static final String CUR_PLAYER_ATTR = "currentPlayer";
    static final String RED_PLAYER = "redPlayer";
    static final String WHITE_PLAYER = "whitePlayer";
    static final String ACTIVE_COLOR = "activeColor";

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

        if(httpSession.attribute(PLAYER_KEY)!=null)
            vm.put(CUR_PLAYER_ATTR, httpSession.attribute(PLAYER_KEY));

        httpSession.attribute(PLAYER_LOBBY_KEY, playerLobby);
        vm.put(PLAYER_LOBBY_KEY, httpSession.attribute(PLAYER_LOBBY_KEY));

        /*
        httpSession.attribute(PLAYER_KEY);
        vm.put(CUR_PLAYER_ATTR, httpSession.attribute(RED_PLAYER));

        httpSession.attribute(PLAYER_KEY);
        vm.put(CUR_PLAYER_ATTR, httpSession.attribute(ACTIVE_COLOR));

        httpSession.attribute(PLAYER_KEY);
        vm.put(ACTIVE_COLOR, httpSession.attribute(WHITE_PLAYER));
        */

        LOG.finer("GetGameRoute is invoked.");
        //

        return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
    }

}