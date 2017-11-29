package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

/**
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 */
public class PostCheckChangeRoute implements Route {

    /**
     * Checks if the game that is being spectated has changed active colors
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   A message of type info, false if there has not been a change, true if there has been a change
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();

        Game game = session.attribute(GetGameRoute.GAME_ATTR);
        Message msg;

        if(game.getActiveColor() == session.attribute(GetGameRoute.ACTIVE_COLOR)){
            msg = new Message("false", MasterEnum.MessageType.info);
        } else {
            session.attribute(GetGameRoute.ACTIVE_COLOR, game.getActiveColor());
            msg = new Message("true", MasterEnum.MessageType.info);
        }

        Gson gson = new Gson();
        return gson.toJson(msg);
    }
}
