package domain;

import java.util.List;

public class GameSetup {
	/*public void createAndSetupNewGame() {
		
	}*/
	public GameState setupNewGame(int numPlayers, int numComputerPlayers, int numContinents, List<String> nicknameList) {
		GameState gameState = new GameState(numPlayers, numComputerPlayers, numContinents, nicknameList);

		return gameState;
	}
}
