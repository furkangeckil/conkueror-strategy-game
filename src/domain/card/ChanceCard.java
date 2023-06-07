package domain.card;

import domain.player.Player;

public class ChanceCard extends Card{
	
	private String effect;
	private Player owner;
	
	public ChanceCard(String effect, Player player) {
		this.effect = effect;
		this.owner = player;		
	}
}
