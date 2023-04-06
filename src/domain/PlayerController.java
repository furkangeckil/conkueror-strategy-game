package domain;

public class PlayerController {
	private GameState gameState;
	private GameBoard gameBoard;
	
	public PlayerController(GameState gameState, GameBoard gameBoard) {
		this.gameState = gameState;
		this.gameBoard = gameBoard;
	}
	
	public void assignTurns() {
		// Assign the first turn to a player and create the turn arrangement
	}
}
