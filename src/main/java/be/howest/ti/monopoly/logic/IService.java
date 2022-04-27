package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Tile;
import be.howest.ti.monopoly.logic.implementation.DummyGame;

import java.util.List;

public interface IService {
    String getVersion();
    List<String > getCommunityCards();
    List<Tile> getTiles();
    DummyGame getDummyGame();
}
