package domain;

import java.util.List;
import domain.Army.armyTypes;

public class ArmyCard extends Card{
    
	private armyTypes type;

	public ArmyCard(String type) {
		super();
		this.type = Army.transformType(type);
	}
	
	@Override
	public void applyEffect(Player player) {
	}
	
	/*changes army cards to obtain additional army
    Option-1: 3 Infantry cards => 1 Cavalry
	Option-2: 2 Infantry cards + 1 Cavalry card => 2 Cavalry
	Option-3: 2 Infantry cards + 1 Artillery card => 2 Artillery
	Option-4: 1 Infantry card + 2 Cavalry cards => 1 Cavalry + 1 Artillery
	Option-5: 1 Artillery + 2 Cavalry cards => 3 Artillery
    */
    public static void tradeArmyCards (Player player, int option) {
    	
    	/*This method is used to trade Player's army cards and updating the Player's total army number.
    	 * 
    	 * Requires:
    	 * 1. A Player object which is properly initialized.
    	 * 2. And a chosen option from the player which dictates which trade is going to happen.
    	 * 
    	 * Modifies:
    	 * 1. Player's cards are appropriately removed if the Player has enough cards for the selected trade.
    	 * 2. Player's total army number is updated according to the trade that occurs.
    	 * 
    	 * Effects:
    	 * 1. As it is a method with void return it returns nothing.
    	 * 2. It changes Player's cards and their total army number if the circumstances are valid.
    	 * 
    	 */
    	
    	switch (option) {
  	  	case 1:
  	  		if (checkNumOfArmyCard(player.getCards(), armyTypes.INFANTRY) >=3) {
  	  		/*	player.addArmyToPlayer(new Army("Cavalry", player));
  	  			player.removeCardFromPlayer(armyTypes.INFANTRY, 3); */
  	  			player.setTotalArmy(2);
	  			player.removeCardFromPlayer(armyTypes.INFANTRY,3);
  	  		}
  	  		
  	  		
  	  		break;
  	  	case 2:
  	  		if (checkNumOfArmyCard(player.getCards(), armyTypes.INFANTRY) >=2 & checkNumOfArmyCard(player.getCards(), armyTypes.CAVALRY) >=1) { 
  	  		
  	  		/*	player.addArmyToPlayer(new Army("Cavalry", player));
  	  			player.addArmyToPlayer(new Army("Cavalry", player)); */
  	  			player.removeCardFromPlayer(armyTypes.INFANTRY, 2);
  	  			player.removeCardFromPlayer(armyTypes.CAVALRY, 1);
  	  			player.setTotalArmy(3);
  	  			
  	  		}
  	  		
  	  		break;
  	  	case 3:
  	  		if (checkNumOfArmyCard(player.getCards(), armyTypes.INFANTRY) >=2 & checkNumOfArmyCard(player.getCards(), armyTypes.ARTILLERY) >=1) { 
  	  			
  	  		/*	player.addArmyToPlayer(new Army("Artillery", player));
  	  			player.addArmyToPlayer(new Army("Artillery", player)); */
  	  			player.removeCardFromPlayer(armyTypes.INFANTRY, 2);
  	  			player.removeCardFromPlayer(armyTypes.ARTILLERY, 1);
  	  			player.setTotalArmy(8);
  	  			
  	  		}
  	  		
  	  		break;
  	  	case 4:
  	  		if (checkNumOfArmyCard(player.getCards(), armyTypes.INFANTRY) >=1 & checkNumOfArmyCard(player.getCards(), armyTypes.CAVALRY) >=2) { 
  	  			
  	  		/*	player.addArmyToPlayer(new Army("Cavalry", player));
  	  			player.addArmyToPlayer(new Army("Artillery", player)); */
  	  			player.removeCardFromPlayer(armyTypes.INFANTRY, 1);
  	  			player.removeCardFromPlayer(armyTypes.CAVALRY, 2);
  	  			player.setTotalArmy(4);
  	  			
  	  		} 
  	  		
  	  		break;
  	  	case 5:
  	  		if (checkNumOfArmyCard(player.getCards(), armyTypes.ARTILLERY) >=1 & checkNumOfArmyCard(player.getCards(), armyTypes.CAVALRY) >=2) { 
  	  			
  	  		/*	player.addArmyToPlayer(new Army("Artillery", player));
  	  			player.addArmyToPlayer(new Army("Artillery", player));	*/
  	  			player.addArmyToPlayer(new Army("Artillery", player));
  	  			player.removeCardFromPlayer(armyTypes.ARTILLERY, 1);
  	  			player.removeCardFromPlayer(armyTypes.CAVALRY, 2);
  	  			player.setTotalArmy(10);
  	  			
  	  		} 
  	  		
  	  		break;
    	}
    }
    
    //counts how many given army type cards are in the given card list
    /**
     * This method checks and counts the number of army cards of a specified type within a given list of cards.
     *
     * @param cards: The list of cards to check. These should be instances of Card, and can include ArmyCard or other types of cards.
     *      Requires: A non-null list of Card instances.
     * @param armyType: The type of the army for which cards will be counted. 
     *      Requires: A non-null armyType of type Army.armyTypes.
     *
     * @return num: The number of ArmyCard instances in the input list that match the specified army type.
     *
     * Modifies: Nothing. This method does not change any fields or objects.
     *
     * Effects: This method traverses the input list and checks each Card instance to see if it is an ArmyCard 
     *      and if its type matches the specified armyType. For each match, the method increments a counter. 
     *      The method returns the final count.
     */
    private static int checkNumOfArmyCard(List<Card> cards, armyTypes armyType) {
		int num = 0;
		for (Card card : cards) {
			if (card instanceof ArmyCard) {
				if (armyType == ((ArmyCard)card).getType()) {
					num++;
				}
			}
		}
		return num;	 
	}
    
    public armyTypes getType() {
		return this.type;
	}

	@Override
	public String toString() {
		return "ArmyCard [type=" + type + "]";
	}
    
    
}