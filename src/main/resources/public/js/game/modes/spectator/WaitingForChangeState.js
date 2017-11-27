/**
 * This module exports the WaitForChangeState class constructor.
 *
 * This component is an concrete implementation of a state
 * for the Game view; this state represents the state in which
 * the view is waiting between calls to the server to check whether
 * a move has been made
 */

define(function(require){
    'use strict';

    var SpectatorModeConstants = require('./SpectatorModeConstants');

    function WaitForChangeState(controller) {
        this._controller = controller;
    };

    WaitForChangeState.prototype.getName = function getName(){
        return SpectatorModeConstants.WAITING_FOR_CHANGE;
    }

    WaitForChangeState.prototype.onEntry = function onEntry() {
        setTimeout(() => {this._controller.setState(SpectatorModeConstants.CHECKING_FOR_CHANGE); }, 5000);
    }

    return WaitForChangeState;
});