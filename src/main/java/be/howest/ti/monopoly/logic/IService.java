package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Tile;
import java.util.List;

public interface IService {
    String getVersion();
    List<String > getCommunityCards();
    List<Tile> getTiles();
    List<String> getChanceCards();
    Tile getTile(int position);
    Tile getTile(String tileName);
}
