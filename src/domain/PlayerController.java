package domain;

import java.util.ArrayList;

public class PlayerController {
	private GameState gameState;
	private GameBoard gameBoard;
	private ArrayList<Player> players;
    private int currentPlayerIndex;

	
	public PlayerController(GameState gameState, GameBoard gameBoard, ArrayList<Player> players) {
		this.gameState = gameState;
		this.gameBoard = gameBoard;
		this.players = players;
		this.currentPlayerIndex = 0;
	}
	
    
    public PlayerController(ArrayList<Player> players) {
        this.players = players;
        currentPlayerIndex = 0;
    }

    public void assignTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
    
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }
}
