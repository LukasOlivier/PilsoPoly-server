package be.howest.ti.monopoly.logic.implementation.tiles;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.Railroad;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.StreetHouseRent;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.Utility;
import be.howest.ti.monopoly.logic.implementation.tiles.specialtiles.ChanceTile;
import be.howest.ti.monopoly.logic.implementation.tiles.specialtiles.CommunityTile;
import be.howest.ti.monopoly.logic.implementation.tiles.specialtiles.GoToJailTile;
import be.howest.ti.monopoly.logic.implementation.tiles.specialtiles.TaxTile;

import java.util.List;

public class AllGameTiles {

    private AllGameTiles() {
        throw new IllegalStateException("game tile class");
    }

    public static List<Tile> createGameTiles() {
        return List.of(
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
    }
}
