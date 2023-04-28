package domain;

import java.util.List;

public class TerritoryCard extends Card{
	
	private Territory territory;
	private static int neededCard = 3;
	
	public TerritoryCard(Territory territory) {
		super();
		this.territory = territory;
	}

	@Override
	public void applyEffect(Player player) {
		if(checkNumOfTerritory(player.getCards())>neededCard) {
			this.territory.getOwner().removeTerritory(territory);
			this.territory.changeOwner(player);
			player.addTerritory(territory);
		}
	}
	
	public void applyEffect(int option) {
		
	}
	
	private int checkNumOfTerritory(List<Card> cards) {
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
