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
    Game getGame();
    Game getDummyGame();
    List<JsonObject> getAllGames();
    int getGameMapSize();
    List<String> getChanceCards();

    // TILES
    Tile getTile(int position);
    Tile getTile(String tileName);

    Game getGameById(String id);

    // AUCTION
    void startPlayerAuction(Request request);
    void placeBidOnPlayerAuction(Request request);
    Auction getPlayerAuctions(Request request);
}
