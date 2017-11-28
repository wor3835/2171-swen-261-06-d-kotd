/**
 * This module exports a map of constants used in the REPLAY mode.
 */
define(function(){
    'use strict';

    /**
     * This module is a map of constant symbols to their names.
     * Used in methods to change GameView Replay mode states.
     */
    return {

      //states
      REPLAY_MODE_STARTING: 'REPLAY_MODE_STARTING',
      EMPTY_TURN: 'Empty Turn',
      BACKING_UP: 'Backing up',
      STEPPING_FORWARD: 'Stepping Forward'
      // TODO more State constants

      //buttons
      ,BACK_BUTTON_ID: 'backBtn'
      ,BACK_BUTTON_TOOLTIP: 'Go back one move'
      ,STEP_BUTTON_ID: 'stepBtn'
      ,STEP_BUTTON_TOOLTIP: 'Step forward one move'

    };
});