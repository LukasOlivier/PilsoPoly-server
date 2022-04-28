package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.IService;
import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Game;
<<<<<<< HEAD
import be.howest.ti.monopoly.logic.implementation.GameState;
=======
>>>>>>> fadec3caae81a4d336ca517d298b9f8e2db66db9
import be.howest.ti.monopoly.logic.implementation.Tile;

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
    public Game addGame(Game game) {
        return delegate.addGame(game);
    }

    @Override
    public Game getDummyGame() {
        return delegate.getDummyGame();
    }

    @Override
    public Map<String, Game> getAllGames() {
        return delegate.getAllGames();
    }
}
