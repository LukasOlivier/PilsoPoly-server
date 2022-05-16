package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.communityandchance.*;
import be.howest.ti.monopoly.logic.implementation.communityandchance.specific_cards.*;
import be.howest.ti.monopoly.logic.implementation.tiles.Railroad;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.tiles.Utility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

@JsonIgnoreProperties({""})
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
    private static List<CommunityOrChanceCard> chanceCards = new ArrayList<>();
    private static List<CommunityOrChanceCard> communityCards = new ArrayList<>();
    private final Random random = new Random();
    private Dice lastDiceRoll;


    public Game(int numberOfPlayers, String prefix, int size) {
        setNumberOfPlayers(numberOfPlayers);
        this.started = false;
        this.players = new LinkedList<>();
        setId(prefix, size);
    }

    public List<Integer> getLastDiceRoll() {
        if (lastDiceRoll != null) {
            return List.of(lastDiceRoll.getDiceOne(), lastDiceRoll.getDiceTwo());
        }
        return Collections.emptyList();
    }

    public void setLastDiceRoll(Dice lastDiceRoll) {
        this.lastDiceRoll = lastDiceRoll;
    }

    public void setId(String id, int size) {
        int increasePrefixCount = 1;
        if (!Objects.equals(id, "PilsoPoly")) {
            throw new IllegalArgumentException();
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

    public void isEveryoneBankrupt() {
        int bankruptCounter = 0;
        String possibleWinner = null;
        for (Player player : getPlayers()) {
            if (player.isBankrupt()) {
                bankruptCounter++;
            } else {
                possibleWinner = player.getName();
            }
        }
        if ((bankruptCounter == getNumberOfPlayers() - 1) && possibleWinner != null) {
            this.winner = possibleWinner;
        }
    }

    public static void createCommunityCards(){
        communityCards.add(new PayOrReceive("Doctor's fee. Pay $50", -50));
        communityCards.add(new PayOrReceive("Pay hospital fees of $100", -100));
        communityCards.add(new PayOrReceive("Pay school fees of $50", -50));
        communityCards.add(new PayOrReceive("Bank error in your favor. Collect $200", 200));
        communityCards.add(new PayOrReceive("From sale of stock you get $50", 50));
        communityCards.add(new PayOrReceive("Holiday fund matures. Receive $100", 100));
        communityCards.add(new PayOrReceive("Income tax refund. Collect $20", 20));
        communityCards.add(new PayOrReceive("Life insurance matures. Collect $100", 100));
        communityCards.add(new PayOrReceive("Receive $25 consultancy fee", 25));
        communityCards.add(new PayOrReceive("You inherit $100", 100));
        communityCards.add(new PayOrReceive("You have won second prize in a beauty contest. Collect $10", 10));
        communityCards.add(new GoToTile("Go to Jail. Go directly to jail, do not pass Go, do not collect $200", 0));
        communityCards.add(new GoToTile("Advance to Go (Collect $200)", 0));
        communityCards.add(new GetOutOfJailFreeCard("Get Out of Jail Free"));
        communityCards.add(new CollectOrGiveEveryPlayer("It is your birthday. Collect $10 from every player", 10));
        communityCards.add(new Repairs("You are assessed for street repair. $40 per house. $115 per hotel", 40, 115));
    }

    public static void createChanceCards(){
        chanceCards.add(new GetOutOfJailFreeCard("Get Out of Jail Free"));
        chanceCards.add(new PayOrReceive("Your building loan matures. Collect $150", 150));
        chanceCards.add(new PayOrReceive("Speeding fine $15", -15));
        chanceCards.add(new PayOrReceive("Bank pays you dividend of $50", 50));
        chanceCards.add(new PayOrReceive("Your building loan matures. Collect $150", 150));
        chanceCards.add(new GoToTile("Go to Jail. Go directly to Jail, do not pass Go, do not collect $200", 10));
        chanceCards.add(new GoToTile("Advance to Go (Collect $200)", 0));
        chanceCards.add(new GoToTile("Advance to Boardwalk", 39));
        chanceCards.add(new GoToTile("Advance to St. Charles Place. If you pass Go, collect $200", 11));
        chanceCards.add(new GoToTile("Advance to Illinois Avenue. If you pass Go, collect $200", 24));
        chanceCards.add(new GoToTile("Take a trip to Reading Railroad. If you pass Go, collect $200",5));
        chanceCards.add(new CollectOrGiveEveryPlayer("You have been elected Chairman of the Board. Pay each player $50", 50));
        chanceCards.add(new Repairs("Make general repairs on all your property. For each house pay $25. For each hotel pay $100", 25, 100));
    }

    public void doRandomCommunityCardAction(Player player){
        int randomNumber = random.nextInt(communityCards.size());
        communityCards.get(randomNumber).cardAction(this, player);
    }

    public void doRandomChanceCardAction(Player player){
        int randomNumber = random.nextInt(chanceCards.size());
        chanceCards.get(randomNumber).cardAction(this, player);
    }

    public void doCommunityCard(int key, Player player){
        communityCards.get(key).cardAction(this, player);
    }
}
