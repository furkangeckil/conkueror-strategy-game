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

import ui.BoardUI.Mode;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Controller {
	private LoginUI loginUI;
	private MainMenu mainMenuUI = new MainMenu();
	private BoardUI boardUI= new BoardUI();
	private GameState gameState;
	private GameSetupUI gameSetupUI = new GameSetupUI();
	private UserNameUI userNameUI = new UserNameUI();
	private GameSetup gameSetup;
	private PlayerController playerController;
	private Validator validator;
	private Territory fromTerritory;
	private Territory toTerritory;
	
//	public Controller(PlayerController playerController, Validator validator) {
//		//this.boardUI = boardUI;
//		//this.gameState = gameState;
//		//this.gameSetupUI = gameSetupUI;
//		this.playerController = playerController;
//		this.validator = validator;
//		
//		//this.boardUI.addListeners(new BoardActionListener(), new BoardMouseListener());
//		GameSetupUI.addListeners(new GameSetupActionListener());
//	}
	
	public Controller(BoardUI boardUI, GameState gameState, GameSetupUI gameSetupUI, PlayerController playerController, Validator validator,LoginUI loginUI) {
		//this.boardUI = boardUI;
		this.gameState = gameState;
		this.gameSetupUI = gameSetupUI;
		this.playerController = playerController;
		this.validator = validator;
		this.loginUI = loginUI;
		LoginActionListener loginActionListener = new LoginActionListener();
		
	}

	class LoginActionListener implements ActionListener{
		
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
	}
	
	class MainMenuActionListener implements ActionListener{
		
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
	}
	
	class GameSetupActionListener implements ActionListener{
		
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
		
	}
	
class UserNameActionListener implements ActionListener{
		
		public UserNameActionListener() {
			userNameUI.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			
			try{
				
				if(e.getSource() == userNameUI.getContinueButton()) {
					System.out.println("continue Butoon");
					boardUI.showBoard(GameState.getNumContinents(), GameState.getNicknameList(), TerritoryController.getInstance().createTerritoryMatrix());
					
					userNameUI.getLoginFrame().dispose();
				} 
				
			
			}

			catch(NumberFormatException ex){
				
				System.out.println(ex);
				
				
			}
			
		}
		
	}

	class BoardMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			Mode currentMode = boardUI.getCurrentMode();
			Player currentPlayer = playerController.getCurrentPlayer();
			JPanel territoryPanel = (JPanel) e.getSource();
			String territoryName = boardUI.getTerritoryName(territoryPanel);
			Territory territory = gameState.findTerritory(territoryName);
			
			try{
				switch (currentMode) {
					case RECRUITMENT_MODE:	
						if(validator.canPlaceArmies(territory, currentPlayer)) {
							currentPlayer.placeArmies(territory);//should be modified
						}
						
					case ATTACK_MODE:
						if(fromTerritory == null) {
							if(validator.isBelongPlayer(territory, currentPlayer)) {
								fromTerritory = territory;
							}	
						}
						else if(toTerritory == null) {
							if(!validator.isBelongPlayer(territory, currentPlayer)) {
								toTerritory = territory;
							}
						}
						
					case FORTIFY_MODE:
						if(fromTerritory == null) {
							if(validator.isBelongPlayer(territory, currentPlayer)) {
								fromTerritory = territory;
							}	
						}
						else if(toTerritory == null) {
							if(!validator.isBelongPlayer(territory, currentPlayer)) {
								toTerritory = territory;
							}
						}
				}
			}

			catch(NumberFormatException ex){
				
				
			}
			
		}


//		@Override
//		public void mouseClicked(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class BoardActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			Mode currentMode = boardUI.getCurrentMode();
			Player currentPlayer = playerController.getCurrentPlayer();
			
			try{
				
				if(boardUI.getNextButton().getModel().isPressed()) {
					boardUI.nextMode();
					refreshSelectedTerritories();
				}
				
				else if(boardUI.getEndTernButton().getModel().isPressed()){
					playerController.assignTurn();
					refreshSelectedTerritories();
				}
				else {
				
					switch (currentMode) {
					case RECRUITMENT_MODE:	
					
					case ATTACK_MODE:
						if (boardUI.getAttackButton().getModel().isPressed()) {
							if(fromTerritory != null && toTerritory != null) {
								if(validator.validateAttack(gameState, fromTerritory, toTerritory)) {
									currentPlayer.attack();
								}
							}
						}								
									
					
					case FORTIFY_MODE:
						if (boardUI.getFortifyButton().getModel().isPressed()) {
							if(fromTerritory != null && toTerritory != null) {
								if(validator.validateFortify(gameState, fromTerritory, toTerritory, 1)) { //1 should be changed
									currentPlayer.fortify(fromTerritory, toTerritory);
								}
							}
						}
					}
				}
			
			}

			catch(NumberFormatException ex){
				
				System.out.println(ex);
				
				
			}
			
		}
		
	}
	
	
	
	
	
	public void refreshSelectedTerritories() {
		this.fromTerritory=null;
		this.toTerritory=null;
	}
	
	
}

