package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayer() {
        Player testPlayer = new Player("Sibren", "Beer");

        assertEquals("Sibren", testPlayer.getName());
        assertEquals("Beer", testPlayer.getIcon());
    }

    @Test
    void testBankruptcy() {
        Player testPlayer = new Player("Sibren", "Beer");

        testPlayer.setBankrupt();

        assertTrue(testPlayer.isBankrupt());
    }

    @Test
    void buyProperty() {
        Property testProperty = new Street("Indiana Avenue", 23, "street", 3, "RED", 100, 300, 750, 925, 1100, 150, 20, 120, 240);
        PlayerProperty testPlayerProperty = new PlayerProperty(testProperty, false, 0, 0);
        Player testPlayer = new Player("Sibren", "Beer");

        testPlayer.addProperty(testPlayerProperty);

        assertEquals(List.of(testPlayerProperty), testPlayer.getProperties());

    }

    @Test
    void removeMoney() {
        Player testPlayer = new Player("Sibren", "Beer");

        testPlayer.removeMoney(500);

        assertEquals(1000, testPlayer.getMoney());
    }

    @Test
    void fine() {
        Player testPlayer = new Player("Sibren", "Beer");
        testPlayer.setJailed(true);

        testPlayer.fine();

        assertEquals(1450, testPlayer.getMoney());
        assertFalse(testPlayer.isJailed());
    }

    @Test
    void free() {
        Player testPlayer = new Player("Sibren", "Beer");
        testPlayer.addGetOutOfJailFreeCard();
        testPlayer.setJailed(true);

        testPlayer.free();

        assertEquals(0, testPlayer.getGetOutOfJailFreeCards());
        assertFalse(testPlayer.isJailed());
    }

    @Test
    void testTaxSystem() {
        Player testPlayer = new Player("Sibren", "Beer");

        testPlayer.setTaxSystem("ESTIMATE");
        assertEquals("ESTIMATE", testPlayer.getTaxSystem());

        testPlayer.setTaxSystem("COMPUTE");
        assertEquals("COMPUTE", testPlayer.getTaxSystem());
    }

    @Test
    void setCurrentTile() {
        Player testPlayer = new Player("Sibren", "Beer");
        Tile jail = new Tile("Jail", 10, "Jail", "In jail", "jailed");

        testPlayer.setCurrentTile(jail);

        assertEquals("Jail", testPlayer.getCurrentTile());
    }

    @Test
    void addTurnInJail() {
        Player testPlayer = new Player("Sibren", "Beer");
        testPlayer.addTurnInJail();
        assertEquals(1,testPlayer.getTurnsInJail());
    }

    @Test
    void addDoubleThrow() {
        Player testPlayer = new Player("Sibren", "Beer");
        testPlayer.addDoubleThrow();
        assertEquals(1,testPlayer.getAmountOfDoubleThrows());
    }
}