package common;

/**
 * Interface for the chat-messages sent
 * @author hajo
 *
 */
public interface IMessage {
    public String getText();
    public User getSender();
}
