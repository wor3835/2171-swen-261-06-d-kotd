package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

/**
 * The UI Controller to GET the SignIn page.
 *
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 * @author <a href='mailto:gep2494@rit.edu'>George-Edward Pinal</a>
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 */
public class GetSignInRoute implements Route {

    /**
     * Global variables to keep track of the players, routes, and templates
     */
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());

    private final TemplateEngine templateEngine;

    static final String PLAYER_NAME_USED_ATTR = "playerExists";
    static final String VIEW_NAME = "signin.ftl";

    /**
     * Create the Spark Route (UI controller) for the
     * {@code GET /signing} HTTP request.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetSignInRoute(final TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        //
        LOG.config("GetSignInRoute is initialized.");
    }

    /**
     * Render the WebCheckers SignIn page.
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
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Sign in to play!");
        vm.put("error", " ");
        return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
    }

}