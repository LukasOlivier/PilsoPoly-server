package be.howest.ti.monopoly.logic.implementation;

<<<<<<< HEAD
import be.howest.ti.monopoly.logic.implementation.tiles.Railroad;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
=======
import be.howest.ti.monopoly.logic.implementation.Tiles.Railroad;
import be.howest.ti.monopoly.logic.implementation.Tiles.Street;
import be.howest.ti.monopoly.logic.implementation.Tiles.StreetHouseRent;
import be.howest.ti.monopoly.logic.implementation.Tiles.Tile;
>>>>>>> 0094bbd74c51db85c0494e5a2ce8498430aacb2a
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    public void testGoTile() {
        Tile goTile = new Tile("Go", 0, "Go", "Passes go", "go");
        assertEquals("Go", goTile.getName());
        assertEquals(0, goTile.getPosition());
    }

    @Test
    public void testStreetTile() {
        Street indiana = new Street("Indiana Avenue", 23, "street", 3,  "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
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