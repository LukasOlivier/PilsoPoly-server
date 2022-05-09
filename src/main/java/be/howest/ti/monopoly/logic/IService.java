package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.Tile;
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
    Map<String , Game>  getAllGames();
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

    int buyProperty(Request request);


    // AUCTION
    void startPlayerAuction(Request request);
    void placeBidOnPlayerAuction(Request request);

    Auction getPlayerAuctions(Request request);

    void fine(Request request);

    void free(Request request);

    void setBankrupt(Request request);

}
