package ui;

import javax.swing.*;

import domain.Controller;
import domain.GameEnum;
import domain.GameSetup;
import domain.Player;
import domain.PlayerController;
import domain.Territory;
import domain.TerritoryController;
import ui.UserNameUI;
import ui.BoardUI.Mode;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseListener;
import java.util.HashMap;

//import java.util.ArrayList;
import java.util.List;
//import java.util.Random;

public class BoardUI {
	private static HashMap<String, JPanel> territoryMap = new HashMap<>(); //added 
	private static HashMap<JPanel, String> reverseTerritoryMap = new HashMap<>();
	private String mode = "recruit";
	private JButton attackButton = new JButton("Attack");
	private JButton fortifyButton = new JButton("Fortify");
	private JButton endTurnButton = new JButton("End Turn");
	private JButton placeArmiesButton = new JButton("Recruit");
	private JButton nextModeButton = new JButton("Next Mode");
	private JButton exitButton = new JButton("Exit");
	private static List<String> nicknameList;
	private Territory fromTerritory = null;
	private Territory toTerritory = null;

	
    private static final Color[] PLAYER_COLORS = {
            Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN
            // Actually we do not need to color continents, we need to assign colors to players so that they can place armies
            // and make territories their colors.
    };
    
//    private static MouseAdapter territoryClickListener() {
//        return new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//            //    if (gameSetup.getGame().getGameEnum() == GameEnum.RUNNING) {
//                    JPanel territoryPanel = (JPanel) e.getSource();
//                    //territoryPanel.setBackground(PLAYER_COLORS[2]);
//             //   }
//            }
//        };
//    }

    public void showBoard(int numContinents, List<String> nicknameList, String[][] territoryMatrix) {
        JFrame boardFrame = new JFrame("World Map");
        boardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        boardFrame.setSize(1000, 600);

        JPanel boardPanel = new JPanel(null); // Use a custom layout
        boardFrame.add(boardPanel, BorderLayout.CENTER);
        
        //MouseAdapter territoryClickListener = territoryClickListener();
        int territoryWidth = 40;
        int territoryHeight = 40;

        for (int row = 0; row < territoryMatrix.length; row++) {
            for (int col = 0; col < territoryMatrix[row].length; col++) {
                String territoryName = territoryMatrix[row][col];

                if (territoryName != null) {
                    JPanel territoryPanel = new JPanel();
                    territoryPanel.setBackground(Color.GRAY);
                    territoryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    int territoryX = 40 + col * territoryWidth;
                    int territoryY = 50 + row * territoryHeight;

                    territoryPanel.setBounds(territoryX, territoryY, territoryWidth, territoryHeight);
                    //territoryPanel.addMouseListener(territoryClickListener);
                    int armyCount = 1;
                    JLabel armyCountLabel = new JLabel(Integer.toString(armyCount));
                    territoryPanel.add(armyCountLabel, new GridBagConstraints());
                    territoryMap.put(territoryName, territoryPanel);//added
                    reverseTerritoryMap.put(territoryPanel, territoryName);
                    boardPanel.add(territoryPanel);
                }
            }
        }

        JPanel userNamesPanel = new JPanel();
        userNamesPanel.setLayout(new BoxLayout(userNamesPanel, BoxLayout.X_AXIS));

        for (int i = 0; i < nicknameList.size(); i++) {
            JLabel userNameLabel = new JLabel(nicknameList.get(i));
            userNameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            userNameLabel.setOpaque(true);
            userNameLabel.setBackground(PLAYER_COLORS[i % PLAYER_COLORS.length]);
            userNamesPanel.add(userNameLabel);
            userNamesPanel.add(Box.createHorizontalStrut(10)); // Add spacing between labels
        }

        boardFrame.add(userNamesPanel, BorderLayout.NORTH);

        JButton pauseResumeButton = new JButton("Pause");
        pauseResumeButton.addActionListener(e -> {
//        if (gameSetup.getGame().getGameEnum() == GameEnum.RUNNING) {
//            gameSetup.getGame().pauseGame();
//            pauseResumeButton.setText("Resume");
//            System.out.println("The Game is Paused");
//        } else if (gameSetup.getGame().getGameEnum() == GameEnum.PAUSED) {
//            gameSetup.getGame().resumeGame();
//            pauseResumeButton.setText("Pause");
//            System.out.println("The Game is Resumed");
//        }

        
    });
        JPanel actionPanel =  new JPanel();
        
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));

        actionPanel.add(attackButton);

        actionPanel.add(fortifyButton);

        actionPanel.add(placeArmiesButton);
        
        actionPanel.add(nextModeButton);
        
        actionPanel.add(endTurnButton);

        // Set alignment for all buttons
        attackButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        fortifyButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        placeArmiesButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        nextModeButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        endTurnButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        // Add the actionPanel to the boardFrame
        boardFrame.add(actionPanel, BorderLayout.EAST);

//        this.attackButton = new JButton("Attack");
//        actionPanel.add(attackButton, BorderLayout.NORTH);
//
//        this.fortifyButton = new JButton("Fortify");
//        actionPanel.add(fortifyButton, BorderLayout.CENTER);
//
//        this.endTurnButton = new JButton("End Turn");
//        actionPanel.add(endTurnButton, BorderLayout.SOUTH);
//        
//        boardFrame.add(actionPanel, BorderLayout.EAST);

        

//        JButton exitButton = new JButton("Exit");
//        if (exitButton.getModel().isPressed()){
//            gameSetup.getGame().endGame();
//        }
//        exitButton.addActionListener(e -> System.exit(0));
        

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(pauseResumeButton, BorderLayout.WEST);
        bottomPanel.add(exitButton, BorderLayout.EAST);
        boardFrame.add(bottomPanel, BorderLayout.SOUTH);

        boardFrame.setVisible(true);
        addListeners(new BoardActionListener(), new BoardMouseListener());
    }
    
    class BoardMouseListener implements MouseListener{
		

		@Override
		public void mouseClicked(MouseEvent e) {
			Player currentPlayer = Controller.getInstance().getCurrentPlayer();
			JPanel territoryPanel = (JPanel) e.getSource();
			String territoryName = getTerritoryName(territoryPanel);
			Territory territory = Controller.getInstance().findTerritory(territoryName);
			
			
			try{
				if(mode == "recruit") {
					Controller.getInstance().recruit(territory,currentPlayer);
				}
				else if(mode == "attack") {
					if(fromTerritory == null) {
						fromTerritory = territory;
						System.out.println(fromTerritory.getName());
					}
					else if(toTerritory == null) {
						toTerritory = territory;
						System.out.println(toTerritory.getName());
					}
				}
				else if(mode == "fortify") {
					if(fromTerritory == null) {
						fromTerritory = territory;
					}
					else if(toTerritory == null) {
						toTerritory = territory;
					}
				}
				
//				switch (mode) {
//					case "recruit":
//						Controller.getInstance().recruit(territory,currentPlayer);
//						
//					case "attack":
//						if(fromTerritory == null) {
//							fromTerritory = territory;
//							System.out.println(fromTerritory.getName());
//						}
//						else if(toTerritory == null) {
//							toTerritory = territory;
//							System.out.println(toTerritory.getName());
//						}
//						
//					case "fortify":
//						if(fromTerritory == null) {
//							fromTerritory = territory;
//						}
//						else if(toTerritory == null) {
//							toTerritory = territory;
//						}
//				}
			}
			catch(NumberFormatException ex){
				
			}				
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
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
			
			Player currentPlayer = PlayerController.getInstance().getCurrentPlayer();
			
			try{
				
				if(getNextModeButton() == e.getSource()) {
					nextMode();
					System.out.println(mode);
					refreshSelectedTerritories();
				}
				
				else if(getEndTurnButton()==e.getSource()){
					Controller.getInstance().endTurn();
					refreshSelectedTerritories();
					System.out.println("end turn button, currentplayer: "+Controller.getInstance().getCurrentPlayer());
				}
				else {
				
					if(mode == "attack") {
						if (getAttackButton()==e.getSource()) {
							Controller.getInstance().attack(fromTerritory, toTerritory);
						}								
					}
					else if(mode == "fortify"){
						if (getFortifyButton()==e.getSource()) {
							Controller.getInstance().fortify(fromTerritory, toTerritory);
						}
					}
					
					
//					switch (mode) {
//					case "recruit":	
//						
//					case "attack":
//						if (getAttackButton()==e.getSource()) {
//							Controller.getInstance().attack(beep, boop);
//						}								
//									
//					
//					case "fortify":
//						if (getFortifyButton()==e.getSource()) {
//							Controller.getInstance().fortify(fromTerritory, toTerritory);
//						}
//					}
				}
			
			}

			catch(NumberFormatException ex){
				
				System.out.println(ex);
				
				
			}
			
		}
		
	}
    
    
    public static String getTerritoryName(JPanel panel) {
    	return reverseTerritoryMap.get(panel);
    }
    
    public String getCurrentMode() {
    	return mode;
    }
    
    public JButton getAttackButton() {
    	return this.attackButton;
    }
    
    public JButton getFortifyButton() {
    	return this.fortifyButton;
    }
    
    public JButton getNextModeButton() {
    	return this.nextModeButton;
    }
    
    public JButton getEndTurnButton() {
    	return this.endTurnButton;
    }
    
    public static enum Mode {
		RECRUITMENT_MODE, ATTACK_MODE, FORTIFY_MODE
	}
    
    public void nextMode() {
//    	switch (currentMode) {
//		case RECRUITMENT_MODE:
//			currentMode=Mode.ATTACK_MODE;		
//		case ATTACK_MODE:
//			currentMode=Mode.FORTIFY_MODE;
//		case FORTIFY_MODE:
//			currentMode=Mode.RECRUITMENT_MODE;
//    	}
    	if(mode == "recruit") {
    		mode = "attack";	
    	}
    	else if(mode == "attack") {
    		mode = "fortify";
    	}
    	else if(mode == "fortify") {
    		mode = "recruit";
    	}
    }
    
    public void addListeners(ActionListener actionListener, MouseListener mouseListener ) {
    	this.attackButton.addActionListener(actionListener);
    	this.fortifyButton.addActionListener(actionListener);
    	this.placeArmiesButton.addActionListener(actionListener);
    	this.nextModeButton.addActionListener(actionListener);
    	this.endTurnButton.addActionListener(actionListener);
    	
    	for (JPanel territoryPanel : territoryMap.values()) {
    		territoryPanel.addMouseListener(mouseListener);
    	}
    }
    
	public void refreshSelectedTerritories() {
		this.fromTerritory=null;
		this.toTerritory=null;
	}
    
    public static void updateTerritory (int playerIndex, String territoryName, int armyNum) {
    	JPanel territoryPanel = territoryMap.get(territoryName);
    	territoryPanel.setBackground(PLAYER_COLORS[playerIndex]);
    	((JLabel) territoryPanel.getComponents()[0]).setText(Integer.toString(armyNum));
    	
    }
}