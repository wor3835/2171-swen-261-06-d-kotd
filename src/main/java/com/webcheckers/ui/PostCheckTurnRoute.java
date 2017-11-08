package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import com.webcheckers.appl.Game;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.logging.Logger;

import static spark.Spark.halt;

/**
 * Created by wor3835 on 10/23/2017.
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class PostCheckTurnRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostCheckTurnRoute.class.getName());

    @Override
    public Object handle(Request request, Response response) {
        Session session = request.session();
        Player currentPlayer = session.attribute(GetHomeRoute.CUR_PLAYER_ATTR);
        Game game = session.attribute(GetGameRoute.GAME_ATTR);

        String text;

        if(currentPlayer == null)LOG.fine("Current player = null");

        Gson gson = new Gson();

        if((currentPlayer.equals(session.attribute(GetGameRoute.RED_PLAYER))
                && game.getActiveColor() == MasterEnum.Color.RED)
                || (currentPlayer.equals(session.attribute(GetGameRoute.WHITE_PLAYER))
                && game.getActiveColor() == MasterEnum.Color.WHITE)){


            session.attribute(GetGameRoute.ACTIVE_COLOR, game.getActiveColor());
            text = "true";
        }else {
            text = "false";
        }

        Message msg = new Message(text, MasterEnum.MessageType.info);

        return gson.toJson(msg);
    }
}
