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
    private List<Player> players;
    private Auction auction;
    private String id;
    private String directSale;
    private int availableHouses;
    private int availableHotels;
    private List<Turn> turns = new ArrayList<>();
    private boolean canRoll;
    private boolean ended;
    private String currentPlayer;
    private String winner;
    private static List<CommunityOrChanceCard> chanceCards = new ArrayList<>();
    private static List<CommunityOrChanceCard> communityCards = new ArrayList<>();
    private List<Integer> lastDiceRoll = new ArrayList<>();
    private final Random random = new Random();

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

    public List<Integer> getLastDiceRoll() {
        return lastDiceRoll;
    }

    public void setLastDiceRoll(List<Integer> lastDiceRoll) {
        this.lastDiceRoll = lastDiceRoll;
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
        for (Player player : players){
            if (Objects.equals(player.getName(), name)) {
                throw new IllegalArgumentException("There is already a player with this name!");
            }
        }
        players.add(new Player(name, icon));
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
        }else{
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
        communityCards.add(new PayCC("Doctor's fee. Pay $50", 50));
        communityCards.add(new PayCC("Pay hospital fees of $100", 100));
        communityCards.add(new PayCC("Pay school fees of $50", 50));
        communityCards.add(new ReceiveCC("Bank error in your favor. Collect $200", 200));
        communityCards.add(new ReceiveCC("From sale of stock you get $50", 50));
        communityCards.add(new ReceiveCC("Holiday fund matures. Receive $100", 100));
        communityCards.add(new ReceiveCC("Income tax refund. Collect $20", 20));
        communityCards.add(new ReceiveCC("Life insurance matures. Collect $100", 100));
        communityCards.add(new ReceiveCC("Receive $25 consultancy fee", 25));
        communityCards.add(new ReceiveCC("You inherit $100", 100));
        communityCards.add(new ReceiveCC("You have won second prize in a beauty contest. Collect $10", 10));
        communityCards.add(new GoToJailCC("Go to Jail. Go directly to jail, do not pass Go, do not collect $200"));
        communityCards.add(new GoToGo("Advance to Go (Collect $200)"));
        communityCards.add(new GetOutOfJailFreeCard("Get Out of Jail Free"));
        communityCards.add(new CollectOrGiveEveryPlayer("It is your birthday. Collect $10 from every player", 10));
        communityCards.add(new Repairs("You are assessed for street repair. $40 per house. $115 per hotel", 40, 115));
    }

    public static void createChanceCards(){
        chanceCards.add(new GetOutOfJailFreeCard("Get Out of Jail Free"));
        chanceCards.add(new PayCC("Speeding fine $15", 15));
        chanceCards.add(new ReceiveCC("Your building loan matures. Collect $150", 150));
        chanceCards.add(new GoToTile("Advance to St. Charles Place. If you pass Go, collect $200", 11));
        chanceCards.add(new GoToTile("Advance to Illinois Avenue. If you pass Go, collect $200", 24));
        chanceCards.add(new GoToTile("Take a trip to Reading Railroad. If you pass Go, collect $200",5));
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

    public static List<Tile> getGameTiles(){
        return List.of(
                new Tile("Go", 0, "Go", "passes 'GO!' and receives 200 for it", "go"),
                new Street("Mediterranean", 1, "street", 2, "PURPLE", 10, 30, 90, 160, 250, 50, 2, 30, 60),
                new Tile("Community Chest I", 2, "community chest", "Draw a card", "community"),
                new Street("Baltic", 3, "street", 2, "PURPLE", 20, 60, 180, 320, 450, 50, 4, 30, 60),
                new Tile("Tax Income", 4, "Tax Income", "Pay taxes", "incometax"),
                new Railroad("Reading RR", 5, "railroad", 4, "BLACK", 25, 100, 200),
                new Street("Oriental", 6, "street", 3, "LIGHTBLUE", 30, 90, 270, 400, 550, 50, 6, 50, 100),
                new Tile("Chance I", 7, "chance", "Draw a card", "chance"),
                new Street("Vermont", 8, "street", 3, "LIGHTBLUE", 30, 90, 270, 400, 550, 50, 6, 50, 100),
                new Street("Connecticut", 9, "street", 3, "LIGHTBLUE", 40, 100, 300, 450, 600, 50, 8, 50, 120),
                new Tile("Jail", 10, "Jail", "Just visiting", "visiting"),
                new Street("Saint Charles Place", 11, "street", 3, "VIOLET", 50, 150, 450, 625, 750, 100, 10, 70, 140),
                new Utility("Electric Company", 12, "utility", 2, "WHITE", 75, 150),
                new Street("States", 13, "street", 3, "VIOLET", 50, 150, 450, 625, 750, 100, 10, 70, 140),
                new Street("Virginia", 14, "street", 3, "VIOLET", 60, 180, 500, 700, 900, 100, 12, 80, 160),
                new Railroad("Pennsylvania RR", 15, "railroad", 4, "BLACK", 25, 100, 200),
                new Street("Saint James", 16, "street", 3, "ORANGE", 70, 200, 550, 750, 950, 100, 14, 90, 180),
                new Tile("Community Chest II", 17, "community chest", "Draw a card", "community"),
                new Street("Tennessee", 18, "street", 3, "ORANGE", 70, 200, 550, 750, 950, 100, 14, 90, 180),
                new Street("New York", 19, "street", 3, "ORANGE", 80, 220, 600, 800, 1000, 100, 16, 100, 200),
                new Tile("Free Parking", 20, "Free Parking", "Nothing specials happens here", "parking"),
                new Street("Kentucky Avenue", 21, "street", 3, "RED", 90, 250, 700, 875, 1050, 150, 18, 110, 220),
                new Tile("Chance II", 22, "chance", "Draw a card", "chance"),
                new Street("Indiana Avenue", 23, "street", 3, "RED", 100, 300, 750, 925, 1100, 150, 20, 120, 240),
                new Street("Illinois Avenue", 24, "street", 3, "RED", 100, 300, 750, 925, 1100, 150, 20, 120, 240),
                new Railroad("Baltimore and Ohio RR", 25, "railroad", 4, "BLACK", 25, 100, 200),
                new Street("Atlantic", 26, "street", 3, "YELLOW", 110, 330, 800, 975, 1150, 150, 22, 130, 260),
                new Street("Ventnor", 27, "street", 3, "YELLOW", 110, 330, 800, 975, 1150, 150, 22, 130, 260),
                new Utility("Water Works", 28, "utility", 2, "WHITE", 75, 150),
                new Street("Marvin Gardens", 29, "street", 3, "YELLOW", 120, 360, 850, 1025, 1200, 150, 24, 280, 280),
                new Tile("Go to Jail", 30, "Go to Jail", "has to go to jail", "jail"),
                new Street("Pacific", 31, "street", 3, "DARKGREEN", 130, 390, 900, 1100, 1275, 200, 26, 150, 300),
                new Street("North Carolina", 32, "street", 3, "DARKGREEN", 130, 390, 900, 1100, 1275, 200, 26, 150, 300),
                new Tile("Community Chest III", 33, "community chest", "Draw a card", "community"),
                new Street("Pennsylvania", 34, "street", 3, "DARKGREEN", 150, 450, 1000, 1200, 1400, 200, 28, 160, 320),
                new Railroad("Short Line RR", 35, "railroad", 4, "BLACK", 25, 100, 200),
                new Tile("Chance III", 36, "chance", "Draw a card", "chance"),
                new Street("Park Place", 37, "street", 2, "DARKBLUE", 175, 500, 1100, 1300, 1500, 200, 35, 175, 350),
                new Tile("Luxury Tax", 38, "Luxury Tax", "Pay taxes", "luxtax"),
                new Street("Boardwalk", 39, "street", 3, "DARKBLUE", 200, 600, 1400, 1700, 2000, 200, 50, 200, 400)
        );
    }
}
