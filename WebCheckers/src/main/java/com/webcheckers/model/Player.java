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

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Player))
            return false;
        return o.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode(){
        return this.getName().hashCode();
    }

}
