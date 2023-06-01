package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TerritoryController {

    private static TerritoryController instance;

    static  Territory westEurope = new Territory("WE", null, null,0);
    static  Territory eastEurope = new Territory("EE",null, null,0);
    static  Territory midEurope = new Territory("ME",null, null,0);
    static	Territory turkey = new Territory("T",null, null,0);
    static	Territory scandinavia = new Territory("S",null, null,0);
    static	Territory russia = new Territory("R",null, null,0);
    static	Territory britain = new Territory("B",null, null,0);

    static	Territory india = new Territory("I",null, null,0);
    static	Territory china = new Territory("C",null, null,0);
    static	Territory japan = new Territory("J",null, null,0);
    static	Territory mongolia = new Territory("M",null, null,0);
    static	Territory singapore  = new Territory("Si",null, null,0);
    static	Territory iran = new Territory("Ir",null, null,0);
    static	Territory afghanistan = new Territory("A",null, null,0);



    static	Territory egypt = new Territory("E",null, null,0);
    static	Territory morocco = new Territory("M",null, null,0);
    static	Territory southAfrica = new Territory("SA",null, null,0);
    static	Territory madagascar = new Territory("Ma",null, null,0);



    static	Territory brasil = new Territory("Br",null, null,0);
    static	Territory peru = new Territory("P",null, null,0);
    static	Territory argentina = new Territory("Ar",null, null,0);
    static  Territory venesuela = new Territory("V",null, null,0);



    static	Territory eastCanada = new Territory("EC",null, null,0);
    static	Territory westCanada = new Territory("WC",null, null,0);
    static	Territory eastUSA = new Territory("EU",null, null,0);
    static	Territory westUSA = new Territory("WU",null, null,0);
    static	Territory mexico = new Territory("Me",null, null,0);



    static	Territory eastAustralia = new Territory("EA",null, null,0);
    static	Territory westAustralia = new Territory("WA",null, null,0);
    static	Territory newZealand = new Territory("NZ",null, null,0);

    
    
    public Territory[] getAllTerritories() {
        Territory[] territories = new Territory[]{
            westEurope, eastEurope, midEurope, turkey, scandinavia, russia, britain,
            india, china, japan, mongolia, singapore, iran, afghanistan,
            egypt, morocco, southAfrica, madagascar,
            brasil, peru, argentina, venesuela,
            eastCanada, westCanada, eastUSA, westUSA, mexico,
            eastAustralia, westAustralia, newZealand
        };
        
        return territories;
    }
    
    public Territory findTerritory(String name) {
		
		for(Territory territory :  getAllTerritories()) {
			if(territory.getName() == name) {
				return territory;
			}
		}
		return null;
	}
    
    public String[][] createTerritoryMatrix() {
    	Territory[] territories = getAllTerritories();
        String[][] matrix = new String[7][12];
        matrix[0][1] = territories[0].getName();
        matrix[0][2] = territories[1].getName(); 
        matrix[0][3] = territories[2].getName();
        matrix[0][4] = territories[3].getName();
        matrix[0][5] = territories[4].getName();
        matrix[1][2] = territories[5].getName();
        matrix[1][3] = territories[6].getName();
        matrix[1][4] = territories[7].getName();
        matrix[1][5] = territories[8].getName();
        matrix[1][6] = territories[9].getName();
        matrix[2][3] = territories[10].getName();
        matrix[2][4] = territories[11].getName();
        matrix[2][5] = territories[12].getName();
        matrix[2][6] = territories[13].getName();
        matrix[2][7] = territories[14].getName();
        matrix[3][4] = territories[15].getName();
        matrix[3][5] = territories[16].getName();
        matrix[3][6] = territories[17].getName();
        matrix[3][7] = territories[18].getName();
        matrix[3][8] = territories[19].getName();
        matrix[4][5] = territories[20].getName();
        matrix[4][6] = territories[21].getName();
        matrix[4][7] = territories[22].getName();
        matrix[4][8] = territories[23].getName();
        matrix[4][9] = territories[24].getName();
        matrix[5][6] = territories[25].getName();
        matrix[5][7] = territories[26].getName();
        matrix[5][8] = territories[27].getName();
        matrix[5][9] = territories[28].getName();
        matrix[5][10] = territories[29].getName();
        return matrix;
    }

    public static TerritoryController getInstance() {
        if (instance == null) {
            instance = new TerritoryController();
            
            westEurope.addNeighbor(eastEurope);
            
            eastEurope.addNeighbor(westEurope);
            eastEurope.addNeighbor(midEurope);
            eastEurope.addNeighbor(russia);
            
            midEurope.addNeighbor(eastEurope);
            midEurope.addNeighbor(turkey);
            midEurope.addNeighbor(britain);
            
            turkey.addNeighbor(midEurope);
            turkey.addNeighbor(scandinavia);
            turkey.addNeighbor(india);
            
            scandinavia.addNeighbor(turkey);
            scandinavia.addNeighbor(china);
            
            
            russia.addNeighbor(eastEurope);
            russia.addNeighbor(britain);
            
            britain.addNeighbor(midEurope);
            britain.addNeighbor(russia);
            britain.addNeighbor(india);
            britain.addNeighbor(mongolia);
            
            india.addNeighbor(turkey);
            india.addNeighbor(britain);
            india.addNeighbor(china);
            india.addNeighbor(singapore);
            
            china.addNeighbor(scandinavia);
            china.addNeighbor(india);
            china.addNeighbor(japan);
            china.addNeighbor(iran);
            
            japan.addNeighbor(china);
            japan.addNeighbor(afghanistan);
            
            
            
            mongolia.addNeighbor(britain);
            mongolia.addNeighbor(singapore);
            
            singapore.addNeighbor(india);
            singapore.addNeighbor(mongolia);
            singapore.addNeighbor(iran);
            singapore.addNeighbor(morocco);
            
            iran.addNeighbor(china);
            iran.addNeighbor(singapore);
            iran.addNeighbor(afghanistan);
            iran.addNeighbor(southAfrica);
            
            afghanistan.addNeighbor(japan);
            afghanistan.addNeighbor(iran);
            afghanistan.addNeighbor(egypt);
            afghanistan.addNeighbor(madagascar);
            
            egypt.addNeighbor(afghanistan);
            egypt.addNeighbor(brasil);
            
            
            
            morocco.addNeighbor(singapore);
            morocco.addNeighbor(southAfrica);
            
            southAfrica.addNeighbor(iran);
            southAfrica.addNeighbor(morocco);
            southAfrica.addNeighbor(madagascar);
            southAfrica.addNeighbor(argentina);
            
            madagascar.addNeighbor(afghanistan);
            madagascar.addNeighbor(southAfrica);
            madagascar.addNeighbor(brasil);
            madagascar.addNeighbor(venesuela);
            
            brasil.addNeighbor(egypt);
            brasil.addNeighbor(madagascar);
            brasil.addNeighbor(peru);
            brasil.addNeighbor(eastCanada);
            
            peru.addNeighbor(brasil);
            peru.addNeighbor(westCanada);
            
            
            
            argentina.addNeighbor(southAfrica);
            argentina.addNeighbor(venesuela);
            
            venesuela.addNeighbor(madagascar);
            venesuela.addNeighbor(argentina);
            venesuela.addNeighbor(eastCanada);
            venesuela.addNeighbor(westUSA);
            
            eastCanada.addNeighbor(brasil);
            eastCanada.addNeighbor(venesuela);
            eastCanada.addNeighbor(westCanada);
            eastCanada.addNeighbor(mexico);
            
            westCanada.addNeighbor(peru);
            westCanada.addNeighbor(eastCanada);
            westCanada.addNeighbor(eastUSA);
            westCanada.addNeighbor(eastAustralia);
            
            eastUSA.addNeighbor(westCanada);
            eastUSA.addNeighbor(westAustralia);
            
            
            
            westUSA.addNeighbor(venesuela);
            westUSA.addNeighbor(mexico);
            
            mexico.addNeighbor(eastCanada);
            mexico.addNeighbor(westUSA);
            mexico.addNeighbor(eastAustralia);
            
            eastAustralia.addNeighbor(westCanada);
            eastAustralia.addNeighbor(mexico);
            eastAustralia.addNeighbor(westAustralia);
            
            westAustralia.addNeighbor(eastUSA);
            westAustralia.addNeighbor(eastAustralia);
            westAustralia.addNeighbor(newZealand);
            
            newZealand.addNeighbor(westAustralia);
                  
            
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
