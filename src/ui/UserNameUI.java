package ui;
import javax.swing.*;

import domain.controllers.Controller;
import domain.controllers.TerritoryController;
import domain.game.GameSetup;
import domain.game.GameState;
import domain.territory.Territory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserNameUI {
	private JButton continueButton = new JButton("Continue");
	private JFrame loginFrame;
	private static ArrayList<String> nicknameList = new ArrayList<>();
	private ArrayList<JTextField> nicknameFields = new ArrayList<>();
	
    public void showLogin(ArrayList<Integer> gameOptions) {
    	int numPlayers = gameOptions.get(0);
        int numComputerPlayers = gameOptions.get(1);
        int numContinents = gameOptions.get(2);
    	
        loginFrame = new JFrame("Enter Nicknames");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(400, 300);
        loginFrame.setLayout(new BorderLayout());
        loginFrame.setLocationRelativeTo(null);
        //List<String> nicknameList = new ArrayList<>();

        JPanel loginPanel = new JPanel(new GridLayout(numPlayers, 2));
        

        for (int i = 1; i <= numPlayers; i++) {
            loginPanel.add(new JLabel("Player " + i + " nickname:"));
            JTextField nicknameField = new JTextField();
            nicknameFields.add(nicknameField);
            loginPanel.add(nicknameField);
        }

        //BoardUI.showBoard(numContinents, nicknameList, gameSetup, TerritoryController.getInstance().getAllTerritories());


        loginFrame.add(loginPanel, BorderLayout.CENTER);
        continueButton = new JButton("Continue");
    
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	for (JTextField nicknameField : nicknameFields) {
            		String nickname = nicknameField.getText();
            		nicknameList.add(nickname);
            		
            	}
                loginFrame.dispose();
                LoadingAnimation loadingScreen = new LoadingAnimation();
                loadingScreen.startAnimation();
                WorldMapArea worldMapArea = new WorldMapArea();
                Controller.getInstance().createPlayers(nicknameList);
                continueButton.removeActionListener(this);
            }});

        loginFrame.add(continueButton, BorderLayout.SOUTH);
        loginFrame.setVisible(true);
    }
    
    public void addActionListener(ActionListener actionListener ) {
    	this.continueButton.addActionListener(actionListener);
    }

	public JButton getContinueButton() {
		return continueButton;
	}

	public JFrame getLoginFrame() {
		return loginFrame;
	}

	public static ArrayList<String> getNicknameList() {
		return nicknameList;
	}

	public ArrayList<JTextField> getNicknameFields() {
		return nicknameFields;
	}
    
	
    
}