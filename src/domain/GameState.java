package domain;

import java.util.*;
public class GameState {
	private Player currentPlayer;
    private GameBoard gameBoard;
    private List<Player> players;
    private int numComputerPlayers;
    private List<String> nicknameList;
	private boolean buildingMode;
	private List<Territory> territories;
	private int numContinents;
	private int numPlayers;
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
	
	public GameState(int numPlayers, int numComputerPlayers, int numContinents, List <String> nicknameList){
		this.numPlayers = numPlayers;
		this.numComputerPlayers = numComputerPlayers;
		this.numContinents = numContinents;
		this.nicknameList = nicknameList;
	}
    
	public int getNumPlayers() {
        return players.size();
    }

    public int getNumComputerPlayers() {
        return numComputerPlayers;
    }

    public List<String> getNicknameList() {
        return nicknameList;
    }

	public int getNumContinents() {
        return gameBoard.getNumContinents();
    }
	
	//calls applyEffect function of card class to use effect of territory card (P.S: this can be used for chance card also)
	public void useCard(Player player, Card card ) {
		card.applyEffect(player);
	}
	
	//calls tradeArmyCards function of ArmyCard class to change army cards with army
	public void tradeArmyCards(Player player, int option) {
		ArmyCard.tradeArmyCards(player, option);
	}

}
