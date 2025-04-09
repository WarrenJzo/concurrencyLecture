package tp;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int entityCount = 10;

        List<CountEntityMessage> entities = new ArrayList<>();
        List<StoppingBehavior> behaviors = new ArrayList<>();

        for (int i = 0; i < entityCount; i++) {
            CountEntityMessage entity = new CountEntityMessage("Entite numéro " + i);
            entities.add(entity);
        }

        for (int i = 0; i < entityCount; i++) {
            CountEntityMessage current = entities.get(i);
            CountEntityMessage next = entities.get((i + 1) % entityCount);
            current.setNextEntity(next.getReceiver());
            behaviors.add(current.getBehavior());
        }

        entities.get(0).sendMessage(entities.get(0).getReceiver(), new CountMessage(1));

        FairScheduler scheduler = new FairScheduler();
        scheduler.schedule(behaviors);
    }
}
