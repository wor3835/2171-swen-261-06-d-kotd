package com.webcheckers.ui;


import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import static spark.Spark.halt;
/**
 * Created by kzalb on 10/13/2017.
 * @author <a href='mailto:gep2494@rit.edu'>George-Edward Pinal</a>
 */
public class PostSignOutRoute implements Route {
    static final String NAME = "username";

    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    public PostSignOutRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        Objects.requireNonNull(playerLobby, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        LOG.config("PostSignOutRoute is initialized.");
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
        final String name = request.queryParams(NAME);



        LOG.finer("PostSignOutRoute is invoked.");
        if(name.matches("[A-Za-z0-9]*") && playerLobby.contains(name)) {

            // goes back to sign in page
            final Session session = request.session();
            final Player player = session.attribute(GetHomeRoute.PLAYER_KEY);
            player.setName(name);
            playerLobby.removePlayer(player);

            //
            LOG.finer("Player " +player.getName()+" signed out");
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        }

        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Sign out to end game");
        vm.put(GetSignOutRoute.PLAYER_NAME_USED_ATTR, false);
        return templateEngine.render(new ModelAndView(vm, GetSignOutRoute.VIEW_NAME));
    }
}
