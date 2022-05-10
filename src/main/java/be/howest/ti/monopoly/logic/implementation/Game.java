package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.web.Request;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties()
public class Game {
    private int numberOfPlayers;
    private boolean started;
    private List<Player> players;
    private Auction auction;
    private String id;
    private String directSale;
    private int availableHouses;
    private int availableHotels;
    private List<Turn> turns = new LinkedList<>();
    private boolean canRoll;
    private boolean ended;
    private String currentPlayer;
    private String winner;

    public Game(int numberOfPlayers, String prefix, int size) {
        setNumberOfPlayers(numberOfPlayers);
        this.started = false;
        this.players = new LinkedList<>();
        setId(prefix, size);
    }

    public void setId(String id, int size) {
        int increasePrefixCount = 1;
        if (!Objects.equals(id, "PilsoPoly")) {
            throw new IllegalArgumentException();
        }
        this.id = id + "_" + (size+increasePrefixCount);
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        if (numberOfPlayers < 2 || numberOfPlayers > 8) {
            throw new IllegalArgumentException("Number of players should be between 2 and 8");
        } else {
            this.numberOfPlayers = numberOfPlayers;
        }
    }

    public void startPlayerAuction(int bid, int duration, String bidder, String property) {
        auction = new Auction(bid, duration, bidder, property);
    }

    public void placeBidOnPlayerAuction(String bidder, int amount) {
            auction.setHighest_bid(amount);
            auction.setLast_bidder(bidder);
    }

    public Auction getAuction() {
        return auction;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public boolean isStarted() {
        return started;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getId() {
        return id;
    }

    public void setStarted() {
        this.started = true;
    }

    public void addPlayer(String name, String icon){
        //if (started) {
            //throw new IllegalMonopolyActionException("The game has already started");
        // }
        for (Player player : players){
            if (Objects.equals(player.getName(), name)) {
                throw new IllegalArgumentException("There is already a player with this name!");
            }
        }
        players.add(new Player(name, icon));
        if (players.size() == numberOfPlayers) {
            this.started = true;
        }
    }

    public void addTurns(String name, String type, String description, String tile, int diceOne, int diceTwo ){
        Move newMove = new Move(tile, type, description);
        turns.add(new Turn( name, "DEFAULT", newMove, diceOne, diceTwo));
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

    public boolean isCanRoll() {
        return canRoll;
    }

    public boolean isEnded() {
        return ended;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getSpecificPlayer(String name){
        for (Player player : players){
            if (Objects.equals(player.getName(), name)) {
                return player;
            }
        }
        throw new MonopolyResourceNotFoundException("No player with this name is found");
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

    public void setCanRoll(boolean canRoll) {
        this.canRoll = canRoll;
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

    public void isEveryoneBankrupt(){
        int bankruptCounter = 0;
        String possibleWinner = null;
        for(Player player : getPlayers()){
            if (player.isBankrupt()){
                bankruptCounter++;
            }else{
                possibleWinner = player.getName();
            }
        }
        if ((bankruptCounter == getNumberOfPlayers() - 1) && possibleWinner != null){
            this.winner = possibleWinner;
        }
    }
}
