package view;

import model.player.PlayerList;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class RankDialog extends JDialog {
    // show rank dialog when game becomes end
    public RankDialog(PlayerList playerList) {
        setLayout(new GridLayout(playerList.getPlayerListSize() + 1, 1));
        setSize(500, 500);
        setLocationRelativeTo(null);

        // save player's info in map
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < playerList.getPlayerListSize(); i++) {
            map.put(playerList.getPlayer(i).getName(), playerList.getPlayer(i).getScore());
        }

        // sorted map by score
        List<String> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> map.get(o2) - map.get(o1));

        // set score label
        for (String key : keySet) {
            this.add(new JLabel(key + "'s Score : " + map.get(key)));
        }

        // add exit button
        JButton btn = new JButton("EXIT");

        // when user click exit button, system will be terminate
        btn.addActionListener(e -> System.exit(0));

        this.add(btn);
        setVisible(true);
    }
}
