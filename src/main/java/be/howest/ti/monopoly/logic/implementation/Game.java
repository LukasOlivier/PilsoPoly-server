package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.web.Request;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {

    private int numberOfPlayers;
    private boolean started;
    private LinkedList players;
    private String id;
    private String directSale;
    private int availableHouses;
    private int availableHotels;
    private List<Turn> turns = new ArrayList<>();
    private boolean canroll;
    private boolean ended;
    private String currentPlayer;
    private String winner;

    // This is to create a dummy game
    public Game(int numberOfPlayers, boolean started, String id, String directSale, int availableHouses, int availableHotels, boolean canroll, boolean ended, String currentPlayer, String winner) {
        this.numberOfPlayers = numberOfPlayers;
        this.started = started;
        this.id = id;
        this.players = new LinkedList<>();
        this.directSale = directSale;
        this.availableHouses = availableHouses;
        this.availableHotels = availableHotels;
        this.canroll = canroll;
        this.ended = ended;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
    }

    public Game(Request request) {
        if (request == null) {
            throw new IllegalArgumentException();
        }
        setNumberOfPlayers(request.getNumberOfPlayersForNewGame());
        this.started = false;
        this.players = new LinkedList<>();
        this.id = request.getPrefixForNewGame();
    }

    public void setId(String id) {
        if (id == null || id.contains("-")) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        if (numberOfPlayers < 2 || numberOfPlayers > 8) {
            throw new IllegalArgumentException("Number of players should be between 2 and 8");
        } else {
            this.numberOfPlayers = numberOfPlayers;
        }
    }


    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public boolean isStarted() {
        return started;
    }

    public LinkedList getPlayers() {
        return players;
    }

    public String getId() {
        return id;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public void addPlayer(String name, String icon){
        players.add(new Player(name, icon));
    }

    public String getDirectSale() {
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


    public void setDirectSale(String directSale) {
        this.directSale = directSale;
    }

    public void setAvailableHouses(int availableHouses) {
        this.availableHouses = availableHouses;
    }

    public void setAvailableHotels(int availableHotels) {
        this.availableHotels = availableHotels;
    }
    // todo make this add turns
    public void setTurns(List<Turn> turns) {
        this.turns = turns;
    }

    public void setCanroll(boolean canroll) {
        this.canroll = canroll;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
