package domain.game;
import java.util.ArrayList;
import java.util.List;

import domain.territory.Continent;
import domain.territory.Territory;
public class GameBoard {
	private int numContinents;
	private List<Territory> territories;
    private List<Continent> continents;
	
	public GameBoard(int numContinents) {
		this.numContinents = numContinents;
		territories = new ArrayList<>();
        continents = new ArrayList<>();
	}
	
	public int getNumContinents() {
        return numContinents;
    }
	public void renderGame() {
		// Render game map and display the initial game state
	}
}