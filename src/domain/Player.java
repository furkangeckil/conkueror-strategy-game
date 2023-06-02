package domain;

import java.util.ArrayList;
import java.util.List;

import domain.Army.armyTypes;

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


    public int placeArmies(Territory territory) {
        if (unassignedArmies > 0) {
            if(territory.getOwner() == this) {
            territory.addArmy(1);
            unassignedArmies--;
            return 0;
            } else if(territory.getOwner() == null) {
            territory.addArmy(1);
            unassignedArmies--;
            territory.setOwner(this);
            return 0;
            }else {
            System.out.println("cannot place armies");
            return -1;
            }
        } else {
            System.out.println("no army to recruit");
            return -1;
        }
    }
    
    public void getTurnArmy (List<Territory> territories) {
        unassignedArmies += (3+(Math.floor((territories.size()-9)/3)));
    }
    

    public void attack(Territory attackerTerritory, Territory defenderTerritory) {
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
	
	

    public void setUnassignedArmies(int unassignedArmies) {
		this.unassignedArmies = unassignedArmies;
	}


	public int getUnassignedArmies() {
		return unassignedArmies;
	}


	public void decreaseUnassignedArmies(int i) {
    	this.unassignedArmies -= i;
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
    
}
