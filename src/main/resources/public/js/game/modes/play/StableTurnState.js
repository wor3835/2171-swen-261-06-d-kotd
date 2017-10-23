/**
 * This module exports the StableTurnState class constructor.
 * 
 * This component is an concrete implementation of a state
 * for the Game view; this state represents the view state
 * in which the player has created a non-empty Turn.  From
 * this state the user may request another move or to submit
 * the current set of moves as a single turn.
 */
define(function(require){
  'use strict';
  
  // imports
  var PlayModeConstants = require('./PlayModeConstants');
  var AjaxUtils = require('../../util/AjaxUtils');

  /**
   * Constructor function.
   * 
   * @param {PlayController} controller
   *    The Play mode controller object.
   * @param {GameView} view
   *    The Game view object.
   */
  var StableTurnState = function(controller, view) {
    // private attributes
    this._controller = controller;
    this._view = view;
  };

  //
  // Public (external) methods
  //

  /**
   * Get the name of this state.
   */
  StableTurnState.prototype.getName = function getName() {
    return PlayModeConstants.STABLE_TURN;
  }
  
  /**
   * Hook when entering this state.
   */
  StableTurnState.prototype.onEntry = function onEntry() {
    // enable all UI controls
    this._controller.enableButton(PlayModeConstants.BACKUP_BUTTON_ID);
    this._controller.enableButton(PlayModeConstants.SUBMIT_BUTTON_ID);
    // re-enable active Piece
    this._controller.enableActivePiece();
  }

  /**
   * The player may request an additional move for a given turn.
   */
  StableTurnState.prototype.requestMove = function requestMove(pendingMove) {
    // register the requested move
    this._controller.setPendingMove(pendingMove);
    // and change the data to Pending
    this._controller.setState(PlayModeConstants.VALIDATING_MOVE);
  };

  /**
   * Backup a single move.
   */
  StableTurnState.prototype.backupMove = function backupMove() {
    this._controller.setState(PlayModeConstants.REQUESTING_BACKUP_MOVE);
  }

  /**
   * Submit the Turn to the server.
   * 
   * This action leaves the current Game view and retrieves an
   * updated Game view from the server.
   */
  StableTurnState.prototype.submitTurn = function submitTurn() {
    jQuery.post('/submitTurn', '')
    // HTTP success handler
    .done(handleResponse.bind(this))
    // HTTP error handler
    .fail(AjaxUtils.handleErrorResponse)
    // always display a message that the Ajax call has completed.
    .always(() => console.debug('SubmitTurn response complete.'));
    
    // helper function (Ajax success callback)
    function handleResponse(message) {
      this._controller.displayMessage(message);
      if (message.type === 'info') {
        window.location = '/game';
      }
      // handle error message
      else {
        // FIXME: unrecoverable right now
        alert('Submit turn failed: ' + message.text);
      }
    }
  }

  // export class constructor
  return StableTurnState;
  
});
