package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.IService;
import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Tile;

import java.util.List;

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
<<<<<<< HEAD
    public List<String > getCommunityCards() {return delegate.getCommunityCards();}
=======
    public List<Tile> getTiles() {
        return delegate.getTiles();
    }
>>>>>>> d8c07d3bd6baeb11161d27a0b5cf54f68f7de4b1
}
