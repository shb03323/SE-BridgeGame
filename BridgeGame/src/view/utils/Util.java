package view.utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public interface Util {
    public static void changePanelWithBorder(JPanel panel) {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        panel.setBorder(blackline);
    }
}
