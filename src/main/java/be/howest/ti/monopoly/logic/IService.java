package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tiles.Tile;
import be.howest.ti.monopoly.web.Request;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Map;

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
    void clearGameList();

    Map<String, Game> getAllGames();
    List<JsonObject> mapToList(Map<String, Game> mapOfGames);

    int getGameMapSize();
    List<String> getChanceCards();

    Map<String, Game> filterGamesByNumberOfPlayers(int aInt, Map<String, Game> mapToFilter);
    Map<String, Game> filterGamesByPrefix(String aString, Map<String, Game> mapToFilter);
    Map<String, Game> filterGamesByStarted(boolean aBoolean, Map<String, Game> mapToFilter);
    // TILES
    Tile getTile(int position);

    List<JsonObject> filterGamesBy(String isStarted, String numberPlayers, String prefix);

    Tile getTile(String tileName);

    void buyProperty(String gameId, String playerName, String propertyName);


    // AUCTION
    void startPlayerAuction(String playerName, String gameId, String propertyName);
    void placeBidOnPlayerAuction(Request request);

    Auction getPlayerAuctions(String gameId);

    void fine(String playerName, String gameId);

    void setBankrupt(String playerName,String gameId);

    Map<String, Game> getGames();

    void free(String playerName, String gameId);

    Game rollDice(String playerName, String gameId);

    void useEstimateTax(String playerName, String gameId);

    void useComputeTax(String playerName, String gameId);
}
