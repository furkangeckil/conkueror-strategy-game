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
	
	public Controller(BoardUI boardUI, GameState gameState, GameSetupUI gameSetupUI, PlayerController playerController, Validator validator) {
		this.boardUI = boardUI;
		this.gameState = gameState;
		this.gameSetupUI = gameSetupUI;
		this.playerController = playerController;
		this.validator = validator;
	}



	class BoardListener implements MouseListener{
		private Territory attackerTer;
		private Territory defenderTer;

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
							currentPlayer.placeArmies(territory,1);//should be modified
						}
						
					case ATTACK_MODE:
						if(attackerTer == null) {
							if(validator.isBelongAttacker) {
								attackerTer = territory;
							}	
						}
						else if(defenderTer == null) {
							if(validator.validateAttack()) {
								currentPlayer.attack();
							}
						}
						
					case FORTIFY_MODE:
						
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
	
	
}

