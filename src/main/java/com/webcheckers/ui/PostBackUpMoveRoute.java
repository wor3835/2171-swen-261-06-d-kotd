package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static com.webcheckers.ui.PostStartRoute.VALIDATED;

/**
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class PostBackUpMoveRoute implements Route {

    /**
     * If possible, undoes a player's last move (before they submit it)
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   A message of type error if the move could not be undone, or of type info if the backup was a success
     */
    @Override
    public Object handle(Request request, Response response) {
        Gson gson = new Gson();
        Message t;
        Session session = request.session();
        if (session.attribute(PostValidateMoveRoute.MOVE_ATTR) == null)
            t = new Message("back up move failure", MasterEnum.MessageType.error);
        else {
            session.attribute(PostValidateMoveRoute.MOVE_ATTR, null);
            t = new Message("back up move successful", MasterEnum.MessageType.info);
        }

        session.attribute(VALIDATED, Boolean.FALSE);
        return gson.toJson(t);
    }
}