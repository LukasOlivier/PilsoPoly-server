package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuctionTest {

    @Test
    public void getLastBidderTest() {
        Auction testAuction = new Auction(100, 30, "niels", "tile");
        assertEquals("niels", testAuction.getLastBidder());
        assertThrows(IllegalMonopolyActionException.class, () -> {
           testAuction.setLastBidder("niels");
        });
    }

    @Test
    public void getHighestBidTest() {
        Auction testAuction = new Auction(100, 30, "niels", "tile");
        assertEquals(100, testAuction.getHighestBid());
        assertThrows(IllegalMonopolyActionException.class, () -> {
            testAuction.setHighestBid(50);
        });
    }
}