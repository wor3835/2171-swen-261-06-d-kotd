package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import spark.*;

/**
 * The UI Controller to GET the SignIn page.
 *
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 * @author <a href='mailto:gep2494@rit.edu'>George-Edward Pinal</a>
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class GetStatsRoute implements Route {

    /**
     * Global variables to keep track of the players, routes, and templates
     */
    private static final Logger LOG = Logger.getLogger(GetStatsRoute.class.getName());

    private final TemplateEngine templateEngine;

    static final String VIEW_NAME = "stats.ftl";

    /**
     * Create the Spark Route (UI controller) for the
     * {@code GET /stats} HTTP request.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetStatsRoute(final TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        //
        LOG.config("GetStatsRoute is initialized.");
    }

    /**
     * Render the WebCheckers Stats page.
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
        LOG.finer("GetSignInRoute is invoked.");
        //
        Session session = request.session();

        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "My Stats");
        vm.put(GetHomeRoute.CUR_PLAYER_ATTR, session.attribute(GetHomeRoute.CUR_PLAYER_ATTR));
        return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
    }

}