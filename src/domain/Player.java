package domain;

import java.util.List;

public class Player extends User {
    private List<Army> armies;
    private String ID;
    private List<Territory> territories;
    private List<Card> cards;

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

    public void useCard(Card card) {
    	//GameState.useCard(Player this, Card card );
    }
    
    //adds the given car to the card list of the player 
    public void addCardToList(Card card) {
        this.cards.add(card);
    }
    
    public void addTerritory(Territory territory) {
		territories.add(territory);
	} 
    
    public void removeTerritory(Territory territory) {
		territories.remove(territory);
	}

	public List<Card> getCards() {
		return cards;
	}
    
    
}