package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AuctionTest {

    Game game;

    @BeforeEach
    public void createGame() {
        game = new Game(2, "PilsoPoly", 1);
        game.addPlayer("niels", "beer");
        game.addPlayer("lukas", "soete");
    }

    @Test
    public void getLastBidderTest() {
        Auction testAuction = new Auction(100, 30, "niels", "tile", game);
        assertEquals("niels", testAuction.getLastBidder());
        assertThrows(IllegalMonopolyActionException.class, () -> {
           testAuction.addBid("niels", 150);
        });
    }

    @Test
    public void getHighestBidTest() {
        Auction testAuction = new Auction(100, 30, "niels", "tile", game);;
        assertEquals(100, testAuction.getHighestBid());
        assertThrows(IllegalMonopolyActionException.class, () -> {
            testAuction.addBid("robin", 50);
        });
    }
}