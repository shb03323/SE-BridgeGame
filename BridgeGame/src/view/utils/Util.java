package view.utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public interface Util {
    public static void changePanelWithBorder(JPanel panel) {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        panel.setBorder(blackline);
    }

    public static void setBigText(JLabel label) {
        Font font = new Font("Arial", Font.BOLD, 20);
        label.setFont(font);
    }

    public static ImageIcon setIconSize(ImageIcon icon, int size) {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        return icon;
    }
}
