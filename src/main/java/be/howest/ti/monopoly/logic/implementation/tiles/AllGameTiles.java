package be.howest.ti.monopoly.logic.implementation.tiles;

import java.util.List;

public class AllGameTiles {
    public static List<Tile> gameTiles = createGameTiles();


    public static List<Tile> createGameTiles() {
        return List.of(
                new Tile("Go", 0, "Go", "passes 'GO!' and receives 200 for it", "go"),
                new Street("Delhaize 365", 1, "street", 2, "PURPLE", new StreetHouseRent(10,30,90,160,250), 50, 2, 30, 60),
                new Tile("Community Chest I", 2, "community chest", "Draw a card", "community"),
                new Street("Cara", 3, "street", 2, "PURPLE", new StreetHouseRent(20, 60, 180, 320, 450), 50, 4, 30, 60),
                new Tile("Tax Income", 4, "Tax Income", "Pay taxes", "incometax"),
                new Railroad("Brewery Artois", 5, "railroad", 4, "BLACK", 25, 100, 200),
                new Street("Heineken", 6, "street", 3, "LIGHTBLUE", new StreetHouseRent(30, 90, 270, 400, 550), 50, 6, 50, 100),
                new Tile("Chance I", 7, "chance", "Draw a card", "chance"),
                new Street("Sparta Pils", 8, "street", 3, "LIGHTBLUE", new StreetHouseRent(30, 90, 270, 400, 550), 50, 6, 50, 100),
                new Street("Schuttenbräu", 9, "street", 3, "LIGHTBLUE", new StreetHouseRent(40, 100, 300, 450, 600), 50, 8, 50, 120),
                new Tile("Jail", 10, "Jail", "Just visiting", "visiting"),
                new Street("Primus", 11, "street", 3, "VIOLET", new StreetHouseRent(50, 150, 450, 625, 750), 100, 10, 70, 140),
                new Utility("Electric Company", 12, "utility", 2, "WHITE", 75, 150),
                new Street("Bavik", 13, "street", 3, "VIOLET", new StreetHouseRent(50, 150, 450, 625, 750), 100, 10, 70, 140),
                new Street("Bockor", 14, "street", 3, "VIOLET", new StreetHouseRent(60, 180, 500, 700, 900), 100, 12, 80, 160),
                new Railroad("Brewery Rodenbach", 15, "railroad", 4, "BLACK", 25, 100, 200),
                new Street("Stella", 16, "street", 3, "ORANGE", new StreetHouseRent(70, 200, 550, 750, 950), 100, 14, 90, 180),
                new Tile("Community Chest II", 17, "community chest", "Draw a card", "community"),
                new Street("Jupiler", 18, "street", 3, "ORANGE", new StreetHouseRent(70, 200, 550, 750, 950), 100, 14, 90, 180),
                new Street("Maes", 19, "street", 3, "ORANGE", new StreetHouseRent(80, 220, 600, 800, 1000), 100, 16, 100, 200),
                new Tile("Free Parking", 20, "Free Parking", "Nothing specials happens here", "parking"),
                new Street("Rodenbach", 21, "street", 3, "RED", new StreetHouseRent(90, 250, 700, 875, 1050), 150, 18, 110, 220),
                new Tile("Chance II", 22, "chance", "Draw a card", "chance"),
                new Street("Kriek", 23, "street", 3, "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240),
                new Street("Kasteel Rouge", 24, "street", 3, "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240),
                new Railroad("Brewery Lupulus", 25, "railroad", 4, "BLACK", 25, 100, 200),
                new Street("Duvel", 26, "street", 3, "YELLOW", new StreetHouseRent(110, 330, 800, 975, 1150), 150, 22, 130, 260),
                new Street("Omer", 27, "street", 3, "YELLOW", new StreetHouseRent(110, 330, 800, 975, 1150), 150, 22, 130, 260),
                new Utility("Water Works", 28, "utility", 2, "WHITE", 75, 150),
                new Street("Westmalle", 29, "street", 3, "YELLOW", new StreetHouseRent(120, 360, 850, 1025, 1200), 150, 24, 280, 280),
                new Tile("Go to Jail", 30, "Go to Jail", "has to go to jail", "jail"),
                new Street("Brugse zot", 31, "street", 3, "DARKGREEN", new StreetHouseRent(130, 390, 900, 1100, 1275), 200, 26, 150, 300),
                new Street("Chimay", 32, "street", 3, "DARKGREEN", new StreetHouseRent(130, 390, 900, 1100, 1275), 200, 26, 150, 300),
                new Tile("Community Chest III", 33, "community chest", "Draw a card", "community"),
                new Street("Westvleteren", 34, "street", 3, "DARKGREEN", new StreetHouseRent(150, 450, 1000, 1200, 1400), 200, 28, 160, 320),
                new Railroad("Brewery Omer Vander Ghinste", 35, "railroad", 4, "BLACK", 25, 100, 200),
                new Tile("Chance III", 36, "chance", "Draw a card", "chance"),
                new Street("Cornet", 37, "street", 2, "DARKBLUE", new StreetHouseRent(175, 500, 1100, 1300, 1500), 200, 35, 175, 350),
                new Tile("Luxury Tax", 38, "Luxury Tax", "Pay taxes", "luxtax"),
                new Street("Bush12", 39, "street", 3, "DARKBLUE", new StreetHouseRent(200, 600, 1400, 1700, 2000), 200, 50, 200, 400)
        );
    }
}
