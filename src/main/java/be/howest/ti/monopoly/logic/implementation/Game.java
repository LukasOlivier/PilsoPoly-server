package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.tiles.AllGameTiles;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class Game {

    private int numberOfPlayers;
    private boolean started;
    private final List<Player> players;
    private Auction auction;
    private String id;
    private String directSale;
    private int availableHouses;
    private int availableHotels;
    private List<Turn> turns = new ArrayList<>();
    private boolean canRoll = true;
    private boolean ended;
    private String currentPlayer;
    private String winner;
    private Dice lastDiceRoll;

    private List<Tile> gameTiles;

    public Game(int numberOfPlayers, String prefix, int size) {
        setNumberOfPlayers(numberOfPlayers);
        this.started = false;
        this.players = new LinkedList<>();
        setId(prefix, size);
        this.gameTiles = AllGameTiles.createGameTiles();
    }

    public List<Integer> getLastDiceRoll() {
        if (lastDiceRoll != null) {
            return List.of(lastDiceRoll.getDiceOne(), lastDiceRoll.getDiceTwo());
        }
        return Collections.emptyList();
    }

    @JsonIgnore
    public int getLastDiceRollFullAmount(){
        if (lastDiceRoll != null) {
            return lastDiceRoll.getDiceOne() + lastDiceRoll.getDiceTwo();
        }
        return -1;
    }

    public void setLastDiceRoll(Dice lastDiceRoll) {
        this.lastDiceRoll = lastDiceRoll;
    }

    public void setId(String id, int size) {
        int increasePrefixCount = 1;
        if (!Objects.equals(id, "PilsoPoly")) {
            throw new IllegalArgumentException("Prefix is not allowed!");
        }
        this.id = id + "_" + (size + increasePrefixCount);
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        if (numberOfPlayers < 2 || numberOfPlayers > 8) {
            throw new IllegalArgumentException("Number of players should be between 2 and 8");
        } else {
            this.numberOfPlayers = numberOfPlayers;
        }
    }

    public void dontBuyProperty(String bidder, String property) {
        auction = new Auction(bidder, property, this);
    }

    public void placeBidOnBankAuction(String bidder, int amount) {
        auction.addBid(bidder, amount);
    }

    public Auction getAuction() {
        if ( auction != null && auction.auctionHasEnded() ) {
            return null;
        }
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

    @JsonIgnore
    public List<Tile> getGameTiles(){
        return this.gameTiles;
    }

    public void setStarted() {
        this.started = true;
    }

    public void addTurn(Turn turn) {
        turns.add(turn);
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

    public Player getSpecificPlayer(String name) {
        for (Player player : players) {
            if (Objects.equals(player.getName(), name)) {
                return player;
            }
        }
        throw new MonopolyResourceNotFoundException("No player with this name is found");
    }

    public Turn getCurrentTurn() {
        if (!turns.isEmpty()) {
            return getTurns().get(getTurns().size() - 1);
        } else {
            return null;
        }
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

    public void addPlayer(String name, String icon) {
        for (Player player : players) {
            if (Objects.equals(player.getName(), name)) {
                throw new IllegalArgumentException("There is already a player with this name!");
            }
        }
        players.add(new Player(name, icon));
        if (getCurrentPlayer() == null) {
            setCurrentPlayer(name);
        }
        if (players.size() == numberOfPlayers) {
            this.started = true;
            this.canRoll = true;

        }
    }

    public void isEveryoneBankrupt() {
            int numberOfPlayersBankrupt = 0;
            String possibleWinner = null;
            for (Player player : getPlayers()) {
                if (player.isBankrupt()) {
                    numberOfPlayersBankrupt++;
                } else {
                    possibleWinner = player.getName();
                }
            }
            if ((numberOfPlayersBankrupt == getNumberOfPlayers() - 1) && possibleWinner != null) {
                this.winner = possibleWinner;
            }
    }

    @Override
    public String toString() {
        return "Game{" +
                "numberOfPlayers=" + numberOfPlayers +
                ", started=" + started +
                ", id='" + id + '\'' +
                '}';
    }
}
