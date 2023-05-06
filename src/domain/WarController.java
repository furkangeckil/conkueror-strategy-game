package domain;

public class WarController {
    private static WarController instance;
    private AttackerDice attackerDice;
    private DefenderDice defenderDice;
    

    public WarController() {
        attackerDice = new AttackerDice();
        defenderDice = new DefenderDice();
    }

    public static WarController getInstance() {
        if (instance == null) {
            instance = new WarController();
        }
        return instance;
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

    private void compareDiceResults(Territory attackerTerritory, Territory defenderTerritory) {
        int[] attackerResults = attackerDice.getDieResults();
        int[] defenderResults = defenderDice.getDieResults();
    
        sortDescending(attackerResults);
        sortDescending(defenderResults);
    
        int numberOfComparisons = Math.min(attackerResults.length, defenderResults.length);
    
        for (int i = 0; i < numberOfComparisons; i++) {
            if (attackerResults[i] > defenderResults[i]) {
                // Attacker wins the comparison
                defenderTerritory.removeArmy(1);
            } else {
                // Defender wins or the result is a tie
                attackerTerritory.removeArmy(1);
            }
        }
    }
    

    public void resolveBattle(Territory attackerTerritory, Territory defenderTerritory) {
        int attackingArmies = Math.min(attackerTerritory.getArmies() - 1, 3);
        int defendingArmies = Math.min(defenderTerritory.getArmies(), 2);

        if (attackingArmies > 0 && defendingArmies > 0) {
            attackerRollDice();
            defenderRollDice();
            compareDiceResults(attackerTerritory, defenderTerritory);
        }
    }
}
