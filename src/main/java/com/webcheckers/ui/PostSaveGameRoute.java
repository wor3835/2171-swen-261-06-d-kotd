package com.webcheckers.ui;

import com.webcheckers.appl.Game;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

/**
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class PostSaveGameRoute implements Route {
    private final String NAME = "gamename";
    static final String SAVEGAME_ATTR = "savegame";

    private final TemplateEngine templateEngine;

    public PostSaveGameRoute(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    /**
     * Saves the game to be available to replay in the stats page
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   redirects the player to the home screen
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        final String name = request.queryParams(NAME);
        Session session = request.session();

        Player currentPlayer = session.attribute(GetHomeRoute.CUR_PLAYER_ATTR);
        Game game = session.attribute(GetGameRoute.GAME_ATTR);

        if(!name.matches("[A-Za-z0-9]*")){
            session.attribute("error", PostSignInRoute.BAD_CHAR);
            response.redirect(WebServer.ENDGAME_URL);
            halt();
            return null;
        }else if(currentPlayer.saveGame(name, game)==null){
            session.attribute("error", PostSignInRoute.NAME_USED);
            response.redirect(WebServer.ENDGAME_URL);
            halt();
            return null;
        }
        response.redirect(WebServer.HOME_URL);
        halt();
        return null;
    }
}
