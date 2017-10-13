package com.webcheckers.model;

/**
 * Holds the unique player name.
 *
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 * @author <a href='mailto:gep2494@rit.edu'>George-Edward Pinal</a>
 */
public class Player {
    private String name;
    public Player(String name) {
        this.name = name;}

    public void setName(String name)
    {
        if(this.name == null)
            this.name = name;
        else
            throw new RuntimeException("Player already has name");
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
