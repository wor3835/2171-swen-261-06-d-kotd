package main.java.com.webcheckers.model;

/**
 * a checkers board
 *
 * @author <a href='mailto:ajn3687@g.rit.edu'>AJ Nagashima</a>
 * @author <a href='mailto:gep2494@g.rit.edu'>George-Edward Pinal</a>
 * @author
 * @author
 * @author
 */
public class Board {
    private final int BOARD_DIM = 8;
    Tile board[][] = new Tile[BOARD_DIM][BOARD_DIM];

    public Tile[][] makeBoard(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){}
                //board[i][j] = new Tile(x,y);
        }
        return board;
    }
}
