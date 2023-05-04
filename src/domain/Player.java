package domain;

import java.util.List;

public class Player extends User {
    private List<Army> armies;
    private int unassignedArmies;
    private String ID;
    private List<Territory> territories;
    private List<Card> cards;

    

    public Player(String iD) {
        ID = iD;
    }


    public void placeArmies() {
        // Implement placeArmies logic
        // Here, there will be references to Territory classes addArmies() method. There also need to be a variable that keeps the 
        // number of armies that can be recruited in this turn of player, and it need to be decreased when army is recruited. 
    }
    

    public void attack() {
        // Implement attack logic
        // need to select attacker territory and defender territory. Need to call attack validator method.
        Territory attackerTerritory = TerritoryController.getInstance().getAllTerritories()[1];
        Territory defenderTerritory = TerritoryController.getInstance().getAllTerritories()[1];
        WarController.getInstance().resolveBattle( attackerTerritory, defenderTerritory  );
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


    public List<Army> getArmies() {
        return armies;
    }


    public void setArmies(List<Army> armies) {
        this.armies = armies;
    }


    public String getID() {
        return ID;
    }


    public void setID(String iD) {
        ID = iD;
    }


    public List<Territory> getTerritories() {
        return territories;
    }


    public void setTerritories(List<Territory> territories) {
        this.territories = territories;
    }


    public List<Card> getCards() {
        return cards;
    }


    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void decreaseUnassignedArmies(int num) {
        unassignedArmies -= num;
    }
    
}
