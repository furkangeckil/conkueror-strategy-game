package domain;

import java.util.List;

public class GameState {
	private Player currentPlayer;
    private GameBoard gameBoard;
    private int numPlayers;
    private int numComputerPlayers;
    private List<String> nicknameList;
	public GameState(int numPlayers, int numComputerPlayers, int numContinents, List<String> nicknameList) {
		// Set up game board, assign players
		this.numPlayers = numPlayers;
        this.numComputerPlayers = numComputerPlayers;
        this.nicknameList = nicknameList;
        gameBoard = new GameBoard(numContinents);
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
	
	//calls applyEffect function of card class to use effect of territory card (P.S: this can be used for chance card also)
	public void useCard(Player player, Card card ) {
		card.applyEffect(player);
	}
	
	//calls tradeArmyCards function of ArmyCard class to change army cards with army
	public void tradeArmyCards(Player player, int option) {
		ArmyCard.tradeArmyCards(player, option);
	}

}
