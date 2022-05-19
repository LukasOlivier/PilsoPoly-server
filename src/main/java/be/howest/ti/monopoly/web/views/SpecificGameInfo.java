package be.howest.ti.monopoly.web.views;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class SpecificGameInfo {
    private final Game game;

    public SpecificGameInfo(Game game) {
        this.game = game;
    }

    public List<JsonObject> getPlayers() {
        List<JsonObject> listOfPlayerNames = new ArrayList<>();
        for (Player player : game.getPlayers()) {
            listOfPlayerNames.add(new JsonObject()
                    .put("name", player.getName())
                    .put("icon", player.getIcon()));
        }
        return listOfPlayerNames;
    }

    public boolean isStarted() {
        return game.isStarted();
    }

    public int getNumberOfPlayers() {
        return game.getNumberOfPlayers();
    }

    public String getId() {
        return game.getId();
    }

    public String getPrefix() {
        return game.getId();
    }
}
