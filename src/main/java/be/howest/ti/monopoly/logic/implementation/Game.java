package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.communityandchance.*;
import be.howest.ti.monopoly.logic.implementation.communityandchance.specific_cards.*;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.tiles.AllGameTiles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.security.SecureRandom;
import java.util.*;

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
    private boolean canRoll = true;
    private boolean ended;
    private String currentPlayer;
    private String winner;
    private static final List<CommunityOrChanceCard> chanceCards = createChanceCards();
    private static final List<CommunityOrChanceCard> communityCards = createCommunityCards();
    private static final Random random = new SecureRandom();
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


    public static List<CommunityOrChanceCard> createCommunityCards(){
        return List.of(
            new PayOrReceive("Doctor's fee. Pay $50", -50),
            new PayOrReceive("Pay hospital fees of $100", -100),
            new PayOrReceive("Pay school fees of $50", -50),
            new PayOrReceive("Bank error in your favor. Collect $200", 200),
            new PayOrReceive("From sale of stock you get $50", 50),
            new PayOrReceive("Holiday fund matures. Receive $100", 100),
            new PayOrReceive("Income tax refund. Collect $20", 20),
            new PayOrReceive("Life insurance matures. Collect $100", 100),
            new PayOrReceive("Receive $25 consultancy fee", 25),
            new PayOrReceive("You inherit $100", 100),
            new PayOrReceive("You have won second prize in a beauty contest. Collect $10", 10),
            new GoToTile("Go to Jail. Go directly to jail, do not pass Go, do not collect $200", 0),
            new GoToTile("Advance to Go (Collect $200)", 0),
            new GetOutOfJailFreeCard("Get Out of Jail Free"),
            new CollectOrGiveEveryPlayer("It is your birthday. Collect $10 from every player", 10),
            new Repairs("You are assessed for street repair. $40 per house. $115 per hotel", 40, 115)
        );
    }

    public static List<CommunityOrChanceCard> createChanceCards(){
        return List.of(
            new GetOutOfJailFreeCard("Get Out of Jail Free"),
            new PayOrReceive("Your building loan matures. Collect $150", 150),
            new PayOrReceive("Speeding fine $15", -15),
            new PayOrReceive("Bank pays you dividend of $50", 50),
            new PayOrReceive("Your building loan matures. Collect $150", 150),
            new GoToTile("Go to Jail. Go directly to Jail, do not pass Go, do not collect $200", 10),
            new GoToTile("Advance to Go (Collect $200)", 0),
            new GoToTile("Advance to Boardwalk", 39),
            new GoToTile("Advance to St. Charles Place. If you pass Go, collect $200", 11),
            new GoToTile("Advance to Illinois Avenue. If you pass Go, collect $200", 24),
            new GoToTile("Take a trip to Reading Railroad. If you pass Go, collect $200",5),
            new CollectOrGiveEveryPlayer("You have been elected Chairman of the Board. Pay each player $50", 50),
            new Repairs("Make general repairs on all your property. For each house pay $25. For each hotel pay $100", 25, 100),
            new AdvanceToNearest("Advance to the nearest Utility. If unowned, you may buy it from the Bank", "utility"),
            new AdvanceToNearest("Advance to the nearest Railroad. If unowned, you may buy it from the Bank", "railroad")
        );
    }

    public static CommunityOrChanceCard getRandomCommunityCardAction(){
        int randomNumber = random.nextInt(communityCards.size());
        return communityCards.get(randomNumber);

    }

    public static CommunityOrChanceCard getRandomChanceCardAction() {
        int randomNumber = random.nextInt(chanceCards.size());
        return chanceCards.get(randomNumber);
    }

    public  void removePropertiesFromPlayer(Player player) {
        for (PlayerProperty playerProperty : player.getProperties()){
            playerProperty.getProperty().setBought(false);
            playerProperty.getProperty().setMortgaged(false);
        }
        player.setBankrupt();
    }
}
