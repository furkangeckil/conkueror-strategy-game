package domain;

public class WarController {
    private AttackerDice attackerDice;
    private DefenderDice defenderDice;

    public WarController() {
        attackerDice = new AttackerDice();
        defenderDice = new DefenderDice();
    }

    public void attackerRollDice() {
        attackerDice.rollAttackerDice();
    }

    public void defenderRollDice() {
        defenderDice.rollDefenderDice();
    }

    private void sortDescending(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] < array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    private void compareDiceResults() {
        int[] attackerResults = attackerDice.getDieResults();
        int[] defenderResults = defenderDice.getDieResults();

        sortDescending(attackerResults);
        sortDescending(defenderResults);

        int numberOfComparisons = Math.min(attackerResults.length, defenderResults.length);

        for (int i = 0; i < numberOfComparisons; i++) {
            if (attackerResults[i] > defenderResults[i]) {
                // Handle the case when the attacker wins the comparison
            } else {
                // Handle the case when the defender wins or the result is a tie
            }
        }
    }

    public void resolveBattle() {
        attackerRollDice();
        defenderRollDice();
        compareDiceResults();
    }
}
