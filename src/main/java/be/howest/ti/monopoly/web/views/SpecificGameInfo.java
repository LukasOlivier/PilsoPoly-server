package be.howest.ti.monopoly.web.views;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;

import java.util.ArrayList;
import java.util.List;

public class SpecificGameInfo {
    private final Game game ;

    public SpecificGameInfo(Game game) {
        this.game = game;
    }

    public List<String> getPlayers() {
        List<String> listOfPlayerNames = new ArrayList<>();
        for (Player player : game.getPlayers()) {
            listOfPlayerNames.add(player.getName());
        }
        return listOfPlayerNames;
    }

    public boolean isStarted(){
        return game.isStarted();
    }

    public int getNumberOfPlayers(){
        return game.getNumberOfPlayers();
    }

    public String getPrefix(){
        return game.getId();
    }
}
