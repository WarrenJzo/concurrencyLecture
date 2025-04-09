package tp;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CommunicatingBehaviorFactory {

    public static CommunicatingBehavior create(
            MessageQueueReceiver receiver,
            Consumer<Message> onMessage,
            Predicate<Void> stopCondition,
            Runnable onStep) {
        return new CommunicatingBehavior(receiver, onMessage, stopCondition, onStep);
    }
}
