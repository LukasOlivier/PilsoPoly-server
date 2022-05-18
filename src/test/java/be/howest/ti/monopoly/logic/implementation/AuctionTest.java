package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AuctionTest {

    @Test
    public void getLastBidderTest() {
        Auction testAuction = new Auction(100, 30, "niels", "tile", List.of(new Player("niels", "beer"), new Player("robin", "kriek")));
        assertEquals("niels", testAuction.getLastBidder());
        assertThrows(IllegalMonopolyActionException.class, () -> {
           testAuction.addBid("niels", 150);
        });
    }

    @Test
    public void getHighestBidTest() {
        Auction testAuction = new Auction(100, 30, "niels", "tile", List.of(new Player("niels", "beer"), new Player("robin", "kriek")));;
        assertEquals(100, testAuction.getHighestBid());
        assertThrows(IllegalMonopolyActionException.class, () -> {
            testAuction.addBid("robin", 50);
        });
    }
}