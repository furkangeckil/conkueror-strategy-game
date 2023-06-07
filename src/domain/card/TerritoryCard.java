package domain.card;

import java.util.List;

import domain.player.Player;
import domain.territory.Territory;

public class TerritoryCard extends Card{
	
	private Territory territory;
	private static int neededCard = 3;
	
	public TerritoryCard(Territory territory) {
		super();
		this.territory = territory;
	}
	
	//changes owner of the territory if the player have all territory cards for that territory
	@Override
	public void applyEffect(Player player) {
		if(checkNumOfTerritoryCard(player.getCards())>neededCard) {
			this.territory.getOwner().removeTerritoryFromPlayer(territory);
			this.territory.setOwner(player);
			player.addTerritoryToPlayer(territory);
		}
	}
	
	//counts how many cards there are for the current territory in the given card list
	private int checkNumOfTerritoryCard(List<Card> cards) {
		int num = 0;
		for (Card card : cards) {
			if (card instanceof TerritoryCard) {
				if (this.territory == ((TerritoryCard)card).getTerritory()) {
					num++;
				}
			}
		}
		return num;	
	}

	public Territory getTerritory() {
		return territory;
	}

}
