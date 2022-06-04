package view;

import model.player.Player;

public interface MapObserver {
    void move(int currentCell, int nextCell, int playerIndex);
}
