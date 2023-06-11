package ui;
import javax.swing.*;

import domain.game.GameSetup;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
//    public static void startNewGame() {
//        GameSetup gameSetup = new GameSetup();
//        gameSetup.createAndSetupNewGame();
//        showMainMenu(gameSetup);
//
//    }
//
//    public static void showMainMenu(GameSetup gameSetup) {
//        JFrame mainMenuFrame = new JFrame("CONKUEROR");
//        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainMenuFrame.setSize(800, 600);
//        mainMenuFrame.setLayout(new BorderLayout());
//
//        JLabel titleLabel = new JLabel("CONKUEROR", SwingConstants.CENTER);
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
//        mainMenuFrame.add(titleLabel, BorderLayout.NORTH);
//
//        JButton startGameButton = new JButton("Start Game");
//        startGameButton.setFont(new Font("Arial", Font.BOLD, 28));
//
//        JButton helpScreenButton = new JButton("Help");
//        helpScreenButton.setFont(new Font("Arial", Font.BOLD, 28));
//        startGameButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                mainMenuFrame.dispose();
//                GameSetupUI.showGameOptions(gameSetup);
//            }
//        });
//        helpScreenButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                mainMenuFrame.dispose();
//                GameSetupUI.showHelpScreen(gameSetup);
//            }
//        });
//        mainMenuFrame.add(startGameButton, BorderLayout.CENTER);
//        mainMenuFrame.add(helpScreenButton, BorderLayout.SOUTH);
//
//        mainMenuFrame.setVisible(true);
//    }
	private JButton startGameButton = new JButton("Start Game");
    private JButton helpScreenButton = new JButton("Help");
    private JFrame mainMenuFrame;
    private static JFrame helpFrame;
    
    
    public void startNewGame() {
        this.showMainMenu();

    }

    public void showMainMenu() {
        mainMenuFrame = new JFrame("CONKUEROR");
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setSize(800, 600);
        mainMenuFrame.setLayout(new BorderLayout());
        mainMenuFrame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("CONKUEROR", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        mainMenuFrame.add(titleLabel, BorderLayout.NORTH);

        startGameButton = new JButton("Start Game");
        startGameButton.setFont(new Font("Arial", Font.BOLD, 28));

        helpScreenButton = new JButton("Help");
        helpScreenButton.setFont(new Font("Arial", Font.BOLD, 28));
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                mainMenuFrame.dispose();
                GameSetupUI.showGameOptions();
            }
        });
        helpScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.dispose();
                showHelpScreen();
            }
        });
        mainMenuFrame.add(startGameButton, BorderLayout.CENTER);
        mainMenuFrame.add(helpScreenButton, BorderLayout.SOUTH);

        mainMenuFrame.setVisible(true);
    }
    
    public void showHelpScreen() {
        helpFrame = new JFrame("Help Screen");
        helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        helpFrame.setSize(800, 600);
        helpFrame.setLayout(new BorderLayout());

        JTextArea instructionsArea = new JTextArea();
        instructionsArea.setText("Welcome to the game!\n\n" +
                "Instructions:\n" +
                "    Initial Sharing of Territories and Army Placement\n" +
                "      - Each player receives X amount of infantry (X depends on # of players)\n" +
                "      - All players roll a die, highest result places one infantry piece onto a territory to claim it\n" +
                        "      - All players roll a die, highest result places one infantry piece onto a territory to claim it\n" +
                "Press 'Start' to begin the game.");
        instructionsArea.setEditable(false);

        JPanel helpPanel = new JPanel(new GridLayout(4, 2));
        helpPanel.add(instructionsArea);

        helpFrame.add(helpPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                showMainMenu();
                helpFrame.dispose();
            }
        });
        helpFrame.add(backButton, BorderLayout.SOUTH);

        helpFrame.setVisible(true);
    }
    
    public void addActionListener(ActionListener listener) {
    	this.startGameButton.addActionListener(listener);
    	this.helpScreenButton.addActionListener(listener);
    }

	public JButton getStartGameButton() {
		return startGameButton;
	}

	public JButton getHelpScreenButton() {
		return helpScreenButton;
	}

	public JFrame getMainMenuFrame() {
		return mainMenuFrame;
	}
    
	
    
}
