package domain;


public class Validator {

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

        return true;
    }

    public static boolean validateRecruit(Territory territory, int recruitUnits) {
        if (recruitUnits < 1) {
            System.out.println("Invalid number of recruit units.");
            return false;
        }

        // Add more validation rules as needed

        return true;
    }

    public static boolean validateFortify( Territory source, Territory destination, int fortifyUnits) {
        if (source.getOwner() != destination.getOwner()) {
            System.out.println("You can only fortify your own territories.");
            return false;
        }

        if (fortifyUnits < 1 || fortifyUnits >= source.getArmyNum()) {
            System.out.println("Invalid number of fortify units.");
            return false;
        }

        // Add more validation rules as needed

        return true;
    }



    public static boolean canPlaceArmies(Territory territory, Player player) {
        // Check if the territory is unoccupied or belongs to the player
        //return territory.getOwner() == null || territory.getOwner().equals(player);
    	return true;
    }
    
    public static boolean isBelongPlayer(Territory territory, Player player) {
    	return territory.getOwner().equals(player);
    }
}
