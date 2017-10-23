/**
 * This module exports the GameState class constructor.
 * 
 * This Value Object holds a snapshot of the state of the checkers game.
 * Information about who the players are, the current player and whose
 * turn is it.
 */
define(function(require){
  'use strict';
  
  /**
   * Constructor function.
   */
  function GameState(gameData) {

    // public (internal) methods
    
    this.getCurrentPlayer = function getCurrentPlayer() {
      return gameData.currentPlayer;
    };

    this.getOpponentPlayer = function getOpponentPlayer() {
      return (gameData.currentPlayer === gameData.redPlayer)
         ? gameData.whitePlayer : gameData.redPlayer;
    };

    this.getRedPlayer = function getRedPlayer() {
      return gameData.redPlayer;
    };

    this.getWhitePlayer = function getWhitePlayer() {
      return gameData.whitePlayer;
    };
    
    this.isRedsTurn = function isRedsTurn() {
      return gameData.activeColor === 'RED';
    };
    
    this.isPlayerRed = function isPlayerRed() {
      return gameData.redPlayer === gameData.currentPlayer;
    };

    this.isPlayerWhite = function isPlayerWhite() {
      return gameData.whitePlayer === gameData.currentPlayer;
    };

  };

  //
  // Public (external) methods
  //

  /**
   * Queries whether it's the current player's turn.
   */
  GameState.prototype.isMyTurn = function isMyTurn() {
    return (this.isPlayerRed() && this.isRedsTurn())
    || (this.isPlayerWhite() && !this.isRedsTurn());
  };
  
  // export class constructor
  return GameState;
  
});
