package domain.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import domain.player.Player;
import domain.territory.*;

public class TerritoryController {

    private static TerritoryController instance;

    static Continent northAmerica = new Continent();
    static Continent southAmerica = new Continent();
    static Continent africa = new Continent();
    static Continent europe = new Continent();
    static Continent oceania = new Continent();
    static Continent asia = new Continent();

    
    
    static Territory alaska = new Territory("alaska",null,northAmerica,0);   
    static  Territory northWestTerritory = new Territory("northWestTerritory", null, northAmerica,0);
    static  Territory alberta= new Territory("alberta",null, northAmerica,0);
    static  Territory ontario = new Territory("ontario",null, northAmerica,0);
    static	Territory quebec = new Territory("quebec",null, northAmerica,0);
    static	Territory westernUnitedStates = new Territory("westernUnitedStates",null, northAmerica,0);
    static	Territory easternUnitedStates = new Territory("easternUnitedStates",null, northAmerica,0);
    static	Territory centralAmerica = new Territory("centralAmerica",null, northAmerica,0);
    static	Territory greenland  = new Territory("greenland",null, northAmerica,0);
    
    static	Territory venezuela = new Territory("venezuela",null, southAmerica,0);
    static	Territory brazil = new Territory("brazil",null, southAmerica,0);
    static	Territory peru = new Territory("peru",null, southAmerica,0);
    static	Territory argentina = new Territory("argentina",null, southAmerica,0);
  
    static	Territory iceland = new Territory("iceland",null, africa,0);
    static	Territory britain = new Territory("britain",null, africa,0);
    static	Territory scandinavia = new Territory("scandinavia",null, africa,0);
    static	Territory ukraine = new Territory("ukraine",null, africa,0);
    static	Territory northernEurope = new Territory("northernEurope",null, africa,0);
    static	Territory southernEurope = new Territory("southernEurope",null, africa,0);
    static	Territory westernEurope = new Territory("westernEurope",null, africa,0);
    
    static	Territory egypt = new Territory("egypt",null, europe,0);
    static	Territory northAfrica = new Territory("northAfrica",null, europe,0);
    static  Territory eastAfrica = new Territory("eastAfrica",null, europe,0);
    static	Territory congo = new Territory("congo",null, europe,0);
    static	Territory southAfrica = new Territory("southAfrica",null, europe,0);
    static	Territory madagascar = new Territory("madagascar",null, europe,0);
    
    static	Territory middleEast = new Territory("middleEast",null, asia,0);
    static	Territory afghanistan = new Territory("afghanistan",null, asia,0);
    static	Territory ural = new Territory("ural",null, asia,0);
    static	Territory siberia = new Territory("siberia",null, asia,0);
    static	Territory yakutsk = new Territory("yakutsk",null, asia,0);
    static	Territory irkutsk = new Territory("irkutsk",null, asia,0);
    static	Territory mongolia = new Territory("mongolia",null, asia,0);
    static	Territory china = new Territory("china",null, asia,0);
    static	Territory india = new Territory("india",null, asia,0);
    static	Territory slam = new Territory("slam",null, asia,0);
    static	Territory kamchatka = new Territory("kamchatka",null, asia,0);
    static	Territory japan = new Territory("japan",null, asia,0);
    
    static	Territory indonesia = new Territory("indonesia",null, oceania,0);
    static	Territory newGuiena = new Territory("newGuiena",null, oceania,0);
    static	Territory easternAustralia = new Territory("easternAustralia",null, oceania,0);
    static	Territory westernAustralia = new Territory("westernAustralia",null, oceania,0);
    
    public Territory[] getAllTerritories() {
        Territory[] territories = new Territory[]{
        		null,null,null,northWestTerritory,
        		iceland,greenland,null,alaska,
        		null,scandinavia,null,yakutsk,britain,
        		null,ural,ontario,alberta,
        		northernEurope,irkutsk,null,siberia,
        		quebec,kamchatka,ukraine,null,null,afghanistan,
        		southernEurope,westernEurope,westernUnitedStates,mongolia,
        		japan,null,easternUnitedStates,egypt,
        		china,middleEast,india,centralAmerica,
        		northAfrica,slam,venezuela,null,
        		eastAfrica,congo,indonesia,newGuiena,
        		null,peru,brazil,madagascar,
        		southAfrica,null,westernAustralia,null,
        		easternAustralia,argentina,     
        };
        
        return territories;
    }
    
    public Territory findTerritory(String name) {
	
		for(Territory territory :  getAllTerritories()) {
			if(territory != null) {
				if(territory.getName() == name) {
					return territory;
				}
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
            
            
            //North America
            alaska.addNeighbor(northWestTerritory);
            alaska.addNeighbor(alberta);
            alaska.addNeighbor(kamchatka);
            
            northWestTerritory.addNeighbor(alaska);
            northWestTerritory.addNeighbor(alberta);
            northWestTerritory.addNeighbor(ontario);
            northWestTerritory.addNeighbor(greenland);
  
            alberta.addNeighbor(alaska);
            alberta.addNeighbor(northWestTerritory);
            alberta.addNeighbor(ontario);
            alberta.addNeighbor(westernUnitedStates);
 
            ontario.addNeighbor(northWestTerritory);
            ontario.addNeighbor(alberta);
            ontario.addNeighbor(westernUnitedStates);
            ontario.addNeighbor(easternUnitedStates);
            ontario.addNeighbor(quebec);
            ontario.addNeighbor(greenland);
            
            quebec.addNeighbor(ontario);
            quebec.addNeighbor(easternUnitedStates);
            quebec.addNeighbor(greenland);
            
            westernUnitedStates.addNeighbor(alberta);
            westernUnitedStates.addNeighbor(ontario);
            westernUnitedStates.addNeighbor(easternUnitedStates);
            westernUnitedStates.addNeighbor(centralAmerica);
            
            easternUnitedStates.addNeighbor(ontario);
            easternUnitedStates.addNeighbor(quebec);
            easternUnitedStates.addNeighbor(westernUnitedStates);
            easternUnitedStates.addNeighbor(centralAmerica);
            
            centralAmerica.addNeighbor(westernUnitedStates);
            centralAmerica.addNeighbor(easternUnitedStates);
            centralAmerica.addNeighbor(venezuela);
                     
            greenland.addNeighbor(northWestTerritory);
            greenland.addNeighbor(ontario);
            greenland.addNeighbor(quebec);
            greenland.addNeighbor(iceland);
            
            
            
            //South America
            venezuela.addNeighbor(centralAmerica);
            venezuela.addNeighbor(brazil);
            venezuela.addNeighbor(peru);
            
            peru.addNeighbor(venezuela);
            peru.addNeighbor(brazil);
            peru.addNeighbor(argentina);
            
            brazil.addNeighbor(venezuela);
            brazil.addNeighbor(peru);
            brazil.addNeighbor(argentina);
            brazil.addNeighbor(northAfrica);
            
            argentina.addNeighbor(peru);
            argentina.addNeighbor(brazil);
            
            
            
            //Europe
            iceland.addNeighbor(greenland);
            iceland.addNeighbor(britain);
            iceland.addNeighbor(scandinavia);

            britain.addNeighbor(iceland);
            britain.addNeighbor(scandinavia);
            britain.addNeighbor(northernEurope);
            britain.addNeighbor(westernEurope);
            
            scandinavia.addNeighbor(ukraine);
            scandinavia.addNeighbor(northernEurope);
            scandinavia.addNeighbor(britain);
            scandinavia.addNeighbor(iceland);
            
            ukraine.addNeighbor(ural);
            ukraine.addNeighbor(afghanistan);
            ukraine.addNeighbor(middleEast);
            ukraine.addNeighbor(southernEurope);
            ukraine.addNeighbor(northernEurope);
            ukraine.addNeighbor(scandinavia);
           
            northernEurope.addNeighbor(scandinavia);
            northernEurope.addNeighbor(ukraine);
            northernEurope.addNeighbor(southernEurope);
            northernEurope.addNeighbor(westernEurope);
            northernEurope.addNeighbor(britain);
            
            southernEurope.addNeighbor(northernEurope);
            southernEurope.addNeighbor(ukraine);
            southernEurope.addNeighbor(middleEast);
            southernEurope.addNeighbor(egypt);
            southernEurope.addNeighbor(northAfrica);
            southernEurope.addNeighbor(westernEurope);
            
            westernEurope.addNeighbor(britain);
            westernEurope.addNeighbor(northernEurope);
            westernEurope.addNeighbor(southernEurope);
            westernEurope.addNeighbor(northAfrica);
            
            
            
            //Africa
            northAfrica.addNeighbor(westernEurope);
            northAfrica.addNeighbor(southernEurope);
            northAfrica.addNeighbor(egypt);
            northAfrica.addNeighbor(eastAfrica);
            northAfrica.addNeighbor(congo);
            northAfrica.addNeighbor(brazil);
            
            egypt.addNeighbor(southernEurope);
            egypt.addNeighbor(middleEast);
            egypt.addNeighbor(eastAfrica);
            egypt.addNeighbor(northAfrica);
            
            eastAfrica.addNeighbor(egypt);
            eastAfrica.addNeighbor(middleEast);
            eastAfrica.addNeighbor(madagascar);
            eastAfrica.addNeighbor(southAfrica);
            eastAfrica.addNeighbor(congo);
            eastAfrica.addNeighbor(northAfrica);
            
            congo.addNeighbor(northAfrica);
            congo.addNeighbor(eastAfrica);
            congo.addNeighbor(southAfrica);

            southAfrica.addNeighbor(congo);
            southAfrica.addNeighbor(eastAfrica);
            southAfrica.addNeighbor(madagascar);

            madagascar.addNeighbor(eastAfrica);
            madagascar.addNeighbor(southAfrica);

            
            
            //Asia
            middleEast.addNeighbor(ukraine);
            middleEast.addNeighbor(afghanistan);
            middleEast.addNeighbor(india);
            middleEast.addNeighbor(eastAfrica);
            middleEast.addNeighbor(egypt);
            middleEast.addNeighbor(southernEurope);
            
            afghanistan.addNeighbor(ural);
            afghanistan.addNeighbor(china);
            afghanistan.addNeighbor(india);
            afghanistan.addNeighbor(middleEast);
            afghanistan.addNeighbor(ukraine);
       
            ural.addNeighbor(siberia);
            ural.addNeighbor(china);
            ural.addNeighbor(afghanistan);
            ural.addNeighbor(ukraine);
            
            india.addNeighbor(china);
            india.addNeighbor(slam);
            india.addNeighbor(middleEast);
            india.addNeighbor(afghanistan);
            
            china.addNeighbor(siberia);
            china.addNeighbor(mongolia);
            china.addNeighbor(slam);
            china.addNeighbor(india);
            china.addNeighbor(afghanistan);
            china.addNeighbor(ural);
          
            siberia.addNeighbor(yakutsk);
            siberia.addNeighbor(irkutsk);
            siberia.addNeighbor(mongolia);
            siberia.addNeighbor(china);
            siberia.addNeighbor(ural);
            
            slam.addNeighbor(china);
            slam.addNeighbor(indonesia);
            slam.addNeighbor(india);

            mongolia.addNeighbor(irkutsk);
            mongolia.addNeighbor(kamchatka);
            mongolia.addNeighbor(japan);
            mongolia.addNeighbor(china);
            mongolia.addNeighbor(siberia);

            irkutsk.addNeighbor(yakutsk);
            irkutsk.addNeighbor(kamchatka);
            irkutsk.addNeighbor(mongolia);
            irkutsk.addNeighbor(siberia);

            yakutsk.addNeighbor(kamchatka);
            yakutsk.addNeighbor(irkutsk);
            yakutsk.addNeighbor(siberia);

            kamchatka.addNeighbor(alaska);
            kamchatka.addNeighbor(japan);
            kamchatka.addNeighbor(irkutsk);
            kamchatka.addNeighbor(yakutsk);
            kamchatka.addNeighbor(mongolia);
            
            japan.addNeighbor(kamchatka);
            japan.addNeighbor(mongolia);
                       
            
            
            //Indian Ocean
            indonesia.addNeighbor(slam);
            indonesia.addNeighbor(newGuiena);
            indonesia.addNeighbor(westernAustralia);
            
            newGuiena.addNeighbor(indonesia);
            newGuiena.addNeighbor(easternAustralia);
            newGuiena.addNeighbor(westernAustralia);
                      
            westernAustralia.addNeighbor(indonesia);
            westernAustralia.addNeighbor(newGuiena);
            westernAustralia.addNeighbor(easternAustralia);
                    
            easternAustralia.addNeighbor(newGuiena);
            easternAustralia.addNeighbor(westernAustralia);
            
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
