package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputPanel extends JPanel implements InputObserver {
    private JLabel remark = new JLabel("Player turn");
    private JLabel diceNumber = new JLabel("Dice Result : 1");
    public JButton moveButton = new JButton("Move");
    public JButton stayButton = new JButton("Stay");

    public InputPanel() {
        this.setLayout(new GridLayout(1, 4));
        this.add(remark);
        this.add(diceNumber);
        this.add(moveButton);
        this.add(stayButton);
    }

    public void setButtonListener(ActionListener listener) {
        moveButton.addActionListener(listener);
        stayButton.addActionListener(listener);
    }

    @Override
    public void setPlayerName(String name) {
        remark.setText(name + "'s turn");
    }

    @Override
    public void setDiceNumber(int number) {
        diceNumber.setText("Dice Result : " + number);
    }
}
