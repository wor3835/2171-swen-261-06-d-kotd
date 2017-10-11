package com.webcheckers.model;

/**
 * Holds the unique player name.
 *
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 */
public class Player {
    String name;
    public Player(String name) {
        this.name = name;
    }

    public String getName(){return name;}

    public boolean equals(Object o){
        if(!(o instanceof Player))
            return false;
        return ((Player)o).getName().equals(getName());
    }
}
