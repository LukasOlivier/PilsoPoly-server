package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.tiles.*;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.Railroad;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.StreetHouseRent;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.Utility;
import be.howest.ti.monopoly.logic.implementation.tiles.specialtiles.ChanceTile;
import be.howest.ti.monopoly.logic.implementation.tiles.specialtiles.CommunityTile;
import be.howest.ti.monopoly.logic.implementation.tiles.specialtiles.GoToJailTile;
import be.howest.ti.monopoly.logic.implementation.tiles.specialtiles.TaxTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ServiceTest {

    MonopolyService service = new MonopolyService();
    Game game = new Game(2, "PilsoPoly", 0);

    @BeforeEach
    public void initTest(){
        service.addGame(game);
        service.joinGame("PilsoPoly_1", "Alice","icon");
        service.joinGame("PilsoPoly_1", "Bob","icon");
    }

    @Test
    public void testCommunityCards() {
        MonopolyService service = new MonopolyService();
        List<String> cards = List.of(
                "Advance to Go (Collect $200)",
                "Bank error in your favor. Collect $200",
                "Doctor's fee. Pay $50",
                "From sale of stock you get $50",
                "Get Out of Jail Free",
                "Go to Jail. Go directly to jail, do not pass Go, do not collect $200",
                "Holiday fund matures. Receive $100",
                "Income tax refund. Collect $20",
                "It is your birthday. Collect $10 from every player",
                "Life insurance matures. Collect $100",
                "Pay hospital fees of $100",
                "Pay school fees of $50",
                "Receive $25 consultancy fee",
                "You are assessed for street repair. $40 per house. $115 per hotel",
                "You have won second prize in a beauty contest. Collect $10",
                "You inherit $100");
        assertEquals(cards, service.getCommunityCards());
    }

    @Test
    public void getChanceCardsTest() {
        MonopolyService service = new MonopolyService();
        List<String> cards = List.of(
                "Advance to Boardwalk",
                "Advance to Go (Collect $200)",
                "Advance to Illinois Avenue. If you pass Go, collect $200",
                "Advance to St. Charles Place. If you pass Go, collect $200",
                "Advance to the nearest Railroad. If unowned, you may buy it from the Bank.",
                "Advance token to nearest Utility. If unowned, you may buy it from the Bank.",
                "Bank pays you dividend of $50",
                "Get Out of Jail Free",
                "Go to Jail. Go directly to Jail, do not pass Go, do not collect $200",
                "Make general repairs on all your property. For each house pay $25. For each hotel pay $100",
                "Speeding fine $15",
                "Take a trip to Reading Railroad. If you pass Go, collect $200",
                "You have been elected Chairman of the Board. Pay each player $50",
                "Your building loan matures. Collect $150"
        );
        assertEquals(cards, service.getChanceCards());
    }

    @Test
    public void getTiles() {
        MonopolyService service = new MonopolyService();
        List<Tile> tiles = List.of(
                new Tile("Go", 0, "Go", "passes 'GO!' and receives 200 for it", "go"),
                new Street("Delhaize 365", 1, 2, Colors.BROWN.toString(), new StreetHouseRent(10,30,90,160,250), 50, 2, 30, 60),
                new CommunityTile("Community Chest I", 2),
                new Street("Cara", 3, 2, Colors.BROWN.toString(), new StreetHouseRent(20, 60, 180, 320, 450), 50, 4, 30, 60),
                new TaxTile("Tax Income", 4, "Tax Income", "incometax"),
                new Railroad("Brewery Artois", 5),
                new Street("Heineken", 6, 3, Colors.LIGHTBLUE.toString(), new StreetHouseRent(30, 90, 270, 400, 550), 50, 6, 50, 100),
                new ChanceTile("Chance I", 7),
                new Street("Sparta Pils", 8, 3, Colors.LIGHTBLUE.toString(), new StreetHouseRent(30, 90, 270, 400, 550), 50, 6, 50, 100),
                new Street("Schuttenbrau", 9, 3, Colors.LIGHTBLUE.toString(), new StreetHouseRent(40, 100, 300, 450, 600), 50, 8, 50, 120),
                new Tile("Jail", 10, "Jail", "Just visiting.", "visiting"),
                new Street("Primus", 11, 3, Colors.VIOLET.toString(), new StreetHouseRent(50, 150, 450, 625, 750), 100, 10, 70, 140),
                new Utility("Electric Company", 12),
                new Street("Bavik", 13, 3, Colors.VIOLET.toString(), new StreetHouseRent(50, 150, 450, 625, 750), 100, 10, 70, 140),
                new Street("Bockor", 14, 3, Colors.VIOLET.toString(), new StreetHouseRent(60, 180, 500, 700, 900), 100, 12, 80, 160),
                new Railroad("Brewery Rodenbach", 15),
                new Street("Stella", 16, 3, Colors.ORANGE.toString(), new StreetHouseRent(70, 200, 550, 750, 950), 100, 14, 90, 180),
                new CommunityTile("Community Chest II", 17),
                new Street("Jupiler", 18, 3, Colors.ORANGE.toString(), new StreetHouseRent(70, 200, 550, 750, 950), 100, 14, 90, 180),
                new Street("Maes", 19, 3, Colors.ORANGE.toString(), new StreetHouseRent(80, 220, 600, 800, 1000), 100, 16, 100, 200),
                new Tile("Free Parking", 20, "Free Parking", "Nothing specials happens here", "parking"),
                new Street("Rodenbach", 21, 3, Colors.RED.toString(), new StreetHouseRent(90, 250, 700, 875, 1050), 150, 18, 110, 220),
                new ChanceTile("Chance II", 22),
                new Street("Kriek", 23, 3, Colors.RED.toString(), new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240),
                new Street("Kasteel Rouge", 24, 3, Colors.RED.toString(), new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240),
                new Railroad("Brewery Lupulus", 25),
                new Street("Duvel", 26, 3, Colors.YELLOW.toString(), new StreetHouseRent(110, 330, 800, 975, 1150), 150, 22, 130, 260),
                new Street("Omer", 27, 3, Colors.YELLOW.toString(), new StreetHouseRent(110, 330, 800, 975, 1150), 150, 22, 130, 260),
                new Utility("Water Works", 28 ),
                new Street("Westmalle", 29, 3, Colors.YELLOW.toString(), new StreetHouseRent(120, 360, 850, 1025, 1200), 150, 24, 280, 280),
                new GoToJailTile("Go to Jail", 30),
                new Street("Brugse zot", 31, 3, Colors.DARKGREEN.toString(), new StreetHouseRent(130, 390, 900, 1100, 1275), 200, 26, 150, 300),
                new Street("Chimay", 32, 3, Colors.DARKGREEN.toString(), new StreetHouseRent(130, 390, 900, 1100, 1275), 200, 26, 150, 300),
                new CommunityTile("Community Chest III", 33),
                new Street("Westvleteren", 34, 3, Colors.DARKGREEN.toString(), new StreetHouseRent(150, 450, 1000, 1200, 1400), 200, 28, 160, 320),
                new Railroad("Brewery Omer Vander Ghinste", 35),
                new ChanceTile("Chance III", 36),
                new Street("Cornet", 37, 2, Colors.DARKBLUE.toString(), new StreetHouseRent(175, 500, 1100, 1300, 1500), 200, 35, 175, 350),
                new TaxTile("Luxury Tax", 38, "Luxury Tax", "luxtax"),
                new Street("Bush12", 39, 3, Colors.DARKBLUE.toString(), new StreetHouseRent(200, 600, 1400, 1700, 2000), 200, 50, 200, 400)
        );
        assertEquals(tiles, service.getTiles());
    }

    @Test
    public void addGameTest() {
        MonopolyService service = new MonopolyService();
        Game game = new Game(2, "PilsoPoly", 0);
        service.addGame(game);
        assertEquals(1, service.getGameMapSize());
    }

    @Test
    void getAllGamesTest() {
        MonopolyService service = new MonopolyService();

        Game game = new Game(2, "PilsoPoly", 0);
        service.addGame(game);
        Game secondGame = new Game(4, "PilsoPoly", 1);
        service.addGame(secondGame);

        assertEquals(2, service.getGameMapSize());
    }

    @Test
    void clearGameListTest() {
        MonopolyService service = new MonopolyService();
        service.clearGameList();
        assertEquals(0, service.getGameMapSize());
    }

    @Test
    void getTileFromPositionTest() {
        MonopolyService service = new MonopolyService();
        Tile parking = new Tile("Free Parking", 20, "Free Parking", "Nothing specials happens here", "parking");
        assertEquals(parking, service.getTile(20));
    }

    @Test
    void getTileFromNameTest() {
        MonopolyService service = new MonopolyService();
        Tile parking = new Tile("Free Parking", 20, "Free Parking", "Nothing specials happens here", "parking");
        assertEquals(parking, service.getTile("Free Parking"));
    }

    @Test
    void getTileFromPositionNotFoundTest() {
        MonopolyService service = new MonopolyService();
        assertThrows(MonopolyResourceNotFoundException.class, () -> {
            service.getTile(-1);
        });
    }

    @Test
    void getTileFromNameNotFoundTest() {
        MonopolyService service = new MonopolyService();
        assertThrows(MonopolyResourceNotFoundException.class, () -> {
            service.getTile("invalid");
        });
    }

    @Test
    public void buyPropertyTest() {
        game.getSpecificPlayer("Alice").setCurrentTile(Tile.getTileFromPosition(game,1));
        service.buyProperty("PilsoPoly_1","Alice","Delhaize 365");
        PlayerProperty boughtTile = service.findBoughtPropertyByOwner("Delhaize 365","Alice",game);
        assertEquals(game.getSpecificPlayer("Alice").getProperties(),List.of(boughtTile));
    }

    @Test
    public void getGameByIdTest() {
        MonopolyService service = new MonopolyService();
        Game game = new Game(2, "PilsoPoly", 0);
        service.addGame(game);
        assertEquals(game, service.getGameById("PilsoPoly_1"));
    }

    @Test
    public void joinGameTest() {
        MonopolyService service = new MonopolyService();
        Game game = new Game(2, "PilsoPoly", 0);
        service.addGame(game);
        Player alice = new Player("Alice", "icon");
        service.joinGame("PilsoPoly_1", alice.getName(), alice.getIcon());
        assertEquals(List.of(alice), service.getGameById("PilsoPoly_1").getPlayers());
    }

    @Test
    public void dontBuyPropertyTest() {
        service.dontBuyProperty("PilsoPoly_1", "Alice", "BoardWalk");
        assertEquals("Alice", service.getPlayerAuctions("PilsoPoly_1").getLastBidder());
        service.placeBidOnBankAuction("PilsoPoly_1", "Bob", 100);
        assertEquals("Bob", service.getPlayerAuctions("PilsoPoly_1").getLastBidder());
    }

    @Test
    public void collectDebtTest() {
        game.getSpecificPlayer("Alice").setCurrentTile(Tile.getTileFromPosition(game,1));
        service.buyProperty("PilsoPoly_1","Alice","Delhaize 365");
        game.getSpecificPlayer("Bob").setCurrentTile(Tile.getTileFromPosition(game,1));

        service.collectDebt("PilsoPoly_1", "Bob", "Alice", "Delhaize 365");
    }

    @Test void setBankruptTest(){
        service.setBankrupt("Alice","PilsoPoly_1");
        assertTrue(game.getSpecificPlayer("Alice").isBankrupt());
    }

    @Test
    public void useComputeTaxTest() {
        service.useComputeTax("Alice","PilsoPoly_1");
        assertEquals("COMPUTE",game.getSpecificPlayer("Alice").getTaxSystem());
    }

    @Test
    public void useEstimateTaxTest() {
        service.useEstimateTax("Alice","PilsoPoly_1");
        assertEquals("ESTIMATE",game.getSpecificPlayer("Alice").getTaxSystem());
    }

    @Test
    public void getOutOfJailFineTest() {
        game.getSpecificPlayer("Alice").setJailed(true);
        service.getOutOfJailFine("PilsoPoly_1","Alice");
        assertFalse(game.getSpecificPlayer("Alice").isJailed());
    }
    @Test
    public void getOutOfJailFreeTest() {
        game.getSpecificPlayer("Alice").setJailed(true);
        game.getSpecificPlayer("Alice").addGetOutOfJailFreeCard();
        service.getOutOfJailFree("PilsoPoly_1","Alice");
        assertFalse(game.getSpecificPlayer("Alice").isJailed());
    }

    @Test
    public void buyHouseTest() {
        game.getSpecificPlayer("Alice").setCurrentTile(Tile.getTileFromPosition(game,1));
        service.buyProperty("PilsoPoly_1","Alice","Delhaize 365");

        game.getSpecificPlayer("Alice").setCurrentTile(Tile.getTileFromPosition(game,3));
        service.buyProperty("PilsoPoly_1","Alice","Cara");

        service.buyHouse("PilsoPoly_1","Alice","Delhaize 365");
        service.buyHouse("PilsoPoly_1","Alice","Cara");
        service.buyHouse("PilsoPoly_1","Alice","Delhaize 365");

        assertEquals(2,service.getCorrectProperty(game.getSpecificPlayer("Alice"),"Delhaize 365").getHouseCount());
        assertEquals(1,service.getCorrectProperty(game.getSpecificPlayer("Alice"),"Cara").getHouseCount());
    }

    @Test
    public void sellHouseTest() {
        game.getSpecificPlayer("Alice").setCurrentTile(Tile.getTileFromPosition(game,1));
        service.buyProperty("PilsoPoly_1","Alice","Delhaize 365");

        game.getSpecificPlayer("Alice").setCurrentTile(Tile.getTileFromPosition(game,3));
        service.buyProperty("PilsoPoly_1","Alice","Cara");

        service.buyHouse("PilsoPoly_1","Alice","Delhaize 365");
        service.buyHouse("PilsoPoly_1","Alice","Cara");
        service.sellHouse("PilsoPoly_1","Alice","Delhaize 365");

        assertEquals(0,service.getCorrectProperty(game.getSpecificPlayer("Alice"),"Delhaize 365").getHouseCount());
        assertEquals(1,service.getCorrectProperty(game.getSpecificPlayer("Alice"),"Cara").getHouseCount());
    }

    @Test
    public void takeMortgageTest(){
        game.getSpecificPlayer("Alice").setCurrentTile(Tile.getTileFromPosition(game,1));
        service.buyProperty("PilsoPoly_1","Alice","Delhaize 365");

        service.takeMortgage("PilsoPoly_1","Alice","Delhaize 365");
        assertTrue(service.findBoughtPropertyByOwner("Delhaize 365","Alice",game).isMortgage());
    }

    @Test
    public void takeMortgageTestEdgeCases(){
        game.getSpecificPlayer("Alice").setCurrentTile(Tile.getTileFromPosition(game,1));
        assertThrows(IllegalArgumentException.class, () -> {
            service.takeMortgage("PilsoPoly_1","Alice","Delhaize 365");
        });

        service.buyProperty("PilsoPoly_1","Alice","Delhaize 365");
        service.takeMortgage("PilsoPoly_1","Alice","Delhaize 365");

        assertThrows(IllegalStateException.class, () -> {
            service.takeMortgage("PilsoPoly_1","Alice","Delhaize 365");
        });
    }

    @Test
    public void settleMortgageEdgeCases(){
        game.getSpecificPlayer("Alice").setCurrentTile(Tile.getTileFromPosition(game,1));
        assertThrows(IllegalArgumentException.class, () -> {
            service.settleMortgage("PilsoPoly_1","Alice","Delhaize 365");
        });

        service.buyProperty("PilsoPoly_1","Alice","Delhaize 365");
        service.takeMortgage("PilsoPoly_1","Alice","Delhaize 365");
        service.settleMortgage("PilsoPoly_1","Alice","Delhaize 365");

        assertThrows(IllegalStateException.class, () -> {
            service.settleMortgage("PilsoPoly_1","Alice","Delhaize 365");
        });
    }

    @Test
    public void settleMortgage() {
        game.getSpecificPlayer("Alice").setCurrentTile(Tile.getTileFromPosition(game,1));
        service.buyProperty("PilsoPoly_1", "Alice", "Delhaize 365");
        service.takeMortgage("PilsoPoly_1", "Alice", "Delhaize 365");
        service.settleMortgage("PilsoPoly_1", "Alice", "Delhaize 365");
        assertFalse(service.findBoughtPropertyByOwner("Delhaize 365","Alice",game).isMortgage());

    }
}
