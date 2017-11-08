---
geometry: margin=1in
---
# PROJECT Design Documentation


# Team Information
* Team name: KOTD (Team d)
* Team members
    * Robert Kurdziel
    * AJ Nagashima
    * William Raffaelle
    * George Pinal
    * Kerri Zalba

## Executive Summary

This is a summary of the project.

### Purpose
Create an online checkers game.

### Glossary and Acronyms


| Term | Definition |

|------|------------|

| VO | Value Object |

| MVP | Minimum Viable Product |


## Requirements

The features we have in our application include, creating an alphanumeric username that is not already taken, challenge a player
to a game when they are both not already in one in which case it pulls both players into a game page together, when one player
is already in a game it makes the other player wait to play a game with them, allows the players to take turns moving their
pieces, allows players to capture each others pieces, allows pieces to become king when the end of the board is reached,
allows players to win or lose a game, and allows players to log out.


### Definition of MVP
A game of checkers can be played by the official American rule set between two people

### MVP Features
* Sign-in: Players must be able to sign into the checkers site
* Game start: Players must be able to start a game with another person
* Gameplay: Players must be able to play a game that follows the American checkers rule set

### Roadmap of Enhancements
* Spectate: Players must be able to watch an ongoing game between two other players
* Replay: Players must be able to save previous matches and watch it again


## Application Domain

The application domain has a GameLobby that keeps track of all the games server wide and a PlayerLobby that keeps track of
all the players server wide and a Game that keep track of the game and the players playing the game. There is also a MasterEnum
to keep track of all the enumerations in one place and a Message to give an error message or an info message to the player.

### Overview of Major Domain Areas

The application has a Board and a BoardView. The board is of type Space and the board view is used to show
the board for the current player. Each Space object on the board can contain a Piece if it is a white piece. Piece is
an abstract class; the two types of pieces are type Pawn and type King. 

### Details of each Domain Area

It is important to note that Space has its own validMoves method. This returns the valid moves a Piece can move from
that space. The method determines whether the piece is of type Pawn or King and carries out the respective move checking.

## Architecture

This section describes the application architecture.

### Summary

The application of the program spans three tiers: appl, com.webcheckers.model, com.webcheckers.ui. Users interact with the UI by moving 
pieces on the board. The application tier holds the logic that allows moves to be valid. The com.webcheckers.model holds the core 
domain, which in this case is the game board, its spaces, and the spaces pieces. 

![](board.png)


![](game.png)

### Overview of User Interface

The program begins by making a GET to the home page. The player signs in which invokes a POST sign in. If successful,
the player can then choose another signed in player to play against and a GET is made to the game page. Here the player
can make a move. First, there is a POST to validate the move, then there is a POST to submit the move.

### Tier UI

The UI tier allows the player to sign in, play games, make moves, and resign. GET routes handle getting the different
pages (Home, SignIn, Game). POST routes allow the player to sign in, make moves and resign.

### Tier com.webcheckers.model

The com.webcheckers.model tier hold the board object. The board is comprised of Spaces which all have a reference to the Piece object 
on them. The King and Pawn classes make sure that the proper move functionality in used.

### Tier appl

The appl tier manages the games that are created in GameLobby and PlayerLobby. When players sign in they are sent to the 
PlayerLobby, and when challenged that are sent to the GameLobby. The GameLobby must assign players to games and also handle
the case that somebody resigns. 

## Sub-system UI components

The UI components GetHomeRoute, GetSignInRoute, and GetGameRoute ensure that the user can view the game. 

### Purpose of the sub-system

These components provide a game page for the user to view and interact with. 

### Static models

![Board UML](board.png)

### Dynamic models