package com.webcheckers.appl;

/**
 * Created by wor3835 on 10/20/2017.
 */
public class Message {
    private String text;
    private enum Type {info, error}
    private Type type;

    public Message(String text, Type type){
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return null;
    }

    public Type getType(){
        return type;
    }

}
