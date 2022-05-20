package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static be.howest.ti.monopoly.logic.implementation.tiles.AllGameTiles.CHANCE;
import static be.howest.ti.monopoly.logic.implementation.tiles.AllGameTiles.DRAW_A_CARD;
import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    int gameMapSize = 5;
    Game testGame = new Game(4, "PilsoPoly", gameMapSize);

    Player Lukas;
    Player Niels;
    Player Sibren;
    Player Robin;

    @BeforeEach
    void addPlayers(){
        testGame.addPlayer("Sibren", "icon");
        testGame.addPlayer("Robin", "icon");
        testGame.addPlayer("Lukas", "icon");
        testGame.addPlayer("Niels", "icon");
        Lukas = testGame.getSpecificPlayer("Lukas");
        Niels = testGame.getSpecificPlayer("Niels");
        Sibren = testGame.getSpecificPlayer("Sibren");
        Robin = testGame.getSpecificPlayer("Robin");
    }

    @Test
    void testGoTile() {
        Tile goTile = new Tile("Go", 0, "Go", "Passes go", "go");
        assertEquals("Go", goTile.getName());
        assertEquals(0, goTile.getPosition());
    }

    @Test
    void testStreetTile() {
        Street indiana = new Street("Indiana Avenue", 23, "street", 3,  "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
        assertEquals("Indiana Avenue", indiana.getName());
        assertEquals(23, indiana.getPosition());
    }

    @Test
    void testRailroadTile() {
        Railroad readingRR = new Railroad("Reading RR", 5, "railroad", 4, "BLACK", 25,100,200);
        assertEquals("Reading RR", readingRR.getName());
        assertEquals(5, readingRR.getPosition());
    }

    @Test
    void checkPropertyGetters(){
        Property property = new Street("Indiana Avenue", 23, "street", 3,  "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
        assertEquals(20, property.getRent());
        assertEquals(240, property.getCost());
    }

    @Test
    void checkDescriptionrent(){
        Property property = new Street("Indiana Avenue", 23, "street", 3,  "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
        property.setBought(true);
        assertEquals("should pay rent", property.getDescription());
        assertEquals("rent", property.getActionType());
    }

    @Test
    void checkDescriptionbuy(){
        Property property = new Street("Indiana Avenue", 23, "street", 3,  "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
        assertEquals("can buy this property in direct sale", property.getDescription());
        assertEquals("buy",property.getActionType());
    }

    @Test
    void checkDescriptionMortagae(){
        Property property = new Street("Indiana Avenue", 23, "street", 3,  "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
        property.setBought(true);
        property.setMortgaged(true);
        assertEquals("no need to pay rent, the tile is mortgaged", property.getDescription());
        assertEquals("mortgage", property.getActionType());
    }

    @Test
    void propertyToString(){
        Property property = new Street("Indiana Avenue", 23, "street", 3,  "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
        assertEquals("Property{color='RED'}", property.toString());
    }

    @Test
    void testEquals(){
        Property property = new Street("Indiana Avenue", 23, "street", 3,  "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
        Property propertyTwo = new Street("Indiana Avenue", 23, "street", 3,  "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
        assertEquals(propertyTwo, property);
    }

    @Test
    void testEqualsTiles(){
        Tile tile = new Tile("Go", 0, "Go", "passes 'GO!' and receives 200 for it", "go");
        Tile tile1 = new Tile("Go", 0, "Go", "passes 'GO!' and receives 200 for it", "go");
        assertEquals(tile, tile1);
    }

    @Test
    void toStringTile(){
        Tile tile = new Tile("Go", 0, "Go", "passes 'GO!' and receives 200 for it", "go");
        assertEquals("Go", tile.toString());
    }

    @Test
    void takeTileAction(){
        System.out.println(testGame.getCurrentPlayer());
        Tile jail = new Tile("Jail", 10, "Jail", "jailed", "jail");
        Tile.takeTileAction(jail, Sibren, testGame);
        assertTrue(Sibren.isJailed());
        Tile tax = new Tile("Luxury Tax", 38, "Luxury Tax", "Pay taxes", "luxtax");
        Tile.takeTileAction(tax, Sibren, testGame);
        assertEquals(1300, Sibren.getMoney());
        Tile incomTax = new Tile("Tax Income", 4, "Tax Income", "Pay taxes", "incometax");
        Tile.takeTileAction(incomTax, Sibren,testGame);
        assertEquals(1100, Sibren.getMoney());
    }

    @Test
    void getRentDescription(){
        Utility uitlity = new Utility("Electric Company", 12, "utility", 2, "WHITE", 75, 150);
        assertEquals("4 or 10 times the dice roll", uitlity.getRentDescription());
    }

    @Test
    void testEqualsUtility(){
        Utility uitlity = new Utility("Electric Company", 12, "utility", 2, "WHITE", 75, 150);
        Utility uitlityTwo = new Utility("Electric Company", 12, "utility", 2, "WHITE", 75, 150);
        assertEquals(uitlity, uitlityTwo);
    }

    @Test
    void computeRentUtility(){
        Utility uitlity = new Utility("Electric Company", 12, "utility", 2, "WHITE", 75, 150);
        Utility utilityTwo = new Utility("Water Works", 28, "utility", 2, Colors.WHITE.toString(), 75, 150);
        PlayerProperty playerProperty = new PlayerProperty(utilityTwo);
        PlayerProperty playerPropertyTwo = new PlayerProperty(uitlity);
        Sibren.addProperty(playerProperty);
        Sibren.addProperty(playerPropertyTwo);
        Robin.payRent(playerProperty, testGame, Sibren);
        assertEquals(1510, Robin.getMoney());

    }
}

