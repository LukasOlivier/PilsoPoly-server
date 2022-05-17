package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.*;
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
        Property testProperty = new Street("Indiana Avenue", 23, "street", 3, "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
        PlayerProperty testPlayerProperty = new PlayerProperty(testProperty, false, 0, 0);
        Player testPlayer = new Player("Sibren", "Beer");

        testPlayer.addProperties(testPlayerProperty);

        assertEquals(List.of(testPlayerProperty), testPlayer.getProperties());

    }

    @Test
    void removeMoney() {
        Player testPlayer = new Player("Sibren", "Beer");

        testPlayer.removeMoney(500);

        assertEquals(1000, testPlayer.getMoney());
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

    @Test
    void payRentStreet(){
        Property testProperty = new Street("Indiana Avenue", 23, "street", 3, "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
        PlayerProperty testPlayerProperty = new PlayerProperty(testProperty, false, 0, 0);
        Player debtPlayer = new Player("Sibren", "Beer");
        Player player = new Player("Robin", "test");
        debtPlayer.addProperties(testPlayerProperty);
        player.payRentStreet(testPlayerProperty,testProperty,debtPlayer);
        assertEquals(1480, player.getMoney());
    }

    @Test
    void payRentRailRoad(){
        Property testProperty = new Railroad("Reading RR", 5, "railroad", 4, "BLACK", 25, 100, 200);
        PlayerProperty testPlayerProperty = new PlayerProperty(testProperty, false, 0, 0);
        Player debtPlayer = new Player("Sibren", "Beer");
        Player player = new Player("Robin", "test");
        debtPlayer.addProperties(testPlayerProperty);
        player.payRentRailRoad(debtPlayer);
        assertEquals(1475, player.getMoney());
    }

    @Test
    void payRentUtility(){
        Property testProperty =  new Utility("Electric Company", 12, "utility", 2, "WHITE", 75, 150);
        PlayerProperty testPlayerProperty = new PlayerProperty(testProperty, false, 0, 0);
        Player debtPlayer = new Player("Sibren", "Beer");
        Player player = new Player("Robin", "test");
        debtPlayer.addProperties(testPlayerProperty);
        player.payRentUtility(10,debtPlayer);
        assertEquals(1460, player.getMoney());
    }
}