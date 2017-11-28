/**
 * This module exports the EmptyTurnState class constructor.
 *
 * This component is an concrete implementation of a state
 * for the Game view; this state represents the view state
 * in which no new move is displayed
 */

define(function(require){
    'use strict';

    var ReplayModeConstants = require('./ReplayModeConstants');

    var EmptyTurnState = function(controller){
        this._controller = controller;
    }

    EmptyTurnState.prototype.getName = function getName(){
        return ReplayModeConstants.EMPTY_TURN;
    }

    EmptyTurnState.prototype.onEntry = function onEntry() {
    }

    return EmptyTurnState;
});