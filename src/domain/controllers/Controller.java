package domain.controllers;


import ui.GameSetupUI;
import ui.LoginUI;
import ui.MainMenu;
import ui.UserNameUI;
import ui.WorldMapArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import domain.game.GameSetup;
import domain.game.GameState;
import domain.player.Player;
import domain.territory.Territory;

public class Controller {
	private LoginUI loginUI = new LoginUI();
	private MainMenu mainMenuUI = new MainMenu();
	private GameState gameState;
	private GameSetupUI gameSetupUI = new GameSetupUI();
	private UserNameUI userNameUI = new UserNameUI();
	private GameSetup gameSetup;
	private TerritoryController territoryController= TerritoryController.getInstance();
	private Validator validator;
	private Territory fromTerritory;
	private Territory toTerritory;
	private static Controller instance;
	private ArrayList<String> nickNameList = new ArrayList<String>();
		
	private Controller() {
	}

	public static Controller getInstance(){
		if(instance == null) {
			instance = new Controller();
			return instance;
			
		}
		else {
			return instance;
		}
		
	}
	
	public void updateTerritoryOnMap(Territory territory) {
		WorldMapArea.updateTerritory (PlayerController.getInstance().getCurrentPlayerIndex(), territory.getName(), territory.getArmyNum());
	}

	public boolean loginUser(String username, String password) {
		if (UserManager.authenticateUser(username, password)) {
            return true;
        } else {
            return false;
        }
	}
	
	public boolean registerUser(String username, String password) {
		if (UserManager.registerUser(username, password)){
			return true;
		} else {
			return false;
		}
	}
	
	public void setLoginUI(LoginUI ui) {
		this.loginUI = ui;
	}
	
	public void createPlayers(ArrayList<String> nicknameList) {
		PlayerController.getInstance().createPlayers(nicknameList);
	}
	
	public int getNumContinents() {
		return GameState.getNumContinents();
	}
	
	public List<String> getNicknameList(){
		return GameState.getNicknameList();
	}
	
	public String[][] getTerritoryMatrix(){
		return TerritoryController.getInstance().createTerritoryMatrix();
	}
	
	public void endTurn() {
		PlayerController.getInstance().assignTurn();
		System.out.println("end turn button, currentplayer: " + PlayerController.getInstance().getCurrentPlayer() + "\n");
	}

	public boolean attack(Territory fromTerritory, Territory toTerritory) {
		if(Validator.validateAttack(fromTerritory, toTerritory)) {
			PlayerController.getInstance().getCurrentPlayer().attack(fromTerritory, toTerritory);
			System.out.println(fromTerritory.getName() + "   " + toTerritory.getName());
			return true;
		}
		return false;
	}
	
	public boolean fortify(Territory fromTerritory, Territory toTerritory, int armyNum) {
		if(Validator.validateFortify(fromTerritory, toTerritory, armyNum)) {
			PlayerController.getInstance().getCurrentPlayer().fortify(fromTerritory, toTerritory, armyNum);
			System.out.println(fromTerritory.getName() + "   " + toTerritory.getName());
			return true;
		}
		return false;
	}
		
	public boolean recruit(Territory toTerritory, Player currentPlayer, int number) {
		if(Validator.validateRecruit(toTerritory,currentPlayer,number)) {
			currentPlayer.placeArmies(toTerritory,number);
			System.out.println("Territory Name: "+ toTerritory.getName());
			return true;
		}
		return false;
	}
	
	public GameState getGameState() {
		return gameState;
	}

	public Player getCurrentPlayer() {
		return PlayerController.getInstance().getCurrentPlayer();
	}
	
	public int getCurrentPlayerArmyNum() {
		return PlayerController.getInstance().getCurrentPlayer().getUnassignedArmies();
	}
	
	public Territory findTerritory(String name) {
		return TerritoryController.getInstance().findTerritory(name);
	}
	
	public boolean areTerritoriesOccupied() {
		return Validator.areTerritoriesOccupied(TerritoryController.getInstance().getAllTerritories());
	}
	
	public Territory[] getAllTerritories() {
		return TerritoryController.getInstance().getAllTerritories();
	}
	
	public int getTerritoryArmyNum(Territory territory) {
		return territory.getArmyNum();
	}
	
	public String getTerritoryName(Territory territory) {
		return territory.getName();
	}
	
	public int getCurrentPlayerIndex() {
		return PlayerController.getInstance().getCurrentPlayerIndex();
	}
	
	public Player getOwner(Territory territory) {
		return territory.getOwner();
	}
	
	public boolean areInitialArmiesPlaced() {
		return Validator.areInitialArmiesPlaced();
	}
	
	public int getIndex(Player player) {
		int index = 0;
		ArrayList<Player> players = PlayerController.getInstance().getPlayers();
		for(int i = 0; i<players.size();i++) {			
			if(player == PlayerController.getInstance().getPlayers().get(i)) {
				index  = i;
				break;
			}
		}
		
		return index;
	}
	
    public boolean checkGameOver() {
        Territory[] territories = getAllTerritories();
    	int numTerritories = territories.length;
        int count = 0;

        for(int i = 0; i < numTerritories; i++) {
            if(territories[i] != null && territories[i].getOwner() == Controller.getInstance().getCurrentPlayer()) {
                count++;
            }
        }

        if(count == 42) {
            return true;
        }
        else {
            return false;
        }
    }
}
