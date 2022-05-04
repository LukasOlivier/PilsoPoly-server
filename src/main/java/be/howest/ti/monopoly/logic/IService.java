package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tile;
import be.howest.ti.monopoly.web.Request;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface IService {
    String getVersion();
    List<String > getCommunityCards();
    List<Tile> getTiles();
    void addGame(Game game);

    // GAME
    Game getGameById(String id);
    void joinGame(String gameId, String playerName, String icon);
    Game getGame();
    Game getDummyGame();
    List<JsonObject> getAllGames();
    int getGameMapSize();
    List<String> getChanceCards();

    // TILES
    Tile getTile(int position);
    Tile getTile(String tileName);


    // AUCTION
    void startPlayerAuction(Request request);
    void placeBidOnPlayerAuction(Request request);
}
