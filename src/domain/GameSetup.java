package domain;

public class GameSetup {
	public void createAndSetupNewGame() {
		GameState gameState = new GameState();
		gameState.setupGame();
		
		GameBoard gameBoard = new GameBoard(gameState);
		gameBoard.renderGame();
		
		PlayerController playerController = new PlayerController(gameState, gameBoard);
		playerController.assignTurns();
	}
}
