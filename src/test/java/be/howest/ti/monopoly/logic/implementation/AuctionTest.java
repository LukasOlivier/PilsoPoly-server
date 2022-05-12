package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuctionTest {

    @Test
    public void getLastBidderTest() {
        Auction testAuction = new Auction(100, 30, "niels", "tile");
        assertEquals("niels", testAuction.getLast_bidder());
        assertThrows(IllegalMonopolyActionException.class, () -> {
           testAuction.setLast_bidder("niels");
        });
    }

    @Test
    public void getHighestBidTest() {
        Auction testAuction = new Auction(100, 30, "niels", "tile");
        assertEquals(100, testAuction.getHighest_bid());
        assertThrows(IllegalMonopolyActionException.class, () -> {
            testAuction.setHighest_bid(50);
        });
    }
}