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
 * The {@code POST /guess} route handler.
 *
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 */
public class PostSignInRoute implements Route {

    static final String NAME = "username";

    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    public PostSignInRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        Objects.requireNonNull(playerLobby, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        LOG.config("PostSignInRoute is initialized.");
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



        LOG.finer("PostSignInRoute is invoked.");
        if(!name.matches("[A-Za-z0-9]*"))
        {
            Map<String, Object> vm = new HashMap<>();
            vm.put("error", "Name must be alphanumeric, try another name");
            vm.put("title", "Sign in to Play!");
            vm.put(GetSignInRoute.PLAYER_NAME_USED_ATTR, false);
            return templateEngine.render(new ModelAndView(vm, GetSignInRoute.VIEW_NAME));
        }
        else if(playerLobby.contains(name)){
            Map<String, Object> vm = new HashMap<>();
            vm.put("error", "Name taken, try another name");
            vm.put("title", "Sign in to Play!");
            vm.put(GetSignInRoute.PLAYER_NAME_USED_ATTR, false);
            return templateEngine.render(new ModelAndView(vm, GetSignInRoute.VIEW_NAME));
        }
        else{
            // goes back to sign in page
            final Session session = request.session();

            final Player player = playerLobby.playerSignin(name);
            session.attribute(GetHomeRoute.PLAYER_KEY, player);
            //
            LOG.finer("Player " +player.getName()+" signed in");
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        }
    }
}
