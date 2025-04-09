package tp;

import java.util.function.Predicate;

public class CountingEntity implements MessageSender {

    private final String name;
    private int localCount = 0;
    private final int maxLocalCount = 10;
    private final int globalLimit = 100;
    private final MessageQueueReceiver receiver = new MessageQueueReceiver();
    private MessageReceiver nextEntity;

    private int lastGlobalCount = 0;
    private boolean done = false;

    public CountingEntity(String name) {
        this.name = name;
    }

    public void setNextEntity(MessageReceiver next) {
        this.nextEntity = next;
    }

    public MessageQueueReceiver getReceiver() {
        return receiver;
    }

    public CommunicatingBehavior getBehavior() {
        return CommunicatingBehaviorFactory.create(
                receiver,
                message -> {
                    if (message instanceof CountMessage countMsg) {
                        int globalCount = countMsg.count;
                        lastGlobalCount = globalCount;
                        if (globalCount >= globalLimit) {
                            done = true;
                            return;
                        }

                        localCount++;
                        System.out.println(name + " : local = " + localCount + ", global = " + globalCount);

                        if (localCount >= maxLocalCount) {
                            sendMessage(nextEntity, new CountMessage(globalCount + 1));
                            localCount = 0;
                        } else {
                            sendMessage(receiver, new CountMessage(globalCount + 1));
                        }
                    }
                },
                unused -> done,
                () -> {}
        );
    }
}
