package tp;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CommunicatingBehavior implements StoppingBehavior {

    private final MessageQueueReceiver receiver;
    private final Consumer<Message> onMessage;
    private final Runnable onStep;
    private final Predicate<Void> stopCondition;

    public CommunicatingBehavior(
            MessageQueueReceiver receiver,
            Consumer<Message> onMessage,
            Predicate<Void> stopCondition,
            Runnable onStep) {
        this.receiver = receiver;
        this.onMessage = onMessage;
        this.stopCondition = stopCondition;
        this.onStep = onStep;
    }

    @Override
    public void step() {
        Message message = receiver.poll();
        if (message != null) {
            onMessage.accept(message);
        }
        onStep.run();
    }

    @Override
    public boolean wantsToStop() {
        return stopCondition.test(null);
    }
}
