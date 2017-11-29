package com.webcheckers.ui;

import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static spark.Spark.halt;

/**
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 */
public class PostDeleteRoute implements Route {

    /**
     * Deletes the saved game from the player's stat page
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   doesn't need to return anything
     */
    @Override
    public Object handle(Request request, Response response){
        Session session = request.session();
        String name = request.queryParams(PostSaveGameRoute.SAVEGAME_ATTR);

        Player player = session.attribute(GetHomeRoute.CUR_PLAYER_ATTR);

        player.deleteGame(name);

        response.redirect(WebServer.STATS_URL);
        halt();
        return null;
    }
}
