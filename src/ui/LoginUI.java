package ui;
import javax.swing.*;

import domain.GameSetup;
import domain.GameState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginUI {
    public static void showLogin(GameState gameState, GameSetup gameSetup) {
        JFrame loginFrame = new JFrame("Enter Nicknames");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(400, 300);
        loginFrame.setLayout(new BorderLayout());
        List<String> nicknameList = new ArrayList<>();

        JPanel loginPanel = new JPanel(new GridLayout(gameState.getNumPlayers(), 2));
        List<JTextField> nicknameFields = new ArrayList<>();

        for (int i = 1; i <= gameState.getNumPlayers(); i++) {
            loginPanel.add(new JLabel("Player " + i + " nickname:"));
            JTextField nicknameField = new JTextField();
            nicknameFields.add(nicknameField);
            loginPanel.add(nicknameField);
        }

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

                BoardUI.showBoard(gameState.getNumContinents(), nicknameList, gameSetup);

                loginFrame.dispose();
                // Continue with the game setup or game creation, using the entered nicknames
            }
        });

        loginFrame.add(continueButton, BorderLayout.SOUTH);
        loginFrame.setVisible(true);
    }
}