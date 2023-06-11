package domain.card;

import domain.player.Player;

public class CardFactory {

	public static Card createCard(String type, String effect, Player player) {
		if(type == null) {
			return null;
		}
		
		if(type.equalsIgnoreCase("Chance")) {
			return new ChanceCard(effect,player);
		}
		else if(type.equalsIgnoreCase("Army")) {
			return new ArmyCard(effect,player);
		}
		else if(type.equalsIgnoreCase("Territory")) {
			return new TerritoryCard(effect,player);
		}
		
		return null;
	}
	
}
