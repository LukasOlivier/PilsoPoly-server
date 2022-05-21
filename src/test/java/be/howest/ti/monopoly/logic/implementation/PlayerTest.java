package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.*;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.Railroad;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.StreetHouseRent;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

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
        Property testProperty = new Street("Indiana Avenue", 23, 3, "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);
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
    void setCurrentTile() {
        Player testPlayer = new Player("Sibren", "Beer");
        Tile jail = new Tile("Jail", 10, "Jail", "in jail", "jailed");
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
        Property testProperty = new Street("Indiana Avenue", 23, 3, "RED", new StreetHouseRent(100, 300, 750, 925, 1100), 150, 20, 120, 240);

        // No houses
        PlayerProperty playerProperty = new PlayerProperty(testProperty, false, 0, 0);
        Robin.addProperty(playerProperty);
        Sibren.payRent(playerProperty, testGame, Robin);
        assertEquals(1480, Sibren.getMoney());

        // 1 house
        PlayerProperty playerPropertyOneHouse = new PlayerProperty(testProperty, false, 1, 0);
        Sibren.addMoney(20);
        Robin.addProperty(playerPropertyOneHouse);
        Sibren.payRent(playerPropertyOneHouse, testGame, Robin);
        assertEquals(1400, Sibren.getMoney());

        // 2 houses
        PlayerProperty playerPropertyTwoHouses = new PlayerProperty(testProperty, false, 2, 0);
        Sibren.addMoney(100);
        Robin.addProperty(playerPropertyTwoHouses);
        Sibren.payRent(playerPropertyTwoHouses, testGame, Robin);
        assertEquals(1200, Sibren.getMoney());

        // 3 houses
        PlayerProperty playerPropertyThreeHouses = new PlayerProperty(testProperty, false, 3, 0);
        Sibren.addMoney(300);
        Robin.addProperty(playerPropertyThreeHouses);
        Sibren.payRent(playerPropertyThreeHouses, testGame, Robin);
        assertEquals(750, Sibren.getMoney());

        // 4 houses
        PlayerProperty playerPropertyFourHouses = new PlayerProperty(testProperty, false, 4, 0);
        Sibren.addMoney(750);
        Robin.addProperty(playerPropertyFourHouses);
        Sibren.payRent(playerPropertyFourHouses, testGame, Robin);
        assertEquals(575, Sibren.getMoney());

        // with Hotel
        PlayerProperty playerPropertyHotel = new PlayerProperty(testProperty, false, 0, 1);
        Sibren.addMoney(925);
        Robin.addProperty(playerPropertyHotel);
        Sibren.payRent(playerPropertyHotel, testGame, Robin);
        assertEquals(400, Sibren.getMoney());

    }

    @Test
    void payRentRailRoad(){
        Dice diceRoll = new Dice();
        Property testProperty = new Railroad("Reading RR", 5);
        PlayerProperty testPlayerProperty = new PlayerProperty(testProperty, false, 0, 0);

        testGame.setLastDiceRoll(diceRoll);
        Sibren.addProperty(testPlayerProperty);
        Robin.payRent(testPlayerProperty, testGame, Sibren);
        assertEquals(1475, Robin.getMoney());
    }

    @Test
    void payRentUtility(){
        Dice diceRoll = new Dice();
        Property testProperty =  new Utility("Electric Company", 12);
        PlayerProperty testPlayerProperty = new PlayerProperty(testProperty, false, 0, 0);

        Sibren.addProperty(testPlayerProperty);
        testGame.setLastDiceRoll(diceRoll);

        int expectedAmount = testGame.getLastDiceRollFullAmount()*4;
        Robin.payRent(testPlayerProperty, testGame, Sibren);
        assertEquals(1500-expectedAmount, Robin.getMoney());
    }
}