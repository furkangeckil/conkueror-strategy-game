package ui;

import domain.controllers.Controller;
import domain.territory.Territory;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScreen {
    public boolean checkGameOver(Territory[] territories) {
        int numTerritories = territories.length;
        int count = 0;

        for(int i = 0; i < numTerritories; i++) {
            if(territories[i].getOwner() == Controller.getInstance().getCurrentPlayer()) {
                count++;
            }
        }

        if(count == 42) {
            return true;
        }
        else {
            return false;
        }
    }

    public void showScreen() {
        Territory[] territories = Controller.getInstance().getAllTerritories();

        if(checkGameOver(territories)) {
            JFrame frame = new JFrame("Game Over");
            JLabel label = new JLabel("Player " + Controller.getInstance().getCurrentPlayer().getName() + " has won the game!");
            label.setFont(new Font("Arial", Font.BOLD, 30));
            label.setForeground(Color.RED);
            frame.add(label, BorderLayout.CENTER);
            JPanel panel = new JPanel();
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            panel.add(exitButton);
            frame.add(panel, BorderLayout.SOUTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
            frame.setVisible(true);
        }
    }
}
