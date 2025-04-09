package tp;

public class CounterEntity implements StoppingBehavior {
    private final String name;
    private int counter = 0;

    public CounterEntity(String name) {
        this.name = name;
    }

    @Override
    public void step() {
        counter++;
        System.out.println(name + " : " + counter);
    }

    @Override
    public boolean wantsToStop() {
        return counter >= 100;
    }
}
