package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TerritoryController {

    private static TerritoryController instance;

    Territory westEurope = new Territory("WE", null, null);
    Territory eastEurope = new Territory("EE",null, null);
    Territory midEurope = new Territory("ME",null, null);
    Territory turkey = new Territory("T",null, null);
    Territory scandinavia = new Territory("S",null, null);
    Territory russia = new Territory("R",null, null);
    Territory britain = new Territory("B",null, null);

    Territory india = new Territory("I",null, null);
    Territory china = new Territory("C",null, null);
    Territory japan = new Territory("J",null, null);
    Territory mongolia = new Territory("M",null, null);
    Territory singapure  = new Territory("Si",null, null);
    Territory iran = new Territory("Ir",null, null);
    Territory afghanistan = new Territory("A",null, null);



    Territory egypt = new Territory("E",null, null);
    Territory morocco = new Territory("M",null, null);
    Territory southAfrica = new Territory("SA",null, null);
    Territory madagascar = new Territory("Ma",null, null);



    Territory brasil = new Territory("Br",null, null);
    Territory peru = new Territory("P",null, null);
    Territory argantina = new Territory("Ar",null, null);
    Territory venesuela = new Territory("V",null, null);



    Territory eastCanada = new Territory("EC",null, null);
    Territory westCanada = new Territory("WC",null, null);
    Territory eastUSA = new Territory("EU",null, null);
    Territory westUSA = new Territory("WU",null, null);
    Territory mexico = new Territory("Me",null, null);



    Territory eastAustralia = new Territory("EA",null, null);
    Territory westAustralia = new Territory("WA",null, null);
    Territory newZelland = new Territory("NZ",null, null);

    
    
    public Territory[] getAllTerritories() {
        Territory[] territories = new Territory[]{
            westEurope, eastEurope, midEurope, turkey, scandinavia, russia, britain,
            india, china, japan, mongolia, singapure, iran, afghanistan,
            egypt, morocco, southAfrica, madagascar,
            brasil, peru, argantina, venesuela,
            eastCanada, westCanada, eastUSA, westUSA, mexico,
            eastAustralia, westAustralia, newZelland
        };

        return territories;
    }

    public static TerritoryController getInstance() {
        if (instance == null) {
            instance = new TerritoryController();
        }
        return instance;
    }


    public void distributeInitialArmies(List<Player> players) {
        Territory[] territories = getAllTerritories();
        int numberOfPlayers = players.size();
        int initialArmiesPerPlayer;

        switch (numberOfPlayers) {
            case 3:
                initialArmiesPerPlayer = 35;
                break;
            case 4:
                initialArmiesPerPlayer = 30;
                break;
            case 5:
                initialArmiesPerPlayer = 25;
                break;
            case 6:
                initialArmiesPerPlayer = 20;
                break;
            default:
                throw new IllegalArgumentException("Invalid number of players: " + numberOfPlayers);
        }

        assignPlayersToTerritories(players, territories);
        distributeArmies(players, territories, initialArmiesPerPlayer);
    }

    private void assignPlayersToTerritories(List<Player> players, Territory[] territories) {
        List<Player> playerAssignmentOrder = new ArrayList<>(players);
        Collections.shuffle(playerAssignmentOrder);
        int numberOfPlayers = players.size();

        for (int i = 0; i < territories.length; i++) {
            Player currentPlayer = playerAssignmentOrder.get(i % numberOfPlayers);
            territories[i].setOwner(currentPlayer);
            territories[i].setArmyNum(1);
            currentPlayer.decreaseUnassignedArmies(1);
        }
    }

    private void distributeArmies(List<Player> players, Territory[] territories, int initialArmiesPerPlayer) {
        Random random = new Random();

        for (Player player : players) {
            int remainingArmies = initialArmiesPerPlayer - player.getTerritories().size();
            while (remainingArmies > 0) {
                Territory randomTerritory = territories[random.nextInt(territories.length)];
                if (randomTerritory.getOwner().equals(player)) {
                    randomTerritory.addArmy(1);
                    remainingArmies--;
                }
            }
        }
    }




    

}
