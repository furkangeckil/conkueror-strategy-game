package domain;

import java.util.List;

public class Player extends User {
    private List<Army> armies;
    private String ID;
    private List<Territory> territories;
    private List<Card> cards;

    public void placeArmies() {
        // Implement placeArmies logic
        // Here, there will be references to Territory classes addArmies() method. There also need to be a variable that keeps the 
        // number of armies that can be recruited in this turn of player, and it need to be decreased when army is recruited. 
    }

    public void attack() {
        // Implement attack logic
        // need to select attacker territory and defender territory. Need to call attack validator method.
        WarController.resolveBattle( attackerTerritory, defenderTerritory  );
    }

    public void fortify() {
        // Implement fortify logic
    }

    public void drawCard() {
        // Implement drawCard logic
    }

    public void useCard() {
        // Implement useCard logic
    }
}