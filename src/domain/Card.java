package domain;

public abstract class Card {
    
	protected Player owner;
	protected String type;
	
	public void addToPlayer(Player player) {
		this.owner=player;
		player.addCardToList(this);
	}
	
	public abstract void applyEffect(Player player);
	public abstract void applyEffect(int option);
}
