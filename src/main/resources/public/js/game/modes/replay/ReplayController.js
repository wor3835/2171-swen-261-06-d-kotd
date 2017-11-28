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
  var AjaxUtils = require('../../util/AjaxUtils');

  // REPLAY mode states
  var ReplayModeStartState = require('./ReplayModeStartState');
  var EmptyTurnState = require('./EmptyTurnState');
  var BackState = require('./BackState');
  var StepState = require('./StepState');

  /**
   * Constructor function.
   */
  function ReplayController() {
    // Add the StatePattern mixin
    StatePatternMixin.call(this);
    // create states and a lookup map
    this.addStateDefinition(ReplayModeConstants.REPLAY_MODE_STARTING,
            new ReplayModeStartState(this));
    this.addStateDefinition(ReplayModeConstants.EMPTY_TURN,
            new EmptyTurnState(this));
    
    // Add the ModeControls mixin
    ControlsToolbarMixin.call(this);

    this.addButton(ReplayModeConstants.BACK_BUTTON_ID, 'Back', true,
            ReplayModeConstants.BACK_BUTTON_TOOLTIP, this.back);
    this.addButton(ReplayModeConstants.STEP_BUTTON_ID, 'Step', true,
            ReplayModeConstants.STEP_BUTTON_TOOLTIP, this.step);
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

  ReplayController.prototype.back = function back() {
    jQuery.post('./back', '')

    .done(handleResponse.bind(this))

    .fail(AjaxUtils.handleErrorResponse)

    .always(() => console.debug('Back Response Complete.'));

    function handleResponse(message){

       this.setState(ReplayModeConstants.EMPTY_TURN);
       if (message.type === 'info') {
            window.location = '/game';
       } else {
            alert('Back operation failed: ' +message.text);
       }
    }
  }

  ReplayController.prototype.step = function step() {
      jQuery.post('./step', '')

      .done(handleResponse.bind(this))

      .fail(AjaxUtils.handleErrorResponse)

      .always(() => console.debug('Step Response Complete.'));

      function handleResponse(message){

         this.setState(ReplayModeConstants.EMPTY_TURN);
         if (message.type === 'info') {
              window.location = '/game';
         }else {
              alert('Step operation failed: ' +message.text);
         }
      }
  }

  //
  // Public (external) methods
  //

  // export class constructor
  return ReplayController;
  
});
