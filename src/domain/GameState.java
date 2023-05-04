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
    
    public GameState(List<Player> players, int numComputerPlayers, List<Territory> territories, List<String> nicknameList) {
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

	public GameState(int numPlayers, int numComputerPlayers2, int numContinents2, List<String> nicknameList2) {
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

}