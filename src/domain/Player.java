package domain;

import java.util.List;

public class Player extends User {
    private List<Army> armies;
    private String ID;
    private List<Territory> territories;
    private List<Card> cards;

    public void placeArmies(Territory territory, Army army) {
        if(territories.contains(territory) && armies.contains(army)) {
        	territory.addArmy(army);
        }
    }

    public void attack() {
        // Implement attack logic
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