package domain.game;

import java.util.*;

import domain.card.ArmyCard;
import domain.card.Card;
import domain.player.Player;
import domain.territory.Territory;
public class GameState {
	private static Player currentPlayer;
    private static GameBoard gameBoard;
    private static ArrayList<Player> players = new ArrayList<Player>();
    private static int numComputerPlayers;
    private static ArrayList<String> nicknameList = new ArrayList<String>();
	private static ArrayList<Territory> territories = new ArrayList<Territory>();
	private static int numContinents;
	private static int numPlayers;
	
	//private static GameState instance;
    
    /*public GameState(List<Player> players, int numComputerPlayers, List<Territory> territories, List<String> nicknameList) {
		// Set up game board, assign players
		this.players = players;
        this.numComputerPlayers = numComputerPlayers;
        this.nicknameList = nicknameList;
        this.territories = territories;
        gameBoard = new GameBoard(numContinents);
        
        Random random = new Random();
        for(int i = 0; i<territories.size(); i++) {
        	while(true) {
        		int x = random.nextInt(territories.size());
        		if(territories.get(x).getOwner() != null)
        			continue;
        		else {
        			Territory territory = territories.get(x);
            		Player player = players.get(i%(players.size()));
            		territory.setOwner(player);
            		break;
        		}
        	}
        }
	}
	

    public static GameState getInstance(List<Player> players, int numComputerPlayers, List<Territory> territories, List<String> nicknameList) {
    	if (instance == null) {
    		instance = new GameState(players, numComputerPlayers,territories,nicknameList);
    	}
    	return instance;
    }
    
    public static GameState getInstance() {
    	if (instance == null) {
    		instance = new GameState(null,null,null,null);
    	}
    	return instance;
    }*/
	
	/*public int getNumPlayers() {
    return players.size();
	}*/

	
	public GameState(int numPlayers, int numComputerPlayers, int numContinents, ArrayList <String> nicknameList){
		GameState.numPlayers = numPlayers;
		GameState.numComputerPlayers = numComputerPlayers;
		GameState.numContinents = numContinents;
		GameState.nicknameList = nicknameList;
		gameBoard = new GameBoard(numContinents);
	}
    
	public Territory findTerritory(String name) {
		
		for(Territory territory :  territories) {
			if(territory.getName() == name) {
				return territory;
			}
		}
		return null;
	}
	
	
	public static int getNumPlayers() {
        return numPlayers;
    }

    public static int getNumComputerPlayers() {
        return numComputerPlayers;
    }

    public static List<String> getNicknameList() {
        return nicknameList;
    }

	public static int getNumContinents() {
        return numContinents;
    }
	
	public static void addPlayerToList(Player player) {
		players.add(player);
	}
	
	
	
	public static List<Territory> getTerritories() {
		return territories;
	}



	//calls applyEffect function of card class to use effect of territory card (P.S: this can be used for chance card also)
//	public void useCard(Player player, Card card ) {
//		card.applyEffect(player);
//	}
	
	//calls tradeArmyCards function of ArmyCard class to change army cards with army
	public void tradeArmyCards(Player player, int option) {
		ArmyCard.tradeArmyCards(player, option);
	}

}