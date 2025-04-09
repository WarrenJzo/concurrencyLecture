package tp;

public class Main {
    public static void main(String[] args) {
        BehaviorScheduler scheduler = new BehaviorScheduler();

        for (int i = 1; i <= 100; i++) {
            CounterEntity entity = new CounterEntity("Entity-" + i);
            scheduler.callBehavior(entity);
        }
    }
}
