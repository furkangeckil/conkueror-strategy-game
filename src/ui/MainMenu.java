package ui;
import domain.GameSetup;

public class MainMenu {
	public void startNewGame() {
		GameSetup gameSetup = new GameSetup();
		gameSetup.createAndSetupNewGame();
	}
}
