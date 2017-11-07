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

This section describes the features of the application.


### Definition of MVP
A game of checkers can be played by the official American rule set between two people

### MVP Features
* Sign-in: Players must be able to sign into the checkers site
* Game start: Players must be able to start a game with another person
* Gameplay: Players must be able to play a game that follows the American checkers rule set
* Resignation: Players must be able to resign from a game they no longer wish to play

### Roadmap of Enhancements
* Spectate: Players must be able to watch an ongoing game between two other players
* Replay: Players must be able to save previous matches and watch it again


## Application Domain

This section describes the application domain.

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

The application of the program spans three tiers: appl, model, ui. Users interact with the UI by moving 
pieces on the board. The application tier holds the logic that allows moves to be valid. The model holds the core 
domain, which in this case is the game board, its spaces, and the spaces pieces. 

![](https://www.draw.io/?lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&title=titled.html#R7Vtdb%2BI4FP01SLsPVHFCQngsdGZ3tR0JTUezs48mMWCNiaPEFJhfv9exDSQObQqBdqVUSI2vbxz7nmPfj0DPm6y2f2Q4XX7hMWE914m3Pe%2Bh57rIDV34JyU7JfFHoRIsMhprpYPgif4iWuho6ZrGJC8pCs6ZoGlZGPEkIZEoyXCW8U1Zbc5Z%2BakpXhBL8BRhZkv%2FobFYKmnoOwf5n4QulubJyNE9K2yUtSBf4phvjkTep543yTgX6mq1nRAmjWfsou77fKJ3P7GMJKLJDcR1ZkEQ4cCP0GiE%2BmioRnjGbK0XO8WbpDdxe%2FdmymJn7JBv6IrhBFrjOU%2FEk%2B5B0MaMLhK4jmAiJAPBM8kEBRPe6w7BU5BGS8riR7zjazndXODop2mNlzyjv2BYzPSY0J0JzQY3KGk8yTtB7IA0IznoTI0NUEX0BW9Lio84F1oQccZwmtPZfhkrnC1oMuZC8JVWMov%2BTBmbcMazwhbevPiDfm1AWC7ZnkQF7bGGTUL4iohsByr6Bi%2FQttb7Y%2BAEqr05sA0ZneUR00KthzXBF%2FuhDxyAC02DhpQILUr0ofltl0LrHq5Isl5Z1IDFiwKyjP8kxkwJV1w5spwWGbowMhcnyZKnOKLJ4rHQeRgcJF%2B1BaSIw71zVuypJY1jkkigucACK1QlhCmniShM5I%2FhA4acOHd%2Bz4eJT6CNDm34SPVMTHgCa8G0AJAAZTZE0qYZ2vW7zIZfw%2B02RNu9BtojC20LW0YLzBS25gBEZwG7AogYOSD5TQL90EcW2p6NtleDLMMzwqY8p4JyOX6mdCuIvweovttwC7ePqXE2JUzH0KLxF9gu%2BW%2B%2Fq438SGH1bsCkCabSYNBYHOzR7e5WiGBAv8Xu9kjouR6az8O5T8KwP7CI8DcYuPPrN%2FLrgfOOfr3KBb9z6xdu%2FNrd9SG8enVmQefUrwHpDX16dWJ2nta59Pfa2e%2Fp0e3kbEpJRDqXfiOXPgjLLt0dNDwTRoPTZNBP%2B0oigZMFrPBNj2tCPcwA6gQLMubrJM4tBu4XehYp63LIcU1wAZPykFMAIbs1RF3wcc4RFbYSfHgvsPLcIwrVpZ9d9HExpk3dzhWiD4S68OPD7O0gvF34YZWW7IqCBS1J4nv50gVaM8al7x%2FHOF%2BSWG9x6JcOWtsaWjpuQK6MG8TKxBRkS8UPqQWmVq1%2F9T3qkSS23tpUvDhMi6%2BziNSv5VTdps6uGWFY0Ofy815w5Zr8ezful924N6xgo%2Bap7zp%2Bf1MZyK%2B8KfC8ykAQhy2IsAYCQPDuSE0z%2FPSEB%2FUTbjyvsj5cqBmcG3FYPLSrGf8bHjZMNq5AwwBVUPI%2FOA2D8IPT0C60PIHL6dKxW6VjyAJ81Mw5InPAteod7SqNrLFGcNdf8tsQMkRSQZFJhPpKm8aKNoXGjHNGcFLRUml%2BoZDWZvxdRPXCOxq1US9Ol%2FwW0iVranZJx8K2S5fOADW8Xb5kzexURQSc4kSdBiZlKp8HSovm3%2BWZYFTsA2E%2FWHEqGL36SmB3LlxAoYaplnkxcFHO7Q0jnwxHM4hiUFRbVRtznMVdbHGzb2WhcmxRc56E1wotLDbUVNVmBR3U8aoPWXkMqFDCH4dKXvwrxxIpwzuSHZVfi5PjTgnKqjxN4VxIxKvK3bnzYoWndnO%2FKR6po1ob8YjNtLpiXxeQXI5qXUBSh2obBVwLVPdERLLEeSmIqM8%2BaEKF6n%2Fm1HZB3V6%2FgBU1MUbtXm8hxvB930F%2B7A1GAydwI4h%2FXt%2FqR2W0iOE8p1GlPJbAJH70TBUNGkV5bF8sk13oznFKxbO7URDq9pRkFBYiw5MWi7smFVeFqF5t4e0ELkdA%2BHU4tFOL86olYbcSNTStxQ0qXzJzh5WBWqrFeZWS8MAfvjivqr75UltbtTgUzcJhGMTRgMwcHM76DdzWq1w2pd4SV%2F0Syw%2FNC8vApyK8Y8o2zVduw9n9z1h2lfZbObvnqB4IXYmzboWDoXcJBaF5%2BKWOUj%2F83sn79B8%3D)

### Overview of User Interface

The program begins by making a GET to the home page. The player signs in which invokes a POST sign in. If successful,
the player can then choose another signed in player to play against and a GET is made to the game page. Here the player
can make a move. First, there is a POST to validate the move, then there is a POST to submit the move.

### Tier UI

The UI tier allows the player to sign in, play games, make moves, and resign. GET routes handle getting the different
pages (Home, SignIn, Game). POST routes allow the player to sign in, make moves and resign.

### Tier model

The model tier hold the board object. The board is comprised of Spaces which all have a reference to the Piece object 
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

![](https://www.draw.io/?lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&title=titled.html#R7Vtdb%2BI4FP01SLsPVHFCQngsdGZ3tR0JTUezs48mMWCNiaPEFJhfv9exDSQObQqBdqVUSI2vbxz7nmPfj0DPm6y2f2Q4XX7hMWE914m3Pe%2Bh57rIDV34JyU7JfFHoRIsMhprpYPgif4iWuho6ZrGJC8pCs6ZoGlZGPEkIZEoyXCW8U1Zbc5Z%2BakpXhBL8BRhZkv%2FobFYKmnoOwf5n4QulubJyNE9K2yUtSBf4phvjkTep543yTgX6mq1nRAmjWfsou77fKJ3P7GMJKLJDcR1ZkEQ4cCP0GiE%2BmioRnjGbK0XO8WbpDdxe%2FdmymJn7JBv6IrhBFrjOU%2FEk%2B5B0MaMLhK4jmAiJAPBM8kEBRPe6w7BU5BGS8riR7zjazndXODop2mNlzyjv2BYzPSY0J0JzQY3KGk8yTtB7IA0IznoTI0NUEX0BW9Lio84F1oQccZwmtPZfhkrnC1oMuZC8JVWMov%2BTBmbcMazwhbevPiDfm1AWC7ZnkQF7bGGTUL4iohsByr6Bi%2FQttb7Y%2BAEqr05sA0ZneUR00KthzXBF%2FuhDxyAC02DhpQILUr0ofltl0LrHq5Isl5Z1IDFiwKyjP8kxkwJV1w5spwWGbowMhcnyZKnOKLJ4rHQeRgcJF%2B1BaSIw71zVuypJY1jkkigucACK1QlhCmniShM5I%2FhA4acOHd%2Bz4eJT6CNDm34SPVMTHgCa8G0AJAAZTZE0qYZ2vW7zIZfw%2B02RNu9BtojC20LW0YLzBS25gBEZwG7AogYOSD5TQL90EcW2p6NtleDLMMzwqY8p4JyOX6mdCuIvweovttwC7ePqXE2JUzH0KLxF9gu%2BW%2B%2Fq438SGH1bsCkCabSYNBYHOzR7e5WiGBAv8Xu9kjouR6az8O5T8KwP7CI8DcYuPPrN%2FLrgfOOfr3KBb9z6xdu%2FNrd9SG8enVmQefUrwHpDX16dWJ2nta59Pfa2e%2Fp0e3kbEpJRDqXfiOXPgjLLt0dNDwTRoPTZNBP%2B0oigZMFrPBNj2tCPcwA6gQLMubrJM4tBu4XehYp63LIcU1wAZPykFMAIbs1RF3wcc4RFbYSfHgvsPLcIwrVpZ9d9HExpk3dzhWiD4S68OPD7O0gvF34YZWW7IqCBS1J4nv50gVaM8al7x%2FHOF%2BSWG9x6JcOWtsaWjpuQK6MG8TKxBRkS8UPqQWmVq1%2F9T3qkSS23tpUvDhMi6%2BziNSv5VTdps6uGWFY0Ofy815w5Zr8ezful924N6xgo%2Bap7zp%2Bf1MZyK%2B8KfC8ykAQhy2IsAYCQPDuSE0z%2FPSEB%2FUTbjyvsj5cqBmcG3FYPLSrGf8bHjZMNq5AwwBVUPI%2FOA2D8IPT0C60PIHL6dKxW6VjyAJ81Mw5InPAteod7SqNrLFGcNdf8tsQMkRSQZFJhPpKm8aKNoXGjHNGcFLRUml%2BoZDWZvxdRPXCOxq1US9Ol%2FwW0iVranZJx8K2S5fOADW8Xb5kzexURQSc4kSdBiZlKp8HSovm3%2BWZYFTsA2E%2FWHEqGL36SmB3LlxAoYaplnkxcFHO7Q0jnwxHM4hiUFRbVRtznMVdbHGzb2WhcmxRc56E1wotLDbUVNVmBR3U8aoPWXkMqFDCH4dKXvwrxxIpwzuSHZVfi5PjTgnKqjxN4VxIxKvK3bnzYoWndnO%2FKR6po1ob8YjNtLpiXxeQXI5qXUBSh2obBVwLVPdERLLEeSmIqM8%2BaEKF6n%2Fm1HZB3V6%2FgBU1MUbtXm8hxvB930F%2B7A1GAydwI4h%2FXt%2FqR2W0iOE8p1GlPJbAJH70TBUNGkV5bF8sk13oznFKxbO7URDq9pRkFBYiw5MWi7smFVeFqF5t4e0ELkdA%2BHU4tFOL86olYbcSNTStxQ0qXzJzh5WBWqrFeZWS8MAfvjivqr75UltbtTgUzcJhGMTRgMwcHM76DdzWq1w2pd4SV%2F0Syw%2FNC8vApyK8Y8o2zVduw9n9z1h2lfZbObvnqB4IXYmzboWDoXcJBaF5%2BKWOUj%2F83sn79B8%3D)

### Dynamic models