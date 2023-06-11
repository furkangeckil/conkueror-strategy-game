package domain.util;

import java.util.Random;

public class Dice {
    private int result;
    private Random rand;

    public Dice() {
        rand = new Random();
    }

    public int roll() {
        result = rand.nextInt(6) + 1; // Generates a random number between 1 and 6
        return result;
    }

    public int getResult() {
        return result;
    }
}
