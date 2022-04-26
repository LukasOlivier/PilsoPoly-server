package be.howest.ti.monopoly.logic.implementation;

import java.util.LinkedList;

public class Game {

    private int numberOfPlayers;
    private boolean started;
    private LinkedList players;
    private String id;

    public Game(int numberOfPlayers, String id) {
        setNumberOfPlayers(numberOfPlayers);
        this.started = false;
        this.players = new LinkedList<>();
        this.id = id;
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

    public void addPlayer(Player player) {
        this.players.add(player);
    }
}
