package ui;
import javax.swing.*;

import domain.GameSetup;
import domain.GameState;
import domain.Territory;
import domain.TerritoryController;

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
	private ArrayList<String> nicknameList = new ArrayList<>();
	private ArrayList<JTextField> nicknameFields = new ArrayList<>();
	
    public List<String> showLogin(ArrayList<Integer> gameOptions) {
    	int numPlayers = gameOptions.get(0);
        int numComputerPlayers = gameOptions.get(1);
        int numContinents = gameOptions.get(2);
    	
        loginFrame = new JFrame("Enter Nicknames");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(400, 300);
        loginFrame.setLayout(new BorderLayout());
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

        //JButton continueButton = new JButton("Continue");
//        continueButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                File file = new File("nicknames.txt");
//
//                try (FileWriter writer = new FileWriter(file)) {
//                    for (JTextField nicknameField : nicknameFields) {
//                        String nickname = nicknameField.getText();
//                        nicknameList.add(nickname);
//                        writer.write(nickname + "\n");
//                    }
//                } catch (IOException ioException) {
//                    ioException.printStackTrace();
//                }
//                TerritoryController territoryController = new TerritoryController();
//                Territory[] allTerritories = territoryController.getAllTerritories();
//                //BoardUI.showBoard(gameState.getNumContinents(), nicknameList, gameSetup, allTerritories);
//
//                loginFrame.dispose();
//                // Continue with the game setup or game creation, using the entered nicknames
//            }
//        });

        loginFrame.add(continueButton, BorderLayout.SOUTH);
        loginFrame.setVisible(true);
        
        return nicknameList;
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

	public ArrayList<String> getNicknameList() {
		return nicknameList;
	}

	public ArrayList<JTextField> getNicknameFields() {
		return nicknameFields;
	}
    
	
    
}