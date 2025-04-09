package tp;

public interface MessageSender {
    default void sendMessage(MessageReceiver receiver, Message message) {
        receiver.accept(message);
    }
}
