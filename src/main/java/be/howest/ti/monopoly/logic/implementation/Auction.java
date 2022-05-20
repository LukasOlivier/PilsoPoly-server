package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class Auction {
    private int highestBid = 0;
    private final long startTime = System.currentTimeMillis();
    private static final long DURATION = 29000;
    private String lastBidder;
    private String property;
    private Game game;
    private boolean ended = false;

    public Auction(String bidder, String property, Game game) {
        this.lastBidder = bidder;
        this.property = property;
        this.game = game;
    }

    public void endAuction() {
        ended = true;
        Player auctionWinner = findAuctionWinner();
        Tile foundTile = findTile(property);
        PlayerProperty wonProperty = new PlayerProperty(makeFoundTileBought(foundTile));
        updatePlayer(auctionWinner, wonProperty);
        resumeGame();
    }

    public boolean auctionHasEnded() {
        return ended;
    }

    public boolean checkIfCanBid() {
        long currentTime = System.currentTimeMillis();
        return startTime + DURATION > currentTime;
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
        if (checkIfCanBid()) {
            if ( amount == -1 ) {
                endAuction();
            }
            else if (!lastBidder.equals(bidder) && amount > highestBid) {
                lastBidder = bidder;
                highestBid = amount;
            } else {
                throw new IllegalMonopolyActionException("Could not add bid.");
            }
        } else {
            endAuction();
        }
    }

    public int getHighestBid() {
        return highestBid;
    }

    public String getLastBidder() {
        return lastBidder;
    }

    public String getProperty() {
        return property;
    }
}
