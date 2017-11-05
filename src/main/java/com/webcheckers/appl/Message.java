package com.webcheckers.appl;

/**
 * Created by wor3835 on 10/20/2017.
 */
public class Message {
    private String text;
    private MasterEnum.MessageType type;

    public Message(String text, MasterEnum.MessageType type){
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public MasterEnum.MessageType getType(){
        return type;
    }

}
