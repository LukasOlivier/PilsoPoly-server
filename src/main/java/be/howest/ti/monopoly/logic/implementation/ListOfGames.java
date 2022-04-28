package be.howest.ti.monopoly.logic.implementation;

import java.util.LinkedList;
import java.util.List;

public class ListOfGames {

    private LinkedList listOfGames;

    public ListOfGames(){
        listOfGames = new LinkedList<>();
    }

    public LinkedList getListOfGames() {
        return listOfGames;
    }

    public void addGame(Game game){
        listOfGames.push(game);
    }

}
