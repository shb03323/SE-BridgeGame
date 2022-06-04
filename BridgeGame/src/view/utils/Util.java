package view.utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public interface Util {
    // change panel with including border
    static void changePanelWithBorder(JPanel panel) {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        panel.setBorder(blackline);
    }

    // set the text to the size desired by the user
    static void setTextSize(JLabel label, int size) {
        Font font = new Font("Arial", Font.BOLD, size);
        label.setFont(font);
    }

    // set the image icon to the size desired by the user
    static ImageIcon setIconSize(ImageIcon icon, int size) {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        return icon;
    }
}
