/**
 * Simple UI test in swing, ActionListeners not completed.
 */
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame {

    public MyFrame() {
        super();
        this.setSize(500, 100);
        this.setResizable(false);
        this.setTitle("TextEcho");
        setupUI();
    }

    private void setupUI() {
        JPanel onePanel;
        onePanel = new JPanel();
        onePanel.setLayout(new FlowLayout());
        this.add(onePanel);
        displayLabel = new JLabel();
        displayLabel.setText("You typed: ");
        onePanel.add(displayLabel);
        inputField = new JTextField();
        inputField.setColumns(10);
        onePanel.add(inputField);
        echoButton = new JButton();
        echoButton.setText("Echo text");
        onePanel.add(echoButton);
        exitButton = new JButton();
        exitButton.setText("Exit the program");
        onePanel.add(exitButton);
    }

    public static void main(String [] args) {
        MyFrame test = new MyFrame();
        test.setVisible(true);
    }

    private static JLabel displayLabel;

    private static JTextField inputField;

    private static JButton echoButton;

    private class EchoButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        }
    }

    private static JButton exitButton;

    private class ExitButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        }
    }
}
