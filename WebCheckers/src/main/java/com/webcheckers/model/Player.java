package com.webcheckers.model;

/**
 * Holds the unique player name.
 *
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 * @author <a href='mailto:gep2494@rit.edu'>George-Edward Pinal</a>
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 */
public class Player {
    private boolean inGame;
    private String name;
    private Game game;

    public Player(String name) {
        this.name = name;
        this.inGame = false;
    }

    public void setName(String name)
    {
        if(this.name == null)
            this.name = name;
        else
            throw new RuntimeException("Player already has name");
    }

    public String getName(){return name;}

    public boolean isInGame(){
        return this.inGame;
    }

    public void assignGame(Game game){
        this.game = game;
        inGame = true;
    }

    public void leaveGame(){
        this.inGame = false;
    }

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

    @Override
    public String toString(){
        return "<a href='/game' onclick=\"playerLobby.pullByHashCode("+hashCode()+")\">" +getName() + "</a>" + "\n";
    }
}
