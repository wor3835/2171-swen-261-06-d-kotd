package com.webcheckers.appl;

/**
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 *
 *
 * Creates a message for the user to view. Can be an info message
 * or an error message.
 *
 */
public class Message {
    private String text;
    private MasterEnum.MessageType type;

    /**
     * Creates new message
     * @param text text of the message
     * @param type type of the message (info or error)
     */
    public Message(String text, MasterEnum.MessageType type){
        this.text = text;
        this.type = type;
    }

    /**
     * @return message text
     */
    public String getText() {
        return text;
    }

    /**
     * @return message type
     */
    public MasterEnum.MessageType getType(){
        return type;
    }

}
