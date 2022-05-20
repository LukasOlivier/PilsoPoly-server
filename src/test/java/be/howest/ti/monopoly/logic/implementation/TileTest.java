package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TileTest {

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
        assertEquals();
    }
}