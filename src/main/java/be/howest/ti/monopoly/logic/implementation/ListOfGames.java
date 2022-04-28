package be.howest.ti.monopoly.logic.implementation;

import java.util.ArrayList;
import java.util.List;

public class ListOfGames {

    private List<Game> listOfGames = new ArrayList<>();

    public List<Game> getListOfGames() {
        return listOfGames;
    }

    public void addGame(Game game){
        listOfGames.add(game);
    }

    public int getListSize() {
        return listOfGames.size();
    }

}
