package tp;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int entityCount = 10;

        List<CountingEntity> entities = new ArrayList<>();
        List<StoppingBehavior> behaviors = new ArrayList<>();

        for (int i = 0; i < entityCount; i++) {
            CountingEntity entity = new CountingEntity("Entite numéro " + i);
            entities.add(entity);
        }

        for (int i = 0; i < entityCount; i++) {
            CountingEntity current = entities.get(i);
            CountingEntity next = entities.get((i + 1) % entityCount);
            current.setNextEntity(next.getReceiver());
            behaviors.add(current.getBehavior());
        }

        entities.get(0).sendMessage(entities.get(0).getReceiver(), new CountMessage(1));

        FairScheduler scheduler = new FairScheduler();
        scheduler.schedule(behaviors);
    }
}
