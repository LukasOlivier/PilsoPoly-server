package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.tiles.*;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.StreetHouseRent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    int gameMapSize = 5;
    Game testGame = new Game(3, "PilsoPoly", gameMapSize);

    @Test
    void createGame(){
        assertEquals("PilsoPoly_6", testGame.getId());
        assertEquals(3, testGame.getNumberOfPlayers());
        assertFalse(testGame.isStarted());
    }

    @Test
    void createGameWithWrongPrefix(){
        assertThrows(IllegalArgumentException.class, () -> new Game(4, "WrongPrefix", gameMapSize));
    }

    @Test
    void createGameWithIncorrectNumberOfPlayers(){
        assertThrows(IllegalArgumentException.class, () -> new Game(10, "PilsoPoly", gameMapSize));
        assertThrows(IllegalArgumentException.class, () -> new Game(-2, "PilsoPoly", gameMapSize));
    }
    @Test
    void joinGame(){
        testGame.addPlayer("Sibren", "icon");
        testGame.addPlayer("Robin", "icon");
        assertEquals(2,testGame.getPlayers().size());
        assertThrows(IllegalArgumentException.class, () -> testGame.addPlayer("Sibren", "icon"));
    }

    @Test
    void findPlayer(){
        testGame.addPlayer("Lukas", "icon");
        assertEquals("Lukas",testGame.getSpecificPlayer("Lukas").getName());
        assertThrows(MonopolyResourceNotFoundException.class, () -> testGame.getSpecificPlayer("Houdini"));
    }

    @Test
    void autoStartGame(){
        testGame.addPlayer("Sibren", "Icon");
        assertFalse(testGame.isStarted());
        testGame.addPlayer("Lukas", "Icon");
        testGame.addPlayer("Niels", "Icon");
        assertTrue(testGame.isStarted());
    }

    @Test
    void bankruptTest(){
        Property property = new Street("Delhaize 365", 1, 2, Colors.BROWN.toString(), new StreetHouseRent(10,30,90,160,250), 50, 2, 30, 60);
        testGame.addPlayer("Sibren", "icon");
        testGame.addPlayer("Robin", "icon");
        testGame.addPlayer("Lukas", "icon");
        testGame.getSpecificPlayer(testGame.getSpecificPlayer("Sibren").getName()).addProperty(new PlayerProperty(property));
        Player Sibren = testGame.getSpecificPlayer("Sibren");
        Sibren.setBankrupt();
        testGame.getSpecificPlayer(testGame.getSpecificPlayer("Lukas").getName()).setBankrupt();
        testGame.isEveryoneBankrupt();
        assertFalse(testGame.getSpecificPlayer("Robin").isBankrupt());
        assertEquals("Robin" ,testGame.getWinner());
    }

}