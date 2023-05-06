package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TerritoryController {
    
    private static TerritoryController instance;
    public static TerritoryController getInstance() {
        if (instance == null) {
            instance = new TerritoryController();
        }
        return instance;
    }



    Territory westEurope = new Territory(null, null);
    Territory eastEurope = new Territory(null, null);
    Territory midEurope = new Territory(null, null);
    Territory turkey = new Territory(null, null);
    Territory scandinavia = new Territory(null, null);
    Territory russia = new Territory(null, null);
    Territory britania = new Territory(null, null);



    Territory india = new Territory(null, null);
    Territory china = new Territory(null, null);
    Territory japan = new Territory(null, null);
    Territory mongolia = new Territory(null, null);
    Territory singapure  = new Territory(null, null);
    Territory iran = new Territory(null, null);
    Territory afghanistan = new Territory(null, null);



    Territory egypt = new Territory(null, null);
    Territory morocco = new Territory(null, null);
    Territory southAfrica = new Territory(null, null);
    Territory madagascar = new Territory(null, null);



    Territory brasil = new Territory(null, null);
    Territory peru = new Territory(null, null);
    Territory argantina = new Territory(null, null);
    Territory venesuela = new Territory(null, null);



    Territory eastCanada = new Territory(null, null);
    Territory westCanada = new Territory(null, null);
    Territory eastUSA = new Territory(null, null);
    Territory westUSA = new Territory(null, null);
    Territory mexico = new Territory(null, null);



    Territory eastAustralia = new Territory(null, null);
    Territory westAustralia = new Territory(null, null);
    Territory newZelland = new Territory(null, null);
    Territory indonesia = new Territory(null, null);
    
    
    public Territory[] getAllTerritories() {
        Territory[] territories = new Territory[]{
            westEurope, eastEurope, midEurope, turkey, scandinavia, russia, britania,
            india, china, japan, mongolia, singapure, iran, afghanistan,
            egypt, morocco, southAfrica, madagascar,
            brasil, peru, argantina, venesuela,
            eastCanada, westCanada, eastUSA, westUSA, mexico,
            eastAustralia, westAustralia, newZelland, indonesia
        };

        return territories;
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
