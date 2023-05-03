package domain;

import java.util.ArrayList;
import java.util.List;

import domain.Army.armyTypes;

public class Player extends User {
    private List<Army> armies;
    private String ID;
    private List<Territory> territories;
    private List<Card> cards;
    private static GameState gameState;
     
    public Player(String iD) {
		super();
		this.armies = new ArrayList();
		ID = iD;
		this.territories = new ArrayList();
		this.cards = new ArrayList();
	}

	public void addState(GameState gameState) {
        this.gameState = gameState;
    }
    
    public void placeArmies() {
        // Implement placeArmies logic
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
    
    //calls useCard function of gameState class to use effect of territory card (P.S: this can be used for chance card also)
    public void useCard(Card card) {
    	gameState.useCard(this, card );
    }
    
    //calls tradeArmyCards function of gameState class to change army cards with army
    public void tradeArmyCards(Player player, int option) {
    	gameState.tradeArmyCards(player, option);
	}
    
    //adds the given card to the card list of the player 
    public void addCardToPlayer(Card card) {
        this.cards.add(card);
    }
    
    //removes desired number of the given card from the card list of the player 
    public void removeCardFromPlayer(armyTypes type, int num) {
    	for (int i=0; i<this.cards.size() & num>0; i++) {
    		if(((ArmyCard)this.cards.get(i)).getType() == type) {
    			this.cards.remove(i);
    			i--;
    			num--;
    		}
    	}   
    }
    
    //adds the given army to the army list of the player
    public void addArmyToPlayer(Army army) {
        this.armies.add(army);
    }
    
    //removes the given army from the army list of the player
    public void removeArmyFromPlayer(Army army) {
        this.armies.remove(army);
    }
    
    //adds the given territory to the territory list of the player
    public void addTerritoryToPlayer(Territory territory) {
		territories.add(territory);
	} 
    
    //removes the given territory from the territory list of the player
    public void removeTerritoryFromPlayer(Territory territory) {
		territories.remove(territory);
	}

	public List<Card> getCards() {
		return cards;
	}

	public List<Army> getArmies() {
		return armies;
	}

	public List<Territory> getTerritories() {
		return territories;
	}
    
}