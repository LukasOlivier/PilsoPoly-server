package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    public void testGoTile() {
        Tile goTile = new Tile("Go", 0, "Go");
        assertEquals("Go", goTile.getName());
        assertEquals(0, goTile.getPosition());
    }

    @Test
    public void testStreetTile() {
        Street indiana = new Street("Indiana Avenue", 23, "street", 3,  "RED", 100, 300, 750, 925, 1100, 150, 20, 120, 240);
        assertEquals("Indiana Avenue", indiana.getName());
        assertEquals(23, indiana.getPosition());
    }

    @Test
    public void testRailroadTile() {
        Railroad readingRR = new Railroad("Reading RR", 5, "railroad", 4, "BLACK", 25,100,200);
        assertEquals("Reading RR", readingRR.getName());
        assertEquals(5, readingRR.getPosition());
    }
}