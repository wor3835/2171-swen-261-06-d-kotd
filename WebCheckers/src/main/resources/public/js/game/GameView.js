/**
 * This module exports the GameView class constructor.
 * 
 * This component manages the Client-side behavior of the Game view.
 */
define(function(require){
  'use strict';
  
  // imports
  var BoardController = require('./BoardController');
  var GameState = require('./model/GameState');
  
  // MVP imports
  var PlayController = require('./modes/play/PlayController');

  // Project Enhancement imports
  var ReplayController = require('./modes/replay/ReplayController');
  var SpectatorController = require('./modes/spectator/SpectatorController');
  // more?

  //
  // Constants
  //

  var PLAY_MODE = 'PLAY';
  var SPECTATOR_MODE = 'SPECTATOR';
  var REPLAY_MODE = 'REPLAY';

  //
  // Constructor
  //

  /**
   * Constructor function.
   */
  function GameView(gameData) {
    // private data
    this._gameState = new GameState(gameData);
    this._boardController = new BoardController(this._gameState);
    this._mode = gameData.viewMode;
  }

  //
  // Public (external) methods
  //

  GameView.prototype.startup = function startup() {
    // initialize the Info fieldset with the player's names
    this.setRedPlayersName(this._gameState.getRedPlayer());
    this.setWhitePlayersName(this._gameState.getWhitePlayer());
    this.setTurnFlasher();
    
    // launch the Mode controller
    var modeController;
    switch (this._mode) {
    case PLAY_MODE:
      console.debug('Play mode');
      modeController = new PlayController(this, this._boardController, this._gameState);
      break;
    case SPECTATOR_MODE:
      console.debug('Spectator mode');
      modeController = new SpectatorController(this);
      break;
    case REPLAY_MODE:
      console.debug('Replay mode');
      modeController = new ReplayController(this);
      break;
    default:
      alert('Unknown view module: ' + this._mode);
      return;
    }
    modeController.startup();
  };

  GameView.prototype.setHelperText = function setHelperText(helpTextHTML) {
    jQuery("p#help_text").html(helpTextHTML);
  };

  GameView.prototype.setRedPlayersName = function setRedPlayersName(name) {
    jQuery("fieldset#game-info table[data-color='RED'] td.name").text(name);
  };

  GameView.prototype.setWhitePlayersName = function setWhitePlayersName(name) {
    jQuery("fieldset#game-info table[data-color='WHITE'] td.name").text(name);
  };

  GameView.prototype.setTurnFlasher = function setTurnFlasher() {
    var activeColor = this._gameState.isRedsTurn() ? 'RED' : 'WHITE';
    var inactiveColor = this._gameState.isRedsTurn() ? 'WHITE' : 'RED';
    jQuery("fieldset#game-info table[data-color='" + activeColor + "']").addClass('isMyTurn');
    jQuery("fieldset#game-info table[data-color='" + inactiveColor + "']").removeClass('isMyTurn');
  };

  GameView.prototype.displayMessage = function displayMessage(message) {
    jQuery('#message').attr('class', message.type).html(message.text).slideDown(400);
  };

  // export class constructor
  return GameView;
  
});

