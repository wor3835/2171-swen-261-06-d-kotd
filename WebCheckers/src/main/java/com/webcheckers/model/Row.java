package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kzalb on 10/15/2017.
 */
public class Row {

    private int index;

    public Row(int index)
    {
        this.index = index;
    }

    public int getIndex()
    {
        return index;
    }

    public Iterator<Space> iterator(){
        List<Space> spaces = new ArrayList<>();
        for(int i = 0; i < BoardView.BOARD_LENGTH; i++){
            spaces.add(new Space(i, (i+index)%2 == 0 ? true : false);
        }
        return spaces.iterator();
    }
}
