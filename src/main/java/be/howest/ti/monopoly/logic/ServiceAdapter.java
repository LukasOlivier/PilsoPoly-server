package be.howest.ti.monopoly.logic;

import java.util.List;

public class ServiceAdapter implements IService {

    @Override
    public String getVersion() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String > getCommunityCards() {throw new UnsupportedOperationException();}

}
