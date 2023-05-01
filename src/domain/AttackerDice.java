package domain;

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
}
