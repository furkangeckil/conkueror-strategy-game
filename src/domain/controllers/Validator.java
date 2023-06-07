package domain.controllers;

import domain.player.Player;
import domain.territory.Territory;

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
    	}catch(NullPointerException e){
    		System.out.println("This territory doesn't have an owner yet");
    	}
        // Add more validation rules as needed

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
        
    	boolean canPlaceArmy = false;
    	
    	//Checks if the game is out of the initial sharing phase and if the player can place on the said territory.
    	
    	if(player.getUnassignedArmies() > 0) {
    		if(areTerritoriesOccupied(TerritoryController.getInstance().getAllTerritories()) == false & territory.getOwner() == null){
    			canPlaceArmy = true;
    		}
    		else if(areTerritoriesOccupied(TerritoryController.getInstance().getAllTerritories()) & belongsToPlayer(territory,player)) {
    			canPlaceArmy = true;
    		}
    	}
        return canPlaceArmy;
    }
    
    public static boolean belongsToPlayer(Territory territory, Player player) {
    	return territory.getOwner().equals(player);
    }
    
    public static boolean areTerritoriesOccupied(Territory[] territories) {
    	
    	boolean occupied = true;
    	
    	//Checks if all territories are occupied for the start of the game.
    	try {
    	for(Territory territory:territories) {
    		if(territory!=null) {
    			if(territory.getOwner() == null) {
    				occupied = false;
    			}
    		}
    	}}catch(NullPointerException ne){
    		occupied = false;
    		return occupied;
    	}
    	
    	return occupied;
    }
    
}
