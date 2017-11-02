package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import com.webcheckers.model.*;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

/**
 * Created by wor3835 on 10/25/2017.
 */
public class PostSubmitTurnRoute implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();

        Move move = session.attribute(PostValidateMoveRoute.MOVE_ATTR);
        Game game = session.attribute(GetGameRoute.GAME_ATTR);
        Board b1 = game.getB1();
        Board b2 = game.getB2();
        Player opponent = session.attribute(GetGameRoute.OPPONENT_ATTR);
        if(session.attribute(GetGameRoute.ACTIVE_COLOR) == MasterEnum.Color.RED) {
            session.attribute(GetGameRoute.ACTIVE_COLOR, MasterEnum.Color.WHITE);
            b1.makeMove(move, opponent);
            b2.inverseMove(move, opponent);
        } else {
            session.attribute(GetGameRoute.ACTIVE_COLOR, MasterEnum.Color.RED);
            b1.inverseMove(move, opponent);
            b2.makeMove(move, opponent);
        }
        game.movesList.add(move);
        game.switchActive();

        Message msg = new Message("turn processed", MasterEnum.MessageType.info);
        //FIXME: Needs to return error. I asked on the discussion board what error should be checked.
        Gson gson = new Gson();

        return gson.toJson(msg);
    }
}
