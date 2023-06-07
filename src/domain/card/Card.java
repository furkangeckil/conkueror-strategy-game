package domain.card;

import domain.player.Player;

public abstract class Card {
    
	protected Player owner;
	protected String type;
	
	//constructor
	public void addToPlayer(Player player) {
		this.owner=player;
		player.addCardToPlayer(this);
	}
}
