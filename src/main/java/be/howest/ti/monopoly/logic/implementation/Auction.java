package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;

import java.util.Objects;

public class Auction {

    private int highest_bid;
    private int duration;
    private String last_bidder;
    private String property;

    public Auction(int highest_bid, int duration, String bidder, String property) {
        this.highest_bid = highest_bid;
        this.duration = duration;
        this.last_bidder = bidder;
        this.property = property;
    }

    public void setLast_bidder(String last_bidder) {
        if ( this.last_bidder.equals(last_bidder) ) {
            throw new IllegalMonopolyActionException("Wait for another player to bid!");
        } else {
            this.last_bidder = last_bidder;
        }
    }

    public void setHighest_bid(int amount) {
        if ( this.highest_bid == amount ) {
            throw new IllegalMonopolyActionException("Amount mus be higher than previous bid!");
        } else {
            this.highest_bid = amount;
        }
    }

    public int getHighest_bid() {
        return highest_bid;
    }

    public int getDuration() {
        return duration;
    }

    public String getLast_bidder() {
        return last_bidder;
    }

    public String getProperty() {
        return property;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return highest_bid == auction.highest_bid && duration == auction.duration && Objects.equals(last_bidder, auction.last_bidder) && Objects.equals(property, auction.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(highest_bid, duration, last_bidder, property);
    }

    @Override
    public String toString(){
        return "Property: " + property + "\nHighest bid: " + highest_bid + "\nlast bidder: " + last_bidder;
    }
}
