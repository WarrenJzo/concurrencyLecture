package tp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FairScheduler {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    public void schedule(List<StoppingBehavior> behaviors) {
        List<StoppingBehavior> current = new ArrayList<>(behaviors);

        while (!current.isEmpty()) {
            List<Future<StoppingBehavior>> futures = new ArrayList<>();

            // Étape 1 : soumettre tous les comportements pour un "tour"
            for (StoppingBehavior behavior : current) {
                Future<StoppingBehavior> future = executor.submit(() -> {
                    behavior.step();
                    return behavior.wantsToStop() ? null : behavior;
                });
                futures.add(future);
            }

            // Étape 2 : récupérer les survivants
            List<StoppingBehavior> next = new ArrayList<>();
            for (Future<StoppingBehavior> future : futures) {
                try {
                    StoppingBehavior result = future.get(); // blocant
                    if (result != null) {
                        next.add(result);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            current = next;
        }
    }
}
