package main.java.com.webcheckers.model;

/**
 * a checkers board
 *
 * @author <a href='mailto:ajn3687@g.rit.edu'>AJ Nagashima</a>
 * @author <a href='mailto:gep2494@g.rit.edu'>George-Edward Pinal</a>
 * @author <a href='mailto:kcz33966@g.rit.edu'>Kerri Zalba</a>
 * @author <a href='mailto:rwk8144@g.rit.edu'>Robert Kurdziel</a>
 * @author <a href='mailto:wor3835@g.rit.edu'>William Raffaelle</a>
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
