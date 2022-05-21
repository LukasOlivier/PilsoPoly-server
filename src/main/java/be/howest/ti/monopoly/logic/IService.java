package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
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
    Tile getTile(int position);

    List<JsonObject> filterGamesBy(String isStarted, String numberPlayers, String prefix);

    Tile getTile(String tileName);

    void buyProperty(String gameId, String playerName, String propertyName);

    // AUCTION
    void dontBuyProperty(String gameId, String playerName, String propertyName);

    void placeBidOnBankAuction(String gameId, String bidder, int amount);

    Auction getPlayerAuctions(String gameId);

    // RENT
    void collectDebt(String gameName,String playerName, String debtPlayerName,String tileName);

    void setBankrupt(String playerName,String gameId);

    Map<String, Game> getGames();

    Game rollDice(String playerName, String gameId);

    void useEstimateTax(String playerName, String gameId);

    void useComputeTax(String playerName, String gameId);

    void getOutOfJailFine(String gameId, String playerName);

    void getOutOfJailFree(String gameId, String playerName);

    void buyHouse(String gameId, String playerName, String propertyName);

    void sellHouse(String gameId, String playerName, String propertyName);

    void takeMortgage(String gameId, String playerName, String propertyName);

    void buyHotel(String gameId, String playerName, String propertyName);

    void sellHotel(String gameId, String playerName, String propertyName);

    void settleMortgage(String gameId, String playerName, String propertyName);
}
