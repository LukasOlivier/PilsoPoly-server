package be.howest.ti.monopoly.logic;
import be.howest.ti.monopoly.logic.implementation.Tile;
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

    public List<Tile> getTiles() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getChanceCards() {

    public Tile getTile(int position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tile getTile(String tileName) {
        throw new UnsupportedOperationException();
    }
}
