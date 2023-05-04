package domain;

import java.util.List;

public class GameSetup {
	private Game game = new Game();
	public void createAndSetupNewGame() {
    	GameThread gameThread = new GameThread(game);
    	gameThread.start(); 
	}

	public GameState setupNewGame(int numPlayers, int numComputerPlayers, int numContinents, List<String> nicknameList) {
		GameState gameState = new GameState(numPlayers, numComputerPlayers, numContinents, nicknameList);

		return gameState;
	}

	public Game getGame() {
		return game;
	}
}
