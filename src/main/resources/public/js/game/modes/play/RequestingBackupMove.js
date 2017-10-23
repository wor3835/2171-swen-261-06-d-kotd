/**
 * This module exports the RequestingBackupMove class constructor.
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
   */
  var RequestingBackupMove = function(controller) {
    // private attributes
    this._controller = controller;
  };

  //
  // Public (external) methods
  //

  /**
   * Get the name of this state.
   */
  RequestingBackupMove.prototype.getName = function getName() {
    return PlayModeConstants.REQUESTING_BACKUP_MOVE;
  }
  
  /**
   * Hook when entering this state.
   */
  RequestingBackupMove.prototype.onEntry = function onEntry() {
    // 1) disable UI controls
    this._controller.disableButton(PlayModeConstants.BACKUP_BUTTON_ID);
    this._controller.disableButton(PlayModeConstants.SUBMIT_BUTTON_ID);
    this._controller.disableButton(PlayModeConstants.RESIGN_BUTTON_ID);
    
    // 2) disable all Pieces
    this._controller.disableAllMyPieces();
    
    // 3) ask the server to backup from the most recent move
    jQuery.post('/backupMove', '')
    // HTTP success handler
    .done(handleResponse.bind(this))
    // HTTP error handler
    .fail(AjaxUtils.handleErrorResponse)
    // always display a message that the Ajax call has completed.
    .always(() => console.debug('BackupMove response complete.'));
    
    // helper function (Ajax success callback)
    function handleResponse(message) {
      this._controller.displayMessage(message);
      if (message.type === 'info') {
        this._controller.popMove();
        var isTurnActive = this._controller.isTurnActive();
        this._controller.setState(isTurnActive ? PlayModeConstants.STABLE_TURN : PlayModeConstants.EMPTY_TURN);
      }
      // handle error message
      else {
        // FIXME: unrecoverable right now
        alert('Backup move failed: ' + message.text);
      }
    }
  }

  // export class constructor
  return RequestingBackupMove;
  
});
