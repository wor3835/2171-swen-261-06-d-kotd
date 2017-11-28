package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Board;
import com.webcheckers.model.Move;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

/**
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class PostStepRoute implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();

        Game game = session.attribute(GetGameRoute.GAME_ATTR);

        int currentIdx = session.attribute(PostReplayRoute.CURRENT_IDX_ATTR);
        currentIdx++;
        if(game.movesListSize()==currentIdx){
            session.attribute(PostReplayRoute.CURRENT_IDX_ATTR, currentIdx);
            Message msg = new Message("No Moves left", MasterEnum.MessageType.info);
            Gson gson = new Gson();
            return gson.toJson(msg);
        }
        Move move = game.getMove(currentIdx);

        Board b = game.getB();

        if((game.getP1().equals(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)) && currentIdx%2==1) ||
                (game.getP2().equals(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)) && currentIdx%2==0) )
            b.inverseMove(move, null);
        else
            b.makeMove(move, null);

        session.attribute(PostReplayRoute.CURRENT_IDX_ATTR, currentIdx);
        Message msg = new Message("Success", MasterEnum.MessageType.info);

        Gson gson = new Gson();
        return gson.toJson(msg);
    }
}
