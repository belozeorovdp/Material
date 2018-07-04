package common;

import java.io.Serializable;

/**
 * Chat-messages sent 
 *
 * @author hajo
 *
 */
public final class Message implements IMessage, Serializable {

    private static final long serialVersionUID = Constants.SERIAL_V_ID;
    private final String text;
    private final User sender;
   
    public Message(User sender, String text){
        this.sender = sender;
        this.text = text;
    }
    
    public String getText(){
        return text;
    }
    
    public User getSender(){
        return sender;
    }
    
    
}
