/**
 * This module exports the CheckChangeState class constructor.
 *
 * This component is an concrete implementation of a state
 * for the Game view; this state represents the state in which
 * the view makes the Ajax call to the server to check whether
 * a change has happened.
 */
 define(function(require){
   'use strict';

   // imports
   var SpectatorModeConstants = require('./SpectatorModeConstants');
   var AjaxUtils = require('../../util/AjaxUtils');

 /**
  * Constructor function.
  *
  * @param {SpectatorController} controller
  *    The Spectator mode controller object.
  */
 function CheckChangeState(controller) {
   // private attributes
   this._controller = controller;
 };

 CheckChangeState.prototype.getName = function getName() {
    return SpectatorModeConstants.CHECKING_FOR_CHANGE;
 }

 CheckChangeState.prototype.onEntry = function onEntry() {
    jQuery.post('/checkChange', '')

    .done(handleResponse.bind(this))

    .fail(AjaxUtils.handleErrorResponse)

    .always(() => console.debug('Check change response complete.'));

    function handleResponse(message) {
        if (message.type === 'info') {
            if (message.text === 'true'){
                window.location = '/game';
            } else{
                this._controller.setState(SpectatorModeConstants.WAITING_FOR_CHANGE)
            }
        }
        else{
            alert('Check change failed: '+message.text);
        }
    }
 }

 return CheckChangeState;
});