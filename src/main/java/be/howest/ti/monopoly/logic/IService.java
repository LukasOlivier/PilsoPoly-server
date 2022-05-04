package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.Tile;
import be.howest.ti.monopoly.web.Request;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface IService {
    String getVersion();
    List<String > getCommunityCards();
    List<Tile> getTiles();
    void addGame(Game game);

    Game getGame();

    Game getDummyGame();
    List<JsonObject> getAllGames();
    int getGameMapSize();
    List<String> getChanceCards();
    Tile getTile(int position);
    Tile getTile(String tileName);
    Game getGameById(String id);
    int buyProperty(Request request);
    Auction startPlayerAuction(Request request);

}
