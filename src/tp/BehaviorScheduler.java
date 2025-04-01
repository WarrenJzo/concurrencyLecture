package tp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BehaviorScheduler {

    private final ExecutorService executor;


    public BehaviorScheduler() {
        executor = Executors.newCachedThreadPool();
    }


    public void callBehavior(StoppingBehavior behavior) {
        executor.submit(() -> {
            behavior.step();
            if (!behavior.wantsToStop()) {
                executor.submit(() -> callBehavior(behavior));
            }
        });
    }

}
