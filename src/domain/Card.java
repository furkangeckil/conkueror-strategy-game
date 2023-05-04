package domain;

public abstract class Card {
    
	protected Player owner;
	protected String type;
	
	public void addToPlayer(Player player) {
		// Implement addToPlayer logic
	}
	
	public abstract void applyEffect();
	
}