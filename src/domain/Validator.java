package domain;


public class Validator {

    public static boolean validateAttack(GameState gameState, Territory attacker, Territory defender, int attackUnits) {
        if (attacker.getOwner() == defender.getOwner()) {
            System.out.println("You cannot attack your own territory.");
            return false;
        }

        if (attackUnits < 1 || attackUnits >= attacker.getArmyNum()) {
            System.out.println("Invalid number of attack units.");
            return false;
        }

        // Add more validation rules as needed

        return true;
    }

    public static boolean validateRecruit(GameState gameState, Territory territory, int recruitUnits) {
        if (recruitUnits < 1) {
            System.out.println("Invalid number of recruit units.");
            return false;
        }

        // Add more validation rules as needed

        return true;
    }

    public static boolean validateFortify(GameState gameState, Territory source, Territory destination, int fortifyUnits) {
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
}
