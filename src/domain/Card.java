package domain;

public abstract class Card {
    
	protected Player owner;
	protected String type;
	
	//constructor
	public void addToPlayer(Player player) {
		this.owner=player;
		player.addCardToPlayer(this);
	}
	
	public abstract void applyEffect(Player player);
}
