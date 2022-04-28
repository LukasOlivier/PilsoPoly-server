package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tile;
import be.howest.ti.monopoly.logic.implementation.GameState;

import java.util.List;

public interface IService {
    String getVersion();
    List<String > getCommunityCards();
    List<Tile> getTiles();
    Game getDummyGame();
}
