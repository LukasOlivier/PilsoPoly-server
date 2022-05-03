package be.howest.ti.monopoly.logic;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tile;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Map;

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
}
