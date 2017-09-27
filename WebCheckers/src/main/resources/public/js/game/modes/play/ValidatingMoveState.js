/**
 * This module exports the ValidatingMoveState class constructor.
 * 
 * This component is an concrete implementation of a state
 * for the Game view; this state represents the view state
 * in which the player has requested a move and is waiting
 * for server validation.
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
   */
  var ValidatingMoveState = function(controller) {
    // private attributes
    this._controller = controller;
  };

  //
  // Public (external) methods
  //

  /**
   * Get the name of this state.
   */
  ValidatingMoveState.prototype.getName = function getName() {
    return PlayModeConstants.VALIDATING_MOVE;
  }
  
  /**
   * Hook when entering this state.
   */
  ValidatingMoveState.prototype.onEntry = function onEntry() {
    var move = this._controller.getPendingMove();

    // 1) disable UI controls
    this._controller.disableButton(PlayModeConstants.BACKUP_BUTTON_ID);
    this._controller.disableButton(PlayModeConstants.SUBMIT_BUTTON_ID);
    this._controller.disableButton(PlayModeConstants.RESIGN_BUTTON_ID);
    
    // 2) disable all Pieces
    this._controller.disableAllMyPieces();
    
    // 3) ask the server to validate the pending move
    jQuery.post('/validateMove', JSON.stringify(move))
    // HTTP success handler
    .done(handleResponse.bind(this))
    // HTTP error handler
    .fail(AjaxUtils.handleErrorResponse)
    // always display a message that the Ajax call has completed.
    .always(() => console.debug('ValidateMove response complete.'));
    
    // helper function (Ajax success callback)
    function handleResponse(message) {
      this._controller.displayMessage(message);
      if (message.type === 'error') {
        this._controller.resetPendingMove();
        var hasMoves = this._controller.isTurnActive();
        this._controller.setState(hasMoves ? PlayModeConstants.STABLE_TURN : PlayModeConstants.EMPTY_TURN);
      }
      else {
        this._controller.addPendingMove();
        this._controller.setState(PlayModeConstants.STABLE_TURN);
      }
    }
  }

  // export class constructor
  return ValidatingMoveState;
  
});
