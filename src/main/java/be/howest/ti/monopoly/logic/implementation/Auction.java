package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;

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
        if ( this.highest_bid >= amount ) {
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
}
