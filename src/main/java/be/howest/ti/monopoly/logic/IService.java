package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tile;

import java.util.List;
import java.util.Map;

public interface IService {
    String getVersion();
    List<String > getCommunityCards();
    List<Tile> getTiles();
    Game addGame(Game game);
    Game getDummyGame();
    Map<String, Game> getAllGames();
    int getGameMapSize();
}
