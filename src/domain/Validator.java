package domain;


public class Validator {

    public static boolean belongsToPlayer(Territory territory, Player player) {
    	return territory.getOwner().equals(player);
    }
	
    public static boolean validateAttack(Territory attacker, Territory defender) {
    	try {
    		if (attacker.getOwner() == defender.getOwner()) {
    			System.out.println("You cannot attack your own territory.");
    			return false;
    		}

    		if (attacker.getArmyNum() < 3 || attacker.getArmyNum() < defender.getArmyNum()) {
    			System.out.println("Invalid number of attack units.");
    			return false;
    		}
    		
    		if(attacker.isConnected(defender) == false) {
    			System.out.println("Territories are not neighbors");
    			return false;
    		}
    	}catch(NullPointerException e){
    		System.out.println("This territory doesn't have an owner yet");
    	}

        return repOk(attacker,attacker.getOwner()) && repOk(defender,defender.getOwner());
    }

    public static boolean validateRecruit(Territory territory, Player player) {
    	try {
    		if (territory.getOwner().equals(player)){
    			return repOk(territory, player);
    		}
    	}catch(NullPointerException e){
    		System.out.println("This territory doesn't have an owner yet");
    	}

        return false;
    }

    public static boolean validateFortify( Territory source, Territory destination, int fortifyUnits) {
    	try{   	
    		if (source.getOwner() != destination.getOwner()) {
    			System.out.println("You can only fortify your own territories.");
    			return false;
    		}

    		if (fortifyUnits < 1 || fortifyUnits >= source.getArmyNum()) {
    			System.out.println("Invalid number of fortify units.");
    			return false;
    		}

    		if(source.isConnected(destination) == false) {
    			System.out.println("Territories are not neighbors");
    			return false;
    		}
    		
    		if(repOk(source, source.getOwner()) && repOk(destination, destination.getOwner())) {
    			return true;
    		}
    	}catch(NullPointerException e) {
    		System.out.println("This territory doesn't have an owner yet.");
    	}
    	return false;
    }
        
    private static boolean repOk(Territory territory, Player player) {
    	if (territory.getArmyNum() < 0) {
            System.out.println("Invalid representation: Territory's army number cannot be negative.");
            return false;
        }
    	
    	if(player.getTotalArmy() < 0) {
    		System.out.println("Invalid representation: A players total army can't be negative");
		return false;
    	}
    	return true;
    }
}
