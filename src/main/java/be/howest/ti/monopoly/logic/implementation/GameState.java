package be.howest.ti.monopoly.logic.implementation;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private int numberOfPlayers;
    private List<Player> players = new ArrayList<>();
    private boolean started;
    private int directSale;
    private int availableHouses;
    private int availableHotels;
    private List<Turn> turns = new ArrayList<>();
    private boolean canroll;
    private boolean ended;
    private String currentPlayer;
    private String winner;

    public GameState(int numberOfPlayers, boolean started, int directSale, int availableHouses, int availableHotels, boolean canroll, boolean ended,  String winner) {
        this.numberOfPlayers = numberOfPlayers;
        this.started = started;
        this.directSale = directSale;
        this.availableHouses = availableHouses;
        this.availableHotels = availableHotels;
        this.canroll = canroll;
        this.ended = ended;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
    }

    public GameState(int numberOfPlayers) {
        this(numberOfPlayers, false, 0, 31, 12, true, false, null);
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isStarted() {
        return started;
    }

    public int getDirectSale() {
        return directSale;
    }

    public int getAvailableHouses() {
        return availableHouses;
    }

    public int getAvailableHotels() {
        return availableHotels;
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public boolean isCanroll() {
        return canroll;
    }

    public boolean isEnded() {
        return ended;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String getWinner() {
        return winner;
    }

    // iedere keer dat player join request word gestuurd zal deze functie de player toe voegen
    public void addPlayers(String name, String icon){
        players.add(new Player(name, icon));
    }

    // na elke roll komt de mmove hier terecht
    public void addTurns(Turn newTurn){
        turns.add(newTurn);
    }
}
