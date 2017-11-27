package com.webcheckers.ui;

import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static spark.Spark.halt;

/**
 * Created by arthu on 11/26/2017.
 */
public class PostDeleteRoute implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        String name = request.queryParams(PostSaveGameRoute.SAVEGAME_ATTR);

        Player player = session.attribute(GetHomeRoute.CUR_PLAYER_ATTR);

        player.deleteGame(name);

        response.redirect(WebServer.STATS_URL);
        halt();
        return null;
    }
}
