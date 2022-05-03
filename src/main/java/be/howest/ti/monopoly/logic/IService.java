package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.Tile;

import java.util.List;

public interface IService {
    String getVersion();
    List<String > getCommunityCards();
    List<Tile> getTiles();
    Game getGame();
    Game getDummyGame();
    List<String> getChanceCards();
    Tile getTile(int position);
    Tile getTile(String tileName);
    Game getGameById();
    Player getPlayer(String name);
}
