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
    private List<turn> turns = new ArrayList<>();
    private boolean canroll;
    private boolean ended;
    private String currentPlayer;
    private String winner;

}
