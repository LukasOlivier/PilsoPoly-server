package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.IService;
import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tiles.Tile;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Map;


public class TestService implements IService {

    IService delegate = new ServiceAdapter();

    void setDelegate(IService delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getVersion() {
        return delegate.getVersion();
    }

    @Override
    public List<String> getCommunityCards() {
        return delegate.getCommunityCards();
    }

    public List<Tile> getTiles() {
        return delegate.getTiles();
    }

    @Override
    public Tile getTile(int position) {
        return delegate.getTile(position);
    }

    @Override
    public Game getGame() {
        return delegate.getGame();
    }

    @Override
    public void addGame(Game game) {
        delegate.addGame(game);
    }

    @Override
    public Game getDummyGame() {
        return delegate.getDummyGame();
    }

    @Override
    public Map<String, Game> getGames() {
        return delegate.getAllGames();
    }

    @Override
    public Game rollDice(String playerName, String gameId) {
        return delegate.rollDice(playerName, gameId);
    }

    @Override
    public void clearGameList() {
        delegate.clearGameList();
    }

    @Override
    public Map<String, Game> getAllGames() {
        return delegate.getAllGames();
    }

    public List<JsonObject> mapToList(Map<String, Game> mapOfGames) {
        return delegate.mapToList(mapOfGames);
    }

    @Override
    public int getGameMapSize() {
        return delegate.getGameMapSize();
    }

    @Override
    public List<String> getChanceCards() {
        return delegate.getChanceCards();
    }

    @Override
    public Map<String, Game> filterGamesByNumberOfPlayers(int aInt, Map<String, Game> mapToFilter) {
        return null;
    }

    @Override
    public Map<String, Game> filterGamesByPrefix(String aString, Map<String, Game> mapToFilter) {
        return null;
    }

    @Override
    public Map<String, Game> filterGamesByStarted(boolean aBoolean, Map<String, Game> mapToFilter) {
        return null;
    }

    @Override
    public List<JsonObject> filterGamesBy(String isStarted, String numberPlayers, String prefix) {
        return delegate.filterGamesBy(isStarted, numberPlayers, prefix);
    }

    public Tile getTile(String tileName) {
        return delegate.getTile(tileName);
    }

    @Override
    public Game getGameById(String id) {
        return delegate.getGameById(id);
    }

    @Override
    public void startPlayerAuction(String playerName, String gameId, String propertyName) {
        delegate.startPlayerAuction(playerName, gameId, propertyName);
    }

    @Override
    public void placeBidOnPlayerAuction(Request request) {
        delegate.placeBidOnPlayerAuction(request);
    }

    @Override
    public Auction getPlayerAuctions(String gameId) {
        return delegate.getPlayerAuctions(gameId);
    }

    @Override
    public void fine(String playerName, String gameId) {
        delegate.fine(playerName, gameId);
    }

    @Override
    public void free(String playerName, String gameId) {
        delegate.free(playerName, gameId);
    }

    @Override
    public void setBankrupt(String playerName, String gameId) {
        delegate.setBankrupt(playerName, gameId);
    }

    @Override
    public void useComputeTax(String playerName, String gameId) {
        delegate.useComputeTax(playerName, gameId);
    }

    @Override
    public void useEstimateTax(String playerName, String gameId) {
        delegate.useEstimateTax(playerName, gameId);
    }

    @Override
    public void joinGame(String gameId, String playerName, String icon) {
        delegate.joinGame(gameId, playerName, icon);
    }

    @Override
    public void buyProperty(String gameId, String playerName, String propertyName) {
        delegate.buyProperty(gameId,playerName,propertyName);
    }


}

