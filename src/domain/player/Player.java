package domain.player;

import java.util.ArrayList;
import java.util.List;

import domain.army.Army;
import domain.army.Army.armyTypes;
import domain.card.ArmyCard;
import domain.card.Card;
import domain.controllers.WarController;
import domain.game.GameState;
import domain.territory.Territory;

public class Player extends User {
    private List<Army> armies = new ArrayList<Army>();
    private int unassignedArmies= 40 - ((GameState.getNumPlayers()-1)*5);
    private List<Territory> territories = new ArrayList<Territory>();
    private List<Card> cards = new ArrayList<Card>();
    private int totalArmy;
    private String name;
    

    public Player(String name) {
      this.name= name;
    }


    public void placeArmies(Territory territory) {
        if (unassignedArmies > 0) {
            if(territory.getOwner() == this) {
            territory.addArmy(1);
            unassignedArmies--;
            } else if(territory.getOwner() == null) {
            territory.addArmy(1);
            unassignedArmies--;
            territory.setOwner(this);
            }else {
            System.out.println("cannot place armies");
            }
        } else {
            System.out.println("no army to recruit");
        }
    }
    
    public void getTurnArmy (List<Territory> territories) {
        unassignedArmies += Math.floor((territories.size()/3));
    }
    
    public void attack(Territory attackerTerritory, Territory defenderTerritory) {
        WarController.getInstance().rollSimpleDice( attackerTerritory, defenderTerritory  );
    }

    public void fortify(Territory from, Territory to, int numArmy) {
        from.removeArmy(numArmy);
        to.addArmy(numArmy);
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
    		unassignedArmies--;
    }

    public void removeTerritoryFromPlayer(Territory territory) {
        territories.remove(territory);
    }

    public List<Card> getCards() {
        return cards;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
    
    public int getUnassignedArmies() {
    	return this.unassignedArmies;
    }
}
