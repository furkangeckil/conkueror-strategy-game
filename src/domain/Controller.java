package domain;


import ui.BoardUI;
import ui.GameSetupUI;
import ui.LoginUI;
import ui.MainMenu;
import ui.UserNameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;



import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Controller {
	private LoginUI loginUI = new LoginUI();
	private MainMenu mainMenuUI = new MainMenu();
	private BoardUI boardUI= new BoardUI();
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
		loginUI.showLoginScreen();
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
		BoardUI.updateTerritory (PlayerController.getInstance().getCurrentPlayerIndex(), territory.getName(), territory.getArmyNum());
	}

	public boolean loginUser(String username, String password) {
		if (UserManager.authenticateUser(username, password)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(loginUI.getLoginFrame(), "Login failed");
            return false;
        }
	}
	
	public void registerUser(String username, String password) {
		UserManager.registerUser(username, password);
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
		
	public boolean recruit(Territory toTerritory, Player currentPlayer) {
		if(Validator.canPlaceArmies(toTerritory, currentPlayer)) {
			currentPlayer.placeArmies(toTerritory);//should be modified
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
	
	public Territory findTerritory(String name) {
		return TerritoryController.getInstance().findTerritory(name);
	}
	
	public boolean areTerritoriesOccupied() {
		return Validator.areTerritoriesOccupied(TerritoryController.getInstance().getAllTerritories());
	}
	
}
