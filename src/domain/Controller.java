package domain;

import ui.BoardUI;
import ui.GameSetupUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import ui.BoardUI.Mode;

import javax.swing.JPanel;

public class Controller {
	private BoardUI boardUI;
	private GameState gameState;
	private GameSetupUI gameSetupUI;
	private PlayerController playerController;
	private Validator validator;
	private Territory fromTerritory;
	private Territory toTerritory;
	
	public Controller(BoardUI boardUI, GameState gameState, GameSetupUI gameSetupUI, PlayerController playerController, Validator validator) {
		this.boardUI = boardUI;
		this.gameState = gameState;
		this.gameSetupUI = gameSetupUI;
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
									currentPlayer.fortify();
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

