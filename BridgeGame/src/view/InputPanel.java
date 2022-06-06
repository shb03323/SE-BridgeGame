package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputPanel extends JPanel {
    public JLabel remark = new JLabel("Player turn");
    public JButton rollButton = new JButton("Roll");
    public JButton stayButton = new JButton("Stay");

    // input panel for player take turn
    public InputPanel() {
        this.setBounds(800, 800, 200, 100);
        this.setLayout(new GridLayout(3, 1));
        remark.setHorizontalAlignment(JLabel.CENTER);
        this.add(remark);
        this.add(rollButton);
        this.add(stayButton);
    }

    public void setButtonListener(ActionListener listener) {
        rollButton.addActionListener(listener);
        stayButton.addActionListener(listener);
    }

    public void setPlayerName(String name) {
        remark.setText(name + "'s turn");
    }
}
