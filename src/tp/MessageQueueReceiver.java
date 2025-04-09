package tp;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueueReceiver implements MessageReceiver {

    private final BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    @Override
    public void accept(Message message) {
        queue.offer(message);
    }

    public Message poll() {
        return queue.poll();
    }
}
