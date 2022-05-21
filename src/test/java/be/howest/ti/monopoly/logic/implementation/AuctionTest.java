package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.implementation.tiles.Colors;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.StreetHouseRent;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        Auction testAuction = new Auction("niels", "tile", game);
        assertEquals("niels", testAuction.getLastBidder());
        assertThrows(IllegalMonopolyActionException.class, () -> {
           testAuction.addBid("niels", 150);
        });
    }

    @Test
    public void getHighestBidTest() {
        Auction testAuction = new Auction("niels", "tile", game);
        assertEquals(0, testAuction.getHighestBid());
        testAuction.addBid("robin", 100);
        assertThrows(IllegalMonopolyActionException.class, () -> {
            testAuction.addBid("niels", 50);
        });
    }

    @Test
    public void clientSendsBidToEndAuction() {
        Auction testAuction = new Auction("niels", "Kriek", game);
        testAuction.addBid("niels", -1);
        assertTrue(testAuction.auctionHasEnded());
    }

    @Test
    public void endAuction() {
        Auction testAuction = new Auction("niels", "Kriek", game);
        testAuction.endAuction();
        assertTrue(testAuction.auctionHasEnded());
    }

    @Test
    public void findAuctionWinner() {
        Auction testAuction = new Auction("niels", "jupiler", game);
        testAuction.addBid("lukas", 100);
        assertEquals(game.getSpecificPlayer("lukas"), testAuction.findAuctionWinner());;
    }

    @Test
    public void findTile() {
        Auction testAuction = new Auction("niels", "jupiler", game);
        Tile tile = new Street("Bush12", 39, 3, Colors.DARKBLUE.toString(), new StreetHouseRent(200, 600, 1400, 1700, 2000), 200, 50, 200, 400);
        assertEquals(testAuction.findTile("Bush12"), tile);
        assertNull(testAuction.findTile("randomTile"));
    }

    @Test
    public void getAuctionProperty() {
        Auction testAuction = new Auction("niels", "jupiler", game);
        assertEquals("jupiler", testAuction.getProperty());
    }
}