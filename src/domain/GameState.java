package domain;

import java.util.List;
import java.util.Random;

public class GameState {
	private Player currentPlayer;
    private GameBoard gameBoard;
    private List<Player> players;
    private int numComputerPlayers;
    private List<String> nicknameList;
	private boolean buildingMode;
	private List<Territory> territories
    
    public GameState(List<Player> players, int numComputerPlayers, List<Territory> territories, List<String> nicknameList, boolean buildingMode) {
		// Set up game board, assign players
		this.players = players;
        this.numComputerPlayers = numComputerPlayers;
        this.nicknameList = nicknameList;
        this.territories = territories;
        gameBoard = new GameBoard(numContinents);
        
        if(buildingMode == False) {
        	Random random = new Random();
        	for(int i = 0; i<territories.length; i++) {
        		while(true) {
        			Territory territory = territory[random.nextInt(territories.length)];
        			if(territory.getOwner() == null)
        				break; 
        		}
        		Player player = players[i%(players.length)];
        		territory.setOwner(player);
        	}
        }
        
	}

	public int getNumPlayers() {
        return numPlayers;
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

}
