package be.howest.ti.monopoly.logic.implementation;

import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListOfGames {

    private List<Game> listOfGames;

    public ListOfGames(){
        listOfGames = new ArrayList();
    }

    public List<JsonObject> getListOfGames() {
        List<JsonObject> specificList = new ArrayList();
        for (Game game : listOfGames) {
            specificList.add(game.getSpecificGameInfo());
        }
        return specificList;
    }

    public void addGame(Game game){
        listOfGames.add(game);
    }
}
