package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.ServiceAdapter;

import java.util.HashMap;
import java.util.Map;


public class MonopolyService extends ServiceAdapter {


     Map globalList = new HashMap<String, Game>();






    @Override
    public String getVersion() {
        return "0.0.1";
    }
}
