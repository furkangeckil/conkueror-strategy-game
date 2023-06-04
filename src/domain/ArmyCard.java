package domain;

import java.util.List;
import domain.Army.armyTypes;

public class ArmyCard extends Card{
    
	private armyTypes type;

	public ArmyCard(String type) {
		super();
		this.type = Army.transformType(type);
	}
	
	public ArmyCard(armyTypes type) {
		this.type = type;
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
    	if(option == 1) {
    		if(checkNumOfArmyCard(player.getCards(), armyTypes.INFANTRY) >=3) {
    			player.setTotalArmy(5);
    			player.removeCardFromPlayer(armyTypes.INFANTRY,3);
    		}
    	}
    	else if(option == 2) {
	  		if(checkNumOfArmyCard(player.getCards(), armyTypes.INFANTRY) >=2 & checkNumOfArmyCard(player.getCards(), armyTypes.CAVALRY) >=1) {
				player.removeCardFromPlayer(armyTypes.INFANTRY, 2);
				player.removeCardFromPlayer(armyTypes.CAVALRY, 1);
				player.setTotalArmy(10);
	  		}	
    	}
    	else if(option == 3) {
	  		if(checkNumOfArmyCard(player.getCards(), armyTypes.INFANTRY) >=2 & checkNumOfArmyCard(player.getCards(), armyTypes.ARTILLERY) >=1) {
	  			player.removeCardFromPlayer(armyTypes.INFANTRY, 2);
	  			player.removeCardFromPlayer(armyTypes.ARTILLERY, 1);
	  			player.setTotalArmy(20);
	  		}	
    	}
    	else if(option == 4) {
	  		if(checkNumOfArmyCard(player.getCards(), armyTypes.INFANTRY) >=1 & checkNumOfArmyCard(player.getCards(), armyTypes.CAVALRY) >=2) {
	  			player.removeCardFromPlayer(armyTypes.INFANTRY, 1);
	  			player.removeCardFromPlayer(armyTypes.CAVALRY, 2);
	  			player.setTotalArmy(15);
	  		}	
    	}
    	else if(option == 5) {
	  		if(checkNumOfArmyCard(player.getCards(), armyTypes.ARTILLERY) >=1 & checkNumOfArmyCard(player.getCards(), armyTypes.CAVALRY) >=2) {
	  			player.addArmyToPlayer(new Army("Artillery", player));
	  			player.removeCardFromPlayer(armyTypes.ARTILLERY, 1);
	  			player.removeCardFromPlayer(armyTypes.CAVALRY, 2);
	  			player.setTotalArmy(30);
	  		}
    	}
    	else {
    		System.out.println("Option should be between 1 and 5");
    	}
    	
    	
//    	switch (option) {
//  	  	case 1:
//  	  		if (checkNumOfArmyCard(player.getCards(), armyTypes.INFANTRY) >=3) {
//  	  		/*	player.addArmyToPlayer(new Army("Cavalry", player));
//  	  			player.removeCardFromPlayer(armyTypes.INFANTRY, 3); */
//  	  			player.setTotalArmy(2);
//	  			player.removeCardFromPlayer(armyTypes.INFANTRY,3);
//  	  		}
//  	  		
//  	  		
//  	  		break;
//  	  	case 2:
//  	  		if (checkNumOfArmyCard(player.getCards(), armyTypes.INFANTRY) >=2 & checkNumOfArmyCard(player.getCards(), armyTypes.CAVALRY) >=1) { 
//  	  		
//  	  		/*	player.addArmyToPlayer(new Army("Cavalry", player));
//  	  			player.addArmyToPlayer(new Army("Cavalry", player)); */
//  	  			player.removeCardFromPlayer(armyTypes.INFANTRY, 2);
//  	  			player.removeCardFromPlayer(armyTypes.CAVALRY, 1);
//  	  			player.setTotalArmy(3);
//  	  			
//  	  		}
//  	  		
//  	  		break;
//  	  	case 3:
//  	  		if (checkNumOfArmyCard(player.getCards(), armyTypes.INFANTRY) >=2 & checkNumOfArmyCard(player.getCards(), armyTypes.ARTILLERY) >=1) { 
//  	  			
//  	  		/*	player.addArmyToPlayer(new Army("Artillery", player));
//  	  			player.addArmyToPlayer(new Army("Artillery", player)); */
//  	  			player.removeCardFromPlayer(armyTypes.INFANTRY, 2);
//  	  			player.removeCardFromPlayer(armyTypes.ARTILLERY, 1);
//  	  			player.setTotalArmy(8);
//  	  			
//  	  		}
//  	  		
//  	  		break;
//  	  	case 4:
//  	  		if (checkNumOfArmyCard(player.getCards(), armyTypes.INFANTRY) >=1 & checkNumOfArmyCard(player.getCards(), armyTypes.CAVALRY) >=2) { 
//  	  			
//  	  		/*	player.addArmyToPlayer(new Army("Cavalry", player));
//  	  			player.addArmyToPlayer(new Army("Artillery", player)); */
//  	  			player.removeCardFromPlayer(armyTypes.INFANTRY, 1);
//  	  			player.removeCardFromPlayer(armyTypes.CAVALRY, 2);
//  	  			player.setTotalArmy(4);
//  	  			
//  	  		} 
//  	  		
//  	  		break;
//  	  	case 5:
//  	  		if (checkNumOfArmyCard(player.getCards(), armyTypes.ARTILLERY) >=1 & checkNumOfArmyCard(player.getCards(), armyTypes.CAVALRY) >=2) { 
//  	  			
//  	  		/*	player.addArmyToPlayer(new Army("Artillery", player));
//  	  			player.addArmyToPlayer(new Army("Artillery", player));	*/
//  	  			player.addArmyToPlayer(new Army("Artillery", player));
//  	  			player.removeCardFromPlayer(armyTypes.ARTILLERY, 1);
//  	  			player.removeCardFromPlayer(armyTypes.CAVALRY, 2);
//  	  			player.setTotalArmy(10);
//  	  			
//  	  		} 
//  	  		
//  	  		break;
//    	}
    }
    
    //counts how many given army type cards are in the given card list
    public static int checkNumOfArmyCard(List<Card> cards, armyTypes armyType) {
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