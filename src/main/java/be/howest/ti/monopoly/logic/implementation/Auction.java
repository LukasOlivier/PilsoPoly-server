package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import java.util.Objects;

public class Auction {


    private int highestBid = 0;
    private int duration = 30;
    private String lastBidder;
    private final String property;
    private Game game;

    public Auction(String bidder, String property, Game game) {
        this.lastBidder = bidder;
        this.property = property;
        this.game = game;
        checkTimer();
    }

    public void decreaseTimer() {
        new Thread( new Runnable() {
            public void run()  {
                try  { Thread.sleep( 1000 ); }
                catch (InterruptedException ie)  {}
                duration--;
                checkTimer();
            }
        } ).start();
    }

    public void checkTimer() {
        if ( duration > 0 ) {
            decreaseTimer();
        } else {
            Player auctionWinner = findAuctionWinner();
            Tile foundTile = findTile(property);
            PlayerProperty wonProperty = new PlayerProperty(makeFoundTileBought(foundTile));
            updatePlayer(auctionWinner, wonProperty);
            resumeGame();
        }
    }

    public void updatePlayer(Player player, PlayerProperty property) {
        player.addProperty(property);
        player.removeMoney(highestBid);
    }

    public void resumeGame() {
        Player currentPlayer = game.getSpecificPlayer(game.getCurrentPlayer());
        Move.checkIfPlayerCanRollAgain(game, currentPlayer);
    }

    public Property makeFoundTileBought(Tile property) {
        Property foundProperty = (Property) property;
        foundProperty.setBought(true);
        return foundProperty;
    }

    public Player findAuctionWinner() {
        for (Player player : game.getPlayers()) {
            if (player.getName().equals(lastBidder)) {
                return player;
            }
        }
        return null;
    }

    public Tile findTile(String wonProperty) {
        for ( Tile tile : game.getGameTiles() ) {
            if ( tile.getName().equals(wonProperty) ) {
                return tile;
            }
        }
        return null;
    }

    public void addBid(String bidder, int amount) {
        if (!lastBidder.equals(bidder) && amount > highestBid) {
            lastBidder = bidder;
            highestBid = amount;
        } else {
            throw new IllegalMonopolyActionException("Could not add bid");
        }
    }

    public int getHighestBid() {
        return highestBid;
    }

    public int getDuration() {
        return duration;
    }

    public String getLastBidder() {
        return lastBidder;
    }

    public String getProperty() {
        return property;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return highestBid == auction.highestBid && duration == auction.duration && Objects.equals(lastBidder, auction.lastBidder) && Objects.equals(property, auction.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(highestBid, duration, lastBidder, property);
    }

    @Override
    public String toString(){
        return "Property: " + property + "\nHighest bid: " + highestBid + "\nlast bidder: " + lastBidder;
    }
}
