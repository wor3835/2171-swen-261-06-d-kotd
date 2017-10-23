/**
 * This file contains the launch code for the Game view.
 */
(function() {
  // not strict to get the Game state object from the HTML response
  //'use strict';
  
  // This 'this' variable here holds the browser's window object
  // and this is where the 'game.ftl' (FreeMarker) template holds
  // some global Game state data such as the names and piece colors
  // for both players.
  var gameState = this.gameState;

  define(function(require){
    'use strict';

    // imports
    var GameView = require('./GameView');
    
    // configure Ajax to always use JSON responses
    $.ajaxSetup({
      contentType: "application/json; charset=utf-8",
      dataType: "json"
    });
    
    // create the View object
    $(document).ready(function() {
      var view = new GameView(gameState);
      view.startup();
    });
  });

})();
