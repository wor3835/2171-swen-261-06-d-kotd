package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kzalb on 10/15/2017.
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 * @author <a href='mailto:gep2494@rit.edu'>George-Edward Pinal</a>
 * Represents a space on the board
 */
public class Space{

    private int cellIdx;
    private boolean validSpace;
    private Piece piece;


    /**
     * Physical space on the game board
     * @param cellIdx id of cell
     * @param valid if space is on board or not
     * @param piece game piece on space
     */
    public Space(int cellIdx, boolean valid, Piece piece)
    {
        this.cellIdx = cellIdx;
        this.validSpace = valid;
        this.piece = piece;
    }

    /**
     * @return id of cell
     */
    public int getCellIdx()
    {
        return cellIdx;
    }

    /**
     * @return if space is valid
     */
    public boolean isValid()
    {
        return validSpace;
    }

    /**
     * @return game piece on space
     */
    public Piece getPiece()
    {
        return piece;
    }

    public void removePiece(){
        piece = null;
    }

    public void kingMe(){
        if(piece == null)
            throw new RuntimeException("no piece to king");
        else if(piece.getType() == MasterEnum.PieceType.KING)
            throw new RuntimeException("piece is already a king");
        piece = new King(piece);
    }
    
    //for testing
    @Override
    public boolean equals(Object o){
        return (this.isValid() == ((Space) o).isValid() && this.cellIdx == ((Space) o).cellIdx && this.getPiece().equals(((Space) o).getPiece()));
    }
}

