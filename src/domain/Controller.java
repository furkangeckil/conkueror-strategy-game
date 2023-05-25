package domain;


import ui.BoardUI;
import ui.GameSetupUI;
import ui.UIElements;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ui.BoardUI.Mode;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Controller {
	private BoardUI boardUI;
	private GameState gameState;
	private PlayerController playerController;
	private Validator validator;
	private Territory fromTerritory;
	private Territory toTerritory;
	private List<String> nicknameList;
	
	public Controller(BoardUI boardUI, GameState gameState, GameSetupUI gameSetupUI, PlayerController playerController, Validator validator) {
		this.boardUI = boardUI;
		this.gameState = gameState;
		this.playerController = playerController;
		this.validator = validator;
		
		this.boardUI.addListeners(new BoardActionListener(), new BoardMouseListener());
	}



	class BoardMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			Mode currentMode = boardUI.getCurrentMode();
			Player currentPlayer = playerController.getCurrentPlayer();
			JPanel territoryPanel = (JPanel) e.getSource();
			String territoryName = BoardUI.getTerritoryName(territoryPanel);
			Territory territory = gameState.findTerritory(territoryName);
			
			try{
				switch (currentMode) {
					case RECRUITMENT_MODE:	
						if(Validator.canPlaceArmies(territory, currentPlayer)) {
							currentPlayer.placeArmies(territory);//should be modified
						}
						
					case ATTACK_MODE:
						if(fromTerritory == null) {
							if(Validator.isBelongPlayer(territory, currentPlayer)) {
								fromTerritory = territory;
							}	
						}
						else if(toTerritory == null) {
							if(!Validator.isBelongPlayer(territory, currentPlayer)) {
								toTerritory = territory;
							}
						}
						
					case FORTIFY_MODE:
						if(fromTerritory == null) {
							if(Validator.isBelongPlayer(territory, currentPlayer)) {
								fromTerritory = territory;
							}	
						}
						else if(toTerritory == null) {
							if(!Validator.isBelongPlayer(territory, currentPlayer)) {
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
				
				if(UIElements.getInstance().getNextButton().getModel().isPressed()) {
					BoardUI.nextMode();
					refreshSelectedTerritories();
				}
				
				else if(UIElements.getInstance().getEndTurnButton().getModel().isPressed()){
					playerController.assignTurn();
					refreshSelectedTerritories();
				}
				else {
				
					switch (currentMode) {
					case RECRUITMENT_MODE:	
					
					case ATTACK_MODE:
						if (UIElements.getInstance().getAttackButton().getModel().isPressed()) {
							if(fromTerritory != null && toTerritory != null) {
								if(Validator.validateAttack(gameState, fromTerritory, toTerritory)) {
									currentPlayer.attack();
								}
							}
						}								
									
					
					case FORTIFY_MODE:
						if (UIElements.getInstance().getFortifyButton().getModel().isPressed()) {
							if(fromTerritory != null && toTerritory != null) {
								if(Validator.validateFortify(gameState, fromTerritory, toTerritory, 1)) { //1 should be changed
									currentPlayer.fortify(fromTerritory, fromTerritory);
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

	public List<String> showLogin(GameState gameState) {
        JFrame loginFrame = new JFrame("Enter Nicknames");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(400, 300);
        loginFrame.setLayout(new BorderLayout());
        this.nicknameList = new ArrayList<>();

        JPanel loginPanel = new JPanel(new GridLayout(GameState.getNumPlayers(), 2));
        List<JTextField> nicknameFields = new ArrayList<>();

        for (int i = 1; i <= GameState.getNumPlayers(); i++) {
            loginPanel.add(new JLabel("Player " + i + " nickname:"));
            JTextField nicknameField = new JTextField();
            nicknameFields.add(nicknameField);
            loginPanel.add(nicknameField);
        }

        BoardUI.showBoard(GameState.getNumContinents(), nicknameList, TerritoryController.getInstance().getAllTerritories());

        loginFrame.add(loginPanel, BorderLayout.CENTER);

        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("nicknames.txt");

                try (FileWriter writer = new FileWriter(file)) {
                    for (JTextField nicknameField : nicknameFields) {
                        String nickname = nicknameField.getText();
                        nicknameList.add(nickname);
                        writer.write(nickname + "\n");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                TerritoryController territoryController = new TerritoryController();
                Territory[] allTerritories = territoryController.getAllTerritories();
                BoardUI.showBoard(GameState.getNumContinents(), nicknameList,  allTerritories);

                loginFrame.dispose();
                // Continue with the game setup or game creation, using the entered nicknames
            }
        });

        loginFrame.add(continueButton, BorderLayout.SOUTH);
        loginFrame.setVisible(true);

        return nicknameList;
    }

	
	
}

