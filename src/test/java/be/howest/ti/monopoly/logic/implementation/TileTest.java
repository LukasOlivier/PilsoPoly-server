package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    public void testGoTile() {
        Tile goTile = new Tile("Go", 0, "Go", "Go");
        assertEquals("Go", goTile.getName());
        assertEquals(0, goTile.getPosition());
    }

    @Test
    public void testStreetTile() {
        StreetTile streetTile = new StreetTile("Indiana Avenue", 23, "street", "Indiana_Avenue", 220, 110, 18, 90, 250, 700, 875, 1050, 150, "RED", 3, "RED");
        assertEquals("Indiana Avenue", streetTile.getName());
        assertEquals(23, streetTile.getPosition());
    }

    @Test
    public void testRailroadTile() {
        RailroadTile railroadTile = new RailroadTile("Reading RR", 5, "railroad", "Reading_RR", 200, 100, 25, 4, "BLACK");
        assertEquals("Reading RR", railroadTile.getName());
        assertEquals(5, railroadTile.getPosition());
    }
}