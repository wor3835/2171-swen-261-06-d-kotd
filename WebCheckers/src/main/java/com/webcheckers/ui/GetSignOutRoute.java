package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;
import static spark.Spark.halt;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


/**
 * Created by wor3835 on 10/11/2017.
 * @author <a href='mailto:gep2494@rit.edu'>George-Edward Pinal</a>
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 */
public class GetSignOutRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;
    //private final Player player;

    static final String PLAYER_NAME_USED_ATTR = "playerExists";
    static final String VIEW_NAME = "signin.ftl";

    public GetSignOutRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        //this.player = player;

        LOG.config("GetSignOutRoute is initialized.");
    }

    /**
     * Render the WebCheckers SignIn page.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        final Session session = request.session();
        LOG.finer("GetSignOutRoute is invoked.");

        playerLobby.removePlayer(session.attribute(GetHomeRoute.PLAYER_KEY));
        
        session.invalidate();
        response.redirect(WebServer.HOME_URL);
        halt();
        return null;

    }
}