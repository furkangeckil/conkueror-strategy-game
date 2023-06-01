package ui;

import javax.swing.*;

import domain.GameEnum;
import domain.GameSetup;
import domain.PlayerController;
import domain.Territory;
import ui.UserNameUI;

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
	private Mode currentMode =  Mode.RECRUITMENT_MODE;
	private JButton attackButton = new JButton("Attack");
	private JButton fortifyButton = new JButton("Fortify");
	private JButton endTurnButton = new JButton("End Turn");
	private JButton placeArmiesButton = new JButton("Recruit");
	private JButton nextModeButton = new JButton("Next Mode");
	private JButton exitButton = new JButton("Exit");
	
    private static final Color[] PLAYER_COLORS = {
            Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN
            // Actually we do not need to color continents, we need to assign colors to players so that they can place armies
            // and make territories their colors.
    };

//    private static String[][] createTerritoryMatrix(Territory[] territories) {
//        String[][] matrix = new String[7][12];
//        matrix[0][1] = territories[0].getName();
//        matrix[0][2] = territories[1].getName(); 
//        matrix[0][3] = territories[2].getName();
//        matrix[0][4] = territories[3].getName();
//        matrix[0][5] = territories[4].getName();
//        matrix[1][2] = territories[5].getName();
//        matrix[1][3] = territories[6].getName();
//        matrix[1][4] = territories[7].getName();
//        matrix[1][5] = territories[8].getName();
//        matrix[1][6] = territories[9].getName();
//        matrix[2][3] = territories[10].getName();
//        matrix[2][4] = territories[11].getName();
//        matrix[2][5] = territories[12].getName();
//        matrix[2][6] = territories[13].getName();
//        matrix[2][7] = territories[14].getName();
//        matrix[3][4] = territories[15].getName();
//        matrix[3][5] = territories[16].getName();
//        matrix[3][6] = territories[17].getName();
//        matrix[3][7] = territories[18].getName();
//        matrix[3][8] = territories[19].getName();
//        matrix[4][5] = territories[20].getName();
//        matrix[4][6] = territories[21].getName();
//        matrix[4][7] = territories[22].getName();
//        matrix[4][8] = territories[23].getName();
//        matrix[4][9] = territories[24].getName();
//        matrix[5][6] = territories[25].getName();
//        matrix[5][7] = territories[26].getName();
//        matrix[5][8] = territories[27].getName();
//        matrix[5][9] = territories[28].getName();
//        matrix[5][10] = territories[29].getName();
//        return matrix;
//    }
    
    private static MouseAdapter territoryClickListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            //    if (gameSetup.getGame().getGameEnum() == GameEnum.RUNNING) {
                    JPanel territoryPanel = (JPanel) e.getSource();
                    //territoryPanel.setBackground(PLAYER_COLORS[2]);
             //   }
            }
        };
    }

    private static List<String> nicknameList;
    

    public void showBoard(int numContinents, List<String> nicknameList, String[][] territoryMatrix) {
        JFrame boardFrame = new JFrame("World Map");
        boardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        boardFrame.setSize(1000, 600);

        JPanel boardPanel = new JPanel(null); // Use a custom layout
        boardFrame.add(boardPanel, BorderLayout.CENTER);

        //String[][] territoryMatrix = createTerritoryMatrix(territories);
        MouseAdapter territoryClickListener = territoryClickListener();
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
                    territoryPanel.addMouseListener(territoryClickListener);
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
        exitButton.addActionListener(e -> System.exit(0));
        

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(pauseResumeButton, BorderLayout.WEST);
        bottomPanel.add(exitButton, BorderLayout.EAST);
        boardFrame.add(bottomPanel, BorderLayout.SOUTH);

        boardFrame.setVisible(true);
    }
    
    public static String getTerritoryName(JPanel panel) {
    	return reverseTerritoryMap.get(panel);
    }
    
    public Mode getCurrentMode() {
    	return this.currentMode;
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
    
    public JButton getEndTernButton() {
    	return this.endTurnButton;
    }
    
    public static enum Mode {
		RECRUITMENT_MODE, ATTACK_MODE, FORTIFY_MODE
	}
    
    public void nextMode() {
    	switch (this.currentMode) {
		case RECRUITMENT_MODE:
			this.currentMode=Mode.ATTACK_MODE;		
		case ATTACK_MODE:
			this.currentMode=Mode.FORTIFY_MODE;
		case FORTIFY_MODE:
			this.currentMode=Mode.RECRUITMENT_MODE;
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
    
    public static void updateTerritory (int playerIndex, String territoryName, int armyNum) {
    	JPanel territoryPanel = territoryMap.get(territoryName);
    	territoryPanel.setBackground(PLAYER_COLORS[playerIndex]);
    	((JLabel) territoryPanel.getComponents()[0]).setText(Integer.toString(armyNum));
    	
    }
}