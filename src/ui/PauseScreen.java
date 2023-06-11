package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseScreen extends JDialog {

    private JButton resumeButton;
    JFrame pauseScreen = new JFrame("Pause Screen");

    public PauseScreen() {
        //super(parentFrame, "Paused", true);

        //JFrame pauseScreen = new JFrame("Pause Screen");
        pauseScreen.setUndecorated(true);
        pauseScreen.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pauseScreen.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Game is paused.");
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label, BorderLayout.CENTER);

        resumeButton = new JButton("Resume");
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseScreen.setVisible(false);
            }
        });
        panel.add(resumeButton, BorderLayout.SOUTH);

        pauseScreen.add(panel);
        pauseScreen.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        //this.setSize(4000, 2000);
        pauseScreen.setOpacity(0.5f);
        

        //this.setLocationRelativeTo(parentFrame);
    }

    public void showDialog() {
        
        pauseScreen.setVisible(true);
    }
}
