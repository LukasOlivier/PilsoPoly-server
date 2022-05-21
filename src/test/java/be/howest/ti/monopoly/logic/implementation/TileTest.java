package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.properties.Railroad;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.StreetHouseRent;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.tiles.*;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.Utility;
import be.howest.ti.monopoly.logic.implementation.tiles.specialtiles.TaxTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    int gameMapSize = 5;
    Game testGame = new Game(4, "PilsoPoly", gameMapSize);

    Player Alice;
    Player Bob;
    Player Carol;
    Player David;

    @BeforeEach
    void addPlayers(){
        testGame.addPlayer("Alice", "icon");
        testGame.addPlayer("Bob", "icon");
        testGame.addPlayer("Carol", "icon");
        testGame.addPlayer("David", "icon");
        Alice = testGame.getSpecificPlayer("Alice");
        Bob = testGame.getSpecificPlayer("Bob");
        Carol = testGame.getSpecificPlayer("Carol");
        David = testGame.getSpecificPlayer("David");
    }

    @Test
    void testGoTile() {
        Tile goTile = new Tile("Go", 0, "Go", "Passes go", "go");
        assertEquals("Go", goTile.getName());
        assertEquals(0, goTile.getPosition());
    }

    @Test
    void testStreetTile() {
        Street indiana = new Street("Indiana Avenue", 23, 3,  "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
        assertEquals("Indiana Avenue", indiana.getName());
        assertEquals(23, indiana.getPosition());
    }

    @Test
    void testRailroadTile() {
        Railroad readingRR = new Railroad("Reading RR", 5);
        assertEquals("Reading RR", readingRR.getName());
        assertEquals(5, readingRR.getPosition());
    }

    @Test
    void checkPropertyGetters(){
        Property property = new Street("Indiana Avenue", 23, 3,  "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
        assertEquals(20, property.getRent());
        assertEquals(240, property.getCost());
    }

    @Test
    void checkDescriptionRent(){
        Property property = new Street("Indiana Avenue", 23, 3,  "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
        property.setBought(true);
        assertEquals("You need to pay rent to: ", property.getDescription());
        assertEquals("rent", property.getActionType());
    }

    @Test
    void checkDescriptionBuy(){
        Property property = new Street("Indiana Avenue", 23, 3,  "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
        assertEquals("You can buy this property in direct sale.", property.getDescription());
        assertEquals("buy",property.getActionType());
    }

    @Test
    void checkDescriptionMortgage(){
        Property property = new Street("Indiana Avenue", 23, 3,  "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
        property.setBought(true);
        property.setMortgaged(true);
        assertEquals("No need to pay rent, the tile is mortgaged.", property.getDescription());
        assertEquals("mortgage", property.getActionType());
    }

    @Test
    void testEquals(){
        Property property = new Street("Indiana Avenue", 23, 3,  "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
        Property propertyTwo = new Street("Indiana Avenue", 23, 3,  "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
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
        Tile jail = new Tile("Jail", 10, "Jail", "Just visiting.", "visiting");
        jail.tileAction(testGame, Carol);
        assertFalse(Carol.isJailed());
        Tile tax = new TaxTile("Luxury Tax", 38, "Luxury Tax", "luxtax");
        tax.tileAction(testGame, Carol);
        assertEquals(1300, Carol.getMoney());
        Tile incomTax = new TaxTile("Tax Income", 4, "Tax Income", "incometax");
        incomTax.tileAction(testGame, Carol);
        assertEquals(1100, Carol.getMoney());
    }

    @Test
    void getRentDescription(){
        Utility utility = new Utility("Electric Company", 12);
        assertEquals("4 or 10 times the dice roll", utility.getRentDescription());
    }

    @Test
    void testEqualsUtility(){
        Utility utility = new Utility("Electric Company", 12);
        Utility utilityTwo = new Utility("Electric Company", 12);
        assertEquals(utility, utilityTwo);
    }

    @Test
    void computeRentUtility(){
        Utility uitlity = new Utility("Electric Company", 12);
        Utility utilityTwo = new Utility("Water Works", 28);
        PlayerProperty playerProperty = new PlayerProperty(utilityTwo);
        PlayerProperty playerPropertyTwo = new PlayerProperty(uitlity);
        Carol.addProperty(playerProperty);
        Carol.addProperty(playerPropertyTwo);
        David.payRent(playerProperty, testGame, Carol);
        assertEquals(1510, David.getMoney());
    }
}

