package com.webcheckers.ui;

import spark.TemplateEngine;<<<<<<< HEAD
=======
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

>>>>>>> 91bfc3814972c8d3196aa1c26a47f1ffee69f14a
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
<<<<<<< HEAD
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

/**
 * Created by kzalb on 10/11/2017.
 */
public class GetSignOutRoute implements Route{
    private static final Logger LOG = Logger.getLogger(GetSignOutRoute.class.getName());

    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) for the
     * {@code GET /signing} HTTP request.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetSignOutRoute(final TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        //
=======

/**
 * Created by wor3835 on 10/11/2017.
 */
public class GetSignOutRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;
    private final Player player;

    static final String VIEW_NAME = "signin.ftl";

    public GetSignOutRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, Player player) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        Objects.requireNonNull(playerLobby, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.player = player;
>>>>>>> 91bfc3814972c8d3196aa1c26a47f1ffee69f14a
        LOG.config("GetSignOutRoute is initialized.");
    }

    /**
     * Render the WebCheckers SignIn page.
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
<<<<<<< HEAD
        LOG.finer("GetSignOutRoute is invoked.");
        //
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Sign out to exit");
        return templateEngine.render(new ModelAndView(vm , "home.ftl"));
=======
        playerLobby.removePlayer(player);

        LOG.finer("PostSignInRoute is invoked.");

        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Sign in to Play!");
        return templateEngine.render(new ModelAndView(vm, "signin.ftl"));
>>>>>>> 91bfc3814972c8d3196aa1c26a47f1ffee69f14a
    }
}
