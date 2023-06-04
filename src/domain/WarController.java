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
/* This method is for DiceRolling mechanics for attacking part of the game. 
 * Requires:
 * 1- The inputs, Territories valid
 * 2- The inputs does not belong to the same player
 * 3- the inputs contain sufficient number of armies
 * Modifies: 
 * 1- The army numbers of the territories at war
 * 2- The owner of the defender territory if it is occupied
 * Effects: 
 *  ** if the requirements are satisfied; 
 * 1-  if attacker dice is bigger than defender dice defender territory loses one army
 * 2- if 1 and defender territory does not contain any army after war, it gets occupied
 * 3- if defender dice equals attacker dice attacker territory loses two armies
 * 4- if defender dice is bigger attacker dice attacker territory loses two armies
 */
    public void rollSimpleDice(Territory attackerTerritory, Territory defenderTerritory) {
        int[] attackerResults = attackerDice.getDieResults();
        int[] defenderResults = defenderDice.getDieResults();

        int attackerDice = attackerResults[0];
        int defenderDice = defenderResults[0];
        
        System.out.println("Attacker rolled " + attackerDice + " and the defender rolled " + defenderDice);

        if (attackerDice > defenderDice ) {
            attackerTerritory.removeArmy(2);
        } else {
            defenderTerritory.removeArmy(1);
        }
        
        if(defenderTerritory.getArmyNum() == 0) {
        	defenderTerritory.setOwner(attackerTerritory.getOwner());
        	attackerTerritory.removeArmy(1);
        	defenderTerritory.addArmy(1);
        }
    }
    

//    public void resolveBattle(Territory attackerTerritory, Territory defenderTerritory) {
//        int attackingArmies = Math.min(attackerTerritory.getArmyNum() - 1, 3);
//        int defendingArmies = Math.min(defenderTerritory.getArmyNum(), 2);
//
//        if (attackingArmies > 0 && defendingArmies > 0) {
//            attackerRollDice();
//            defenderRollDice();
//            rollSimpleDice(attackerTerritory, defenderTerritory);
//        }
//        // IF THE WAR IS ENDED BY ATTACKER WIN, OCCUPY TERRITORY
//        if (defenderTerritory.getArmyNum() == 0) {
//            defenderTerritory.setOwner(attackerTerritory.getOwner());
//        }
//    }
}
