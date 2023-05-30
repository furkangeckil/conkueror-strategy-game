package domain;

import java.util.Random;

public class DefenderDice extends Dice {
    private int dice1 = 0;
    private int dice2 = 0;
    private int[] dieResults = new int[2];
    public DefenderDice() {
        super();
    }

    public void rollDefenderDice() {
        dice1 = super.roll();
        dice2 = super.roll();
        dieResults[0] = dice1;
        dieResults[1] = dice2;
    }

    public int getDice1() {
        return dice1;
    }

    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }

    public int[] getDieResults() {
        return dieResults;
    }

    public void setDieResults(int[] dieResults) {
        this.dieResults = dieResults;
    }

    
}