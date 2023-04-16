package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import domain.GameSetup;

public class MainMenu {
	public static void startNewGame() {
		GameSetup gameSetup = new GameSetup();
		//gameSetup.createAndSetupNewGame();
		showMainMenu(gameSetup);
		
	}

	public static void showMainMenu(GameSetup gameSetup) {
        JFrame mainMenuFrame = new JFrame("CONKUEROR");
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setSize(800, 600);
        mainMenuFrame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("CONKUEROR", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        mainMenuFrame.add(titleLabel, BorderLayout.NORTH);

        JButton startGameButton = new JButton("Start Game");
		startGameButton.setFont(new Font("Arial", Font.BOLD, 28));
		startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.dispose();
				GameSetupUI.showGameOptions(gameSetup);
            }
        });
        mainMenuFrame.add(startGameButton, BorderLayout.CENTER);

        mainMenuFrame.setVisible(true);
    }
}
