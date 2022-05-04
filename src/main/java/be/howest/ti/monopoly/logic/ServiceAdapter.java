package be.howest.ti.monopoly.logic;
import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.Tile;
import be.howest.ti.monopoly.web.Request;
import io.vertx.core.json.JsonObject;

import java.util.List;

public class ServiceAdapter implements IService {

    @Override
    public String getVersion() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String > getCommunityCards() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Tile> getTiles() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addGame(Game game) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game getGame() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game getDummyGame() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<JsonObject> getAllGames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getGameMapSize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getChanceCards() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tile getTile(int position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tile getTile(String tileName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game getGameById(String id) {throw new UnsupportedOperationException();}

    @Override
    public int buyProperty(Request request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Auction startPlayerAuction(Request request) {
        throw new UnsupportedOperationException();
    }

}
