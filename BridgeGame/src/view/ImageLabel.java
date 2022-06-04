package view;

import view.utils.Util;

import javax.swing.*;

public class ImageLabel extends JLabel {
    public ImageLabel(String tile, int size) {
        String currentWorkingDirectory = System.getProperty("user.dir");
        ImageIcon imageIcon = new ImageIcon(currentWorkingDirectory + "/src/assets/tile/" + tile + ".PNG");
        imageIcon = Util.setIconSize(imageIcon, size);
        this.setIcon(imageIcon);
    }
}
