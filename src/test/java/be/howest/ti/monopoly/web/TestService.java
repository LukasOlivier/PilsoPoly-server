package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.IService;
import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tile;
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
    public List<String > getCommunityCards() {
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
    public Game getGame() {return delegate.getGame();}

    @Override
    public void addGame(Game game) {
        delegate.addGame(game);
    }

    @Override
    public Game getDummyGame() {
        return delegate.getDummyGame();
    }

    @Override
    public Map<String, Game> getAllGames() {
        return delegate.getAllGames();
    }

    @Override
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
    public void startPlayerAuction(Request request) {
        delegate.startPlayerAuction(request);
    }

    @Override
    public void placeBidOnPlayerAuction(Request request) {
        delegate.placeBidOnPlayerAuction(request);
    }

    @Override
    public Auction getPlayerAuctions(Request request) {
        return delegate.getPlayerAuctions(request);
    }

    @Override
    public void fine(Request request) {

    }

    @Override
    public void free(Request request) {

    }

    @Override
    public void setBankrupt(Request request) {

    }

    @Override
    public void joinGame(String gameId, String playerName, String icon) {
        delegate.joinGame(gameId, playerName, icon);
    }
}

