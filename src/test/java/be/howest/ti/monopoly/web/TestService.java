package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.IService;
import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tile;
import io.vertx.core.json.JsonObject;

import java.util.List;


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
    public List<JsonObject> getAllGames() {
        return delegate.getAllGames();
    }

    @Override
    public int getGameMapSize() {
        return delegate.getGameMapSize();
    }

    public List<String> getChanceCards() {
        return delegate.getChanceCards();
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
    public void joinGame(String gameId, String playerName, String icon) {

    }
}

