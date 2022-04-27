package be.howest.ti.monopoly.logic;
import be.howest.ti.monopoly.logic.implementation.Tile;

import java.util.List;

public class ServiceAdapter implements IService {

    @Override
    public String getVersion() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String > getCommunityCards() {throw new UnsupportedOperationException();}

    public List<Tile> getTiles() {
        throw new UnsupportedOperationException();
    }


}
