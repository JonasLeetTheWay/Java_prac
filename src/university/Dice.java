package university;

public class Dice {
    private int value;

    public Dice() {
        this.toss();
    }

    public void toss() {
        int random = (int) (6 * Math.random());

        while (random == 0) {
            random = (int) (6 * Math.random());
        }
        this.value = random;
    }

    public int getTossedValue() {
        return value;
    }
}
