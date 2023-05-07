package domain;

import java.util.ArrayList;
import java.util.List;

import domain.Army.armyTypes;

public class Player extends User {
    private List<Army> armies = new ArrayList<Army>();
    private int unassignedArmies;
    private String ID;
    private List<Territory> territories = new ArrayList<Territory>();
    private List<Card> cards = new ArrayList<Card>();
    private int totalArmy;

    

    public Player(String iD) {
        ID = iD;
    }


    public void placeArmies(Territory territory) {
    	territory.addArmy(1);
    	unassignedArmies--;
    	
    }
    

    public void attack() {
        // Implement attack logic
        // need to select attacker territory and defender territory. Need to call attack validator method.
        Territory attackerTerritory = TerritoryController.getInstance().getAllTerritories()[1];
        Territory defenderTerritory = TerritoryController.getInstance().getAllTerritories()[1];
        WarController.getInstance().resolveBattle( attackerTerritory, defenderTerritory  );
    }

    public void fortify(Territory from, Territory to) {
        from.removeArmy(1);
        to.addArmy(1);
    }

    public void drawCard() {
        // Implement drawCard logic
    }

    public void useCard() {
        // Implement useCard logic
    }

    public int getTotalArmy() {
    	return this.totalArmy;
    }
    
    public void setTotalArmy(int i) {
    	this.totalArmy += i;
    }
    
	public List<Territory> getTerritories() {
		return territories;
	}


    public void decreaseUnassignedArmies(int i) {
    }


    public void removeTerritoryFromPlayer(Territory territory) {
        territories.remove(territory);
    }


    public List<Card> getCards() {
        return cards;
    }


    public void addTerritoryToPlayer(Territory territory) {
        territories.add(territory);
    }

    
    public void addArmyToPlayer(Army army) {
        // implement
    }
	
    
    public void removeCardFromPlayer(armyTypes type, int num) {
    	for (int i=0; i<this.cards.size() & num>0; i++) {
    		if(((ArmyCard)this.cards.get(i)).getType() == type) {
    			this.cards.remove(i);
    			i--;
    			num--;
    		}
    	}   
    }


    public void addCardToPlayer(Card card) {
        // implement
    }
    
}
