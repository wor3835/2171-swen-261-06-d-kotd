/**
 * This module exports the SpectatorController class constructor.
 * 
 * This component does...
 */
define(function(require){
  'use strict';
  
  // imports
  var StatePatternMixin = require('../../util/StatePatternMixin');
  var ControlsToolbarMixin = require('../../util/ControlsToolbarMixin');
  var SpectatorModeConstants = require('./SpectatorModeConstants');
  
  // SPECTATOR mode states
  var SpectatorModeStartState = require('./SpectatorModeStartState');
  var CheckChangeState = require('./CheckChangeState');
  var WaitingForChangeState = require('./WaitingForChangeState');

  /**
   * Constructor function.
   */
  function SpectatorController() {
    // Add the StatePattern mixin
    StatePatternMixin.call(this);
    
    // create states and a lookup map
    this.addStateDefinition(SpectatorModeConstants.SPECTATOR_MODE_STARTING,
            new SpectatorModeStartState(this));

    this.addStateDefinition(SpectatorModeConstants.CHECKING_FOR_CHANGE,
            new CheckChangeState(this));
    this.addStateDefinition(SpectatorModeConstants.WAITING_FOR_CHANGE,
            new WaitingForChangeState(this));

    // Add the ModeControls mixin
    ControlsToolbarMixin.call(this);

    // Public (internal) methods

    /**
     * Start Spectator mode.
     */
    this.startup = function startup() {
      // start Spectator mode
      this.setState(SpectatorModeConstants.SPECTATOR_MODE_STARTING);
    }
    
  };

  //
  // Public (external) methods
  //

  // export class constructor
  return SpectatorController;
  
});
