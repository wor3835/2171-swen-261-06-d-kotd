/**
 * This module exports the CheckMyTurnState class constructor.
 * 
 * This component is an concrete implementation of a state
 * for the Game view; this state represents the state in which
 * the view makes the Ajax call to the server to check whether
 * it's the current player's turn.
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
  function CheckMyTurnState(controller) {
    // private attributes
    this._controller = controller;
  };

  //
  // Public (external) methods
  //

  /**
   * Get the name of this state.
   */
  CheckMyTurnState.prototype.getName = function getName() {
    return PlayModeConstants.CHECK_MY_TURN;
  }
  
  /**
   * Method when entering this state.
   */
  CheckMyTurnState.prototype.onEntry = function onEntry() {
    this._controller.disableButton(PlayModeConstants.RESIGN_BUTTON_ID);
    // query the server if it's my turn
    jQuery.post('/checkTurn', '')
    // HTTP success handler
    .done(handleResponse.bind(this))
    // HTTP error handler
    .fail(AjaxUtils.handleErrorResponse)
    // always display a message that the Ajax call has completed.
    .always(() => console.debug('CheckTurn response complete.'));
    
    // helper function (Ajax success callback)
    function handleResponse(message) {
      if (message.type === 'info') {
        if (message.text === 'true') {
          // tell the browser to redisplay the Game View to get the updated board
          window.location = '/game';
        } else {
          this._controller.setState(PlayModeConstants.WAIT_FOR_MY_TURN)
        }
      }
      // handle error message
      else {
        // FIXME: unrecoverable right now
        alert('Check turn action failed: ' + message.text);
      }
    }
  }

  // export class constructor
  return CheckMyTurnState;
  
});
