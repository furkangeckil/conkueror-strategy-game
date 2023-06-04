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

    /**
     * This method is used to place a player's unassigned armies onto a specified territory.
     *
     * Requires:
     * 1. The input, territory, is a valid Territory object.
     * 2. The Player object, on which the method is called, is properly initialized.
     * 
     * Modifies:
     * 1. The Territory object passed as an argument, specifically the army count and possibly the ownership of the territory.
     * 2. The unassignedArmies field of the Player object, decreasing its value if armies are successfully placed.
     * 
     * Effects:
     * 1. If the player has unassigned armies and owns the territory, one army is added to the territory and the player's unassigned armies decrease by one. Returns 0.
     * 2. If the player has unassigned armies and the territory has no owner, one army is added, the player's unassigned armies decrease by one, and the player takes ownership of the territory. Returns 1.
     * 3. If the player has unassigned armies but the territory is owned by another player, no changes occur and a message is printed. Returns -1.
     * 4. If the player has no unassigned armies, no changes occur and a message is printed. Returns -1.
     *
     * @param territory the territory on which to place armies
     * @return int indicating success (0, 1) or failure (-1) of placing armies
     */
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
            return 1;
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


    public void decreaseUnassignedArmies(int i) {
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
