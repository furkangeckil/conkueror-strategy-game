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

import ui.BoardUI.Mode;

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
	
	/*class LoginActionListener implements ActionListener{
		
		public LoginActionListener() {
			loginUI.addActionListener(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try{
				
				if(e.getSource() == loginUI.getLoginButton()) {
					System.out.println("LoginUI is working");
					String username = loginUI.getUsernameField().getText();
	                String password = new String(loginUI.getPasswordField().getPassword());

	                if (UserManager.authenticateUser(username, password)) {
	                    JOptionPane.showMessageDialog(loginUI.getLoginFrame(), "Login successful");
	                    mainMenuUI.startNewGame();
	                    MainMenuActionListener mainMenuActionListener = new MainMenuActionListener();
	                    loginUI.getLoginFrame().dispose();
	                } else {
	                    JOptionPane.showMessageDialog(loginUI.getLoginFrame(), "Login failed");
	                }
				}
				
				else if(e.getSource() == loginUI.getRegisterButton()){
					System.out.println("LoginUI is working");
					String username = loginUI.getUsernameField().getText();
	                String password = new String(loginUI.getPasswordField().getPassword());

	                UserManager.registerUser(username, password);
	                JOptionPane.showMessageDialog(loginUI.getLoginFrame(), "Registration successful");
	                mainMenuUI.startNewGame();
	                MainMenuActionListener mainMenuActionListener = new MainMenuActionListener();
	                loginUI.getLoginFrame().dispose();
				}
			}

			catch(NumberFormatException ex){
				
				System.out.println(ex);
				
				
			}				
		}
	}*/
	
	/*class MainMenuActionListener implements ActionListener{
		
		public MainMenuActionListener() {
			mainMenuUI.addActionListener(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try{
				
				if(e.getSource() == mainMenuUI.getStartGameButton()) {
					System.out.println("mainMenuUI is working");
					
					mainMenuUI.getMainMenuFrame().dispose();
					gameSetupUI.showGameOptions();
					GameSetupActionListener gameSetupActionListener = new GameSetupActionListener();
				}
				
				else if(e.getSource() == mainMenuUI.getHelpScreenButton()){
					System.out.println("Helpbutton is working");
					GameSetupUI.showHelpScreen();
	                
				}
			}

			catch(NumberFormatException ex){
				
				System.out.println(ex);
				
				
			}				
		}
	}*/
	
	/*class GameSetupActionListener implements ActionListener{
		
		public GameSetupActionListener() {
			gameSetupUI.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			
			try{
				
				if(e.getSource() == GameSetupUI.getNextButton()) {
					ArrayList<Integer> gameOptions = GameSetupUI.getGameOptions();
					gameState = new GameState(gameOptions.get(0), gameOptions.get(1), gameOptions.get(2), new ArrayList<String>());
					userNameUI.showLogin(gameOptions);
					UserNameActionListener userNameActionListener = new UserNameActionListener();
					
					System.out.println("oldu");
					gameSetupUI.getOptionsFrame().dispose();
				} else if(e.getSource() == GameSetupUI.getBackButton()) {
					gameSetupUI.getHelpFrame().dispose();
					mainMenuUI.showMainMenu();
				}
				
			
			}

			catch(NumberFormatException ex){
				
				System.out.println(ex);
				
				
			}
			
		}
		
	}*/
	
	/*class UserNameActionListener implements ActionListener{
		
		public UserNameActionListener() {
			userNameUI.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			
			try{
				
				if(e.getSource() == userNameUI.getContinueButton()) {
					System.out.println("continue Butoon");
					for (JTextField nicknameField : userNameUI.getNicknameFields()) {
						String nickname = nicknameField.getText();
						nickNameList.add(nickname);					
                    }
					
					
					
					boardUI.showBoard(GameState.getNumContinents(), GameState.getNicknameList(), TerritoryController.getInstance().createTerritoryMatrix());
					boardUI.addListeners(new BoardActionListener(), new BoardMouseListener());
					ArrayList<String> nickNames = userNameUI.getNicknameList();
					System.out.println("Nicknames: "+ nickNameList);
					PlayerController.getInstance().createPlayers(nickNameList);
					userNameUI.getLoginFrame().dispose();
				} 
				
			
			}

			catch(NumberFormatException ex){
				
				System.out.println(ex);
				
				
			}
			
		}
		
	}*/

	

	
	
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

	public void attack(Territory fromTerritory, Territory toTerritory) {
		//if(Validator.validateAttack(fromTerritory, toTerritory)) {
			//PlayerController.getInstance().getCurrentPlayer().attack();
			//System.out.println(fromTerritory.getName() + "   " + toTerritory.getName());
	//	}
		PlayerController.getInstance().getCurrentPlayer().attack(fromTerritory, toTerritory);
		System.out.println(fromTerritory.getName() + "   " + toTerritory.getName());
	}
	
	public void fortify(Territory fromTerritory, Territory toTerritory) {
		if(true) {
			PlayerController.getInstance().getCurrentPlayer().fortify(fromTerritory, toTerritory);
			System.out.println(fromTerritory.getName() + "   " + toTerritory.getName());
		}
	}
		
	public void recruit(Territory toTerritory, Player currentPlayer) {
		if(Validator.canPlaceArmies(toTerritory, currentPlayer)) {
			currentPlayer.placeArmies(toTerritory);//should be modified
			System.out.println("Territory Name: "+ toTerritory.getName());
		}
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
	
}
