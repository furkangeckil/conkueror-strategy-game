package domain.controllers;

import java.util.ArrayList;

import domain.game.GameBoard;
import domain.game.GameState;
import domain.player.Player;

public class PlayerController {
	private GameState gameState;
	private GameBoard gameBoard;
	private ArrayList<Player> players = new ArrayList<Player>();
    private int currentPlayerIndex = 0;
    private static PlayerController instance;
	
	private PlayerController() {
		
	}
	
	public static PlayerController getInstance() {
		if (instance == null) {
			instance = new PlayerController();
			return instance;
		}
		else {
			return instance;
		}
	}
    

    public void assignTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
    
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }
    
    
    
    public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}


	public void createPlayers(ArrayList<String> nickNames) {
    	for (String nickName :  nickNames) {
    		Player player = new Player(nickName);
    		GameState.addPlayerToList(player);
    		players.add(player);
    	}
    }
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
}