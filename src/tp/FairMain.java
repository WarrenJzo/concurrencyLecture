package tp;

import java.util.ArrayList;
import java.util.List;

public class FairMain {
    public static void main(String[] args) {
        List<StoppingBehavior> behaviors = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            behaviors.add(new CounterEntity("Entity-" + i));
        }

        FairScheduler fairScheduler = new FairScheduler();
        fairScheduler.schedule(behaviors);
    }
}
