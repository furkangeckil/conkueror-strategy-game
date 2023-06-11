package domain.util;

public class AttackerDice extends Dice {
    private int dice1 = 0;
    private int dice2 = 0;
    private int dice3 = 0;
    private int[] dieResults = new int[3];

    public AttackerDice() {
        super();
    }

    public void rollAttackerDice() {
        dice1 = super.roll();
        dice2 = super.roll();
        dice3 = super.roll();
        dieResults[0] = dice1;
        dieResults[1] = dice2;
        dieResults[2] = dice3;
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

    public int getDice3() {
        return dice3;
    }

    public void setDice3(int dice3) {
        this.dice3 = dice3;
    }

    public int[] getDieResults() {
        return dieResults;
    }

    public void setDieResults(int[] dieResults) {
        this.dieResults = dieResults;
    }

    
}