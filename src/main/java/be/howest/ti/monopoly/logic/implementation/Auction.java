package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;

import java.util.Objects;

public class Auction {

    private int highestBid;
    private final int duration;
    private String lastBidder;
    private final String property;

    public Auction(int highestBid, int duration, String bidder, String property) {
        this.highestBid = highestBid;
        this.duration = duration;
        this.lastBidder = bidder;
        this.property = property;
    }

    public void setLastBidder(String lastBidder) {
        if ( this.lastBidder.equals(lastBidder) ) {
            throw new IllegalMonopolyActionException("Wait for another player to bid!");
        } else {
            this.lastBidder = lastBidder;
        }
    }

    public void setHighestBid(int amount) {
        if ( this.highestBid >= amount ) {
            throw new IllegalMonopolyActionException("Amount must be higher than previous bid!");
        } else {
            this.highestBid = amount;
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
