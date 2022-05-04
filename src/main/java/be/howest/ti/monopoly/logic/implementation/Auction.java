package be.howest.ti.monopoly.logic.implementation;

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
        this.last_bidder = last_bidder;
    }

    public void setHighest_bid(int amount) {
        this.highest_bid = amount;
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
