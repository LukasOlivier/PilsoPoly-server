package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.IService;
import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Tile;

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
<<<<<<< HEAD
    public List<String> getChanceCards() {
        return delegate.getChanceCards();
    }

=======
    public Tile getTile(String tileName) {
        return delegate.getTile(tileName);
    }
>>>>>>> ac2b6408e88feb1ca2176757539c8c748fe55033
}
