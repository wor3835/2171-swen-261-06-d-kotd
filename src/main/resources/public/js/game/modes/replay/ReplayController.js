/**
 * This module exports the ReplayController class constructor.
 * 
 * This component does...
 */
define(function(require){
  'use strict';
  
  // imports
  var StatePatternMixin = require('../../util/StatePatternMixin');
  var ControlsToolbarMixin = require('../../util/ControlsToolbarMixin');
  var ReplayModeConstants = require('./ReplayModeConstants');
  
  // REPLAY mode states
  var ReplayModeStartState = require('./ReplayModeStartState');

  /**
   * Constructor function.
   */
  function ReplayController() {
    // Add the StatePattern mixin
    StatePatternMixin.call(this);
    // create states and a lookup map
    this.addStateDefinition(ReplayModeConstants.REPLAY_MODE_STARTING,
            new ReplayModeStartState(this));
    
    // Add the ModeControls mixin
    ControlsToolbarMixin.call(this);
    // TODO: create mode control buttons

    // Public (internal) methods

    /**
     * Start Replay mode.
     */
    this.startup = function startup() {
      // start Replay mode
      this.setState(ReplayModeConstants.REPLAY_MODE_STARTING);
    }
    
  };

  //
  // Public (external) methods
  //

  // export class constructor
  return ReplayController;
  
});
