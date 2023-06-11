package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadingAnimation extends JDialog {
    private JProgressBar progressBar;
    private int progress;

    public LoadingAnimation() {
        setTitle("Loading...");
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(300, 100);
        setLocationRelativeTo(null);

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(250, 30));
        getContentPane().add(progressBar, BorderLayout.CENTER);

        Timer timer = new Timer(80, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (progress < 100) {
                    progress++;
                    progressBar.setValue(progress);
                } else {
                    ((Timer) e.getSource()).stop();
                    dispose();
                }
            }
        });
        timer.start();
    }
        
    public void startAnimation() {
        setVisible(true);
    }
}