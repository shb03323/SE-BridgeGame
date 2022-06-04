package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputPanel extends JPanel {
    private JLabel remark = new JLabel("Player turn");
    public JButton rollButton = new JButton("Rove");
    public JButton stayButton = new JButton("Stay");

    public InputPanel() {
        this.setLayout(new GridLayout(1, 4));
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
