package be.howest.ti.monopoly.logic.implementation.tiles;
import java.util.List;

public class AllGameTiles {

    private AllGameTiles() {
        throw new IllegalStateException("game tile class");
    }


    public static final String STREET = "street";
    public static final String RAILROAD = "railroad";

    public static final String COMMUNITY_CHEST = "community chest";
    public static final String COMMUNITY = "community";
    public static final String DRAW_A_CARD = "Draw a card";
    public static final String CHANCE = "chance";

    protected static final List<Tile> gameTiles = createGameTiles();

    public static List<Tile> createGameTiles() {
        return List.of(
                new Tile("Go", 0, "Go", "passes 'GO!' and receives 200 for it", "go"),
                new Street("Delhaize 365", 1, STREET, 2, Colors.PURPLE.toString(), new StreetHouseRent(10,30,90,160,250), 50, 2, 30, 60),
                new Tile("Community Chest I", 2, COMMUNITY_CHEST, DRAW_A_CARD, COMMUNITY),
                new Street("Cara", 3, STREET, 2, Colors.PURPLE.toString(), new StreetHouseRent(20, 60, 180, 320, 450), 50, 4, 30, 60),
                new Tile("Tax Income", 4, "Tax Income", "Pay taxes", "incometax"),
                new Railroad("Brewery Artois", 5, RAILROAD, 4, Colors.BLACK.toString(), 25, 100, 200),
                new Street("Heineken", 6, STREET, 3, Colors.LIGHTBLUE.toString(), new StreetHouseRent(30, 90, 270, 400, 550), 50, 6, 50, 100),
                new Tile("Chance I", 7, CHANCE, DRAW_A_CARD, CHANCE),
                new Street("Sparta Pils", 8, STREET, 3, Colors.LIGHTBLUE.toString(), new StreetHouseRent(30, 90, 270, 400, 550), 50, 6, 50, 100),
                new Street("Schuttenbräu", 9, STREET, 3, Colors.LIGHTBLUE.toString(), new StreetHouseRent(40, 100, 300, 450, 600), 50, 8, 50, 120),
                new Tile("Jail", 10, "Jail", "Just visiting", "visiting"),
                new Street("Primus", 11, STREET, 3, Colors.VIOLET.toString(), new StreetHouseRent(50, 150, 450, 625, 750), 100, 10, 70, 140),
                new Utility("Electric Company", 12, "utility", 2, "WHITE", 75, 150),
                new Street("Bavik", 13, STREET, 3, "VIOLET", new StreetHouseRent(50, 150, 450, 625, 750), 100, 10, 70, 140),
                new Street("Bockor", 14, STREET, 3, "VIOLET", new StreetHouseRent(60, 180, 500, 700, 900), 100, 12, 80, 160),
                new Railroad("Brewery Rodenbach", 15, RAILROAD, 4, Colors.BLACK.toString(), 25, 100, 200),
                new Street("Stella", 16, STREET, 3, Colors.ORANGE.toString(), new StreetHouseRent(70, 200, 550, 750, 950), 100, 14, 90, 180),
                new Tile("Community Chest II", 17, COMMUNITY_CHEST, DRAW_A_CARD, COMMUNITY),
                new Street("Jupiler", 18, STREET, 3, Colors.ORANGE.toString(), new StreetHouseRent(70, 200, 550, 750, 950), 100, 14, 90, 180),
                new Street("Maes", 19, STREET, 3, Colors.ORANGE.toString(), new StreetHouseRent(80, 220, 600, 800, 1000), 100, 16, 100, 200),
                new Tile("Free Parking", 20, "Free Parking", "Nothing specials happens here", "parking"),
                new Street("Rodenbach", 21, STREET, 3, Colors.RED.toString(), new StreetHouseRent(90, 250, 700, 875, 1050), 150, 18, 110, 220),
                new Tile("Chance II", 22, CHANCE, DRAW_A_CARD, CHANCE),
                new Street("Kriek", 23, STREET, 3, Colors.RED.toString(), new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240),
                new Street("Kasteel Rouge", 24, STREET, 3, Colors.RED.toString(), new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240),
                new Railroad("Brewery Lupulus", 25, RAILROAD, 4, Colors.BLACK.toString(), 25, 100, 200),
                new Street("Duvel", 26, STREET, 3, Colors.YELLOW.toString(), new StreetHouseRent(110, 330, 800, 975, 1150), 150, 22, 130, 260),
                new Street("Omer", 27, STREET, 3, Colors.YELLOW.toString(), new StreetHouseRent(110, 330, 800, 975, 1150), 150, 22, 130, 260),
                new Utility("Water Works", 28, "utility", 2, Colors.WHITE.toString(), 75, 150),
                new Street("Westmalle", 29, STREET, 3, Colors.YELLOW.toString(), new StreetHouseRent(120, 360, 850, 1025, 1200), 150, 24, 280, 280),
                new Tile("Go to Jail", 30, "Go to Jail", "has to go to jail", "jail"),
                new Street("Brugse zot", 31, STREET, 3, Colors.DARKGREEN.toString(), new StreetHouseRent(130, 390, 900, 1100, 1275), 200, 26, 150, 300),
                new Street("Chimay", 32, STREET, 3, Colors.DARKGREEN.toString(), new StreetHouseRent(130, 390, 900, 1100, 1275), 200, 26, 150, 300),
                new Tile("Community Chest III", 33, COMMUNITY_CHEST, DRAW_A_CARD, COMMUNITY),
                new Street("Westvleteren", 34, STREET, 3, Colors.DARKGREEN.toString(), new StreetHouseRent(150, 450, 1000, 1200, 1400), 200, 28, 160, 320),
                new Railroad("Brewery Omer Vander Ghinste", 35, RAILROAD, 4, Colors.BLACK.toString(), 25, 100, 200),
                new Tile("Chance III", 36, CHANCE, DRAW_A_CARD, CHANCE),
                new Street("Cornet", 37, STREET, 2, Colors.DARKBLUE.toString(), new StreetHouseRent(175, 500, 1100, 1300, 1500), 200, 35, 175, 350),
                new Tile("Luxury Tax", 38, "Luxury Tax", "Pay taxes", "luxtax"),
                new Street("Bush12", 39, STREET, 3, Colors.DARKBLUE.toString(), new StreetHouseRent(200, 600, 1400, 1700, 2000), 200, 50, 200, 400)
        );
    }
}
