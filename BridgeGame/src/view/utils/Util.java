package view.utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public interface Util {
    public static void changePanelWithBorder(JPanel panel) {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        panel.setBorder(blackline);
    }

    public static void setTextSize(JLabel label, int size) {
        Font font = new Font("Arial", Font.BOLD, size);
        label.setFont(font);
    }

    public static ImageIcon setIconSize(ImageIcon icon, int size) {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        return icon;
    }
}
