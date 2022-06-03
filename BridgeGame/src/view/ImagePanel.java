package view;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private Image img;
    private int size;

    public ImagePanel(Image img, int size) {
        this.img = img;
        this.size = size;
        setSize(size, size);
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 5, 5, size - 10, size - 10, null);
    }
}
