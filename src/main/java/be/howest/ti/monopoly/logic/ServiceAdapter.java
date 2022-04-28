package be.howest.ti.monopoly.logic;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tile;
import be.howest.ti.monopoly.logic.implementation.GameState;

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

    public List<Tile> getTiles() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game addGame(Game game) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game getDummyGame() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, Game> getAllGames() {
        throw new UnsupportedOperationException();
    }
}
