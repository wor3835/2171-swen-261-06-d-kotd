package com.webcheckers.ui;

import com.webcheckers.appl.Game;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

/**
 * Created by arthu on 11/24/2017.
 */
public class PostSaveGameRoute implements Route {
    private final String NAME = "gamename";
    static final String SAVEGAME_ATTR = "savegame";

    private final TemplateEngine templateEngine;

    public PostSaveGameRoute(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final String name = request.queryParams(NAME);
        Session session = request.session();

        Player currentPlayer = session.attribute(GetHomeRoute.CUR_PLAYER_ATTR);
        Game game = session.attribute(GetGameRoute.GAME_ATTR);

        if(!name.matches("[A-Za-z0-9]*")){
            Map<String, Object> vm = new HashMap<>();
            vm.put("error", PostSignInRoute.BAD_CHAR);
            vm.put("title", request.queryParams("title"));
            vm.put("message", request.queryParams("message"));
            return templateEngine.render(new ModelAndView(vm, GetEndGameRoute.VIEW_NAME));
        }else if(currentPlayer.saveGame(name, game)==null){
            Map<String, Object> vm = new HashMap<>();
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
