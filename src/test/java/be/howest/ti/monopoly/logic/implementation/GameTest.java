package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.Tiles.Street;
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
        testGame.addPlayer("Sibren", "icon");
        testGame.addPlayer("Robin", "icon");
        testGame.addPlayer("Lukas", "icon");
        testGame.getSpecificPlayer("Sibren").setBankrupt();
        testGame.getSpecificPlayer("Lukas").setBankrupt();
        testGame.isEveryoneBankrupt();
        assertFalse(testGame.getSpecificPlayer("Robin").isBankrupt());
        assertTrue(testGame.getSpecificPlayer("Lukas").isBankrupt());
        assertEquals("Robin" ,testGame.getWinner());
    }

    @Test
    void ccCardTest(){
        testGame.addPlayer("Lukas", "icon");
        Game.createCommunityCards();
        System.out.println(testGame.getSpecificPlayer("Lukas").getMoney());
        System.out.println(testGame.getSpecificPlayer("Lukas").getCurrentTile());
        Move.makeMove(testGame.getSpecificPlayer("Lukas"), 2, testGame);
        System.out.println(testGame.getSpecificPlayer("Lukas").getMoney());
        System.out.println(testGame.getSpecificPlayer("Lukas").getCurrentTile());
        Move.makeMove(testGame.getSpecificPlayer("Lukas"), 5, testGame);
        System.out.println(testGame.getSpecificPlayer("Lukas").getCurrentTile());
        System.out.println(testGame.getSpecificPlayer("Lukas").getMoney());
    }

    @Test
    void ccGetOutOfJail(){
        testGame.addPlayer("Lukas", "icon");
        Game.createCommunityCards();
        assertEquals(0, testGame.getSpecificPlayer("Lukas").getGetOutOfJailFreeCards());
        testGame.doCommunityCard(12, "Lukas");
        assertEquals(1, testGame.getSpecificPlayer("Lukas").getGetOutOfJailFreeCards());
    }

    @Test
    void ccGoToJail(){
        testGame.addPlayer("Niels", "icon");
        Game.createCommunityCards();
        testGame.doCommunityCard(10, "Niels");
        assertTrue(testGame.getSpecificPlayer("Niels").isJailed());
        assertEquals(1500, testGame.getSpecificPlayer("Niels").getMoney());
    }

    @Test
    void ccGoToJailPassingGo(){
        testGame.addPlayer("Sibren", "icon");
        testGame.getSpecificPlayer("Sibren").setCurrentTile(new Street("Park Place", 37, "street", 2, "DARKBLUE", 175, 500, 1100, 1300, 1500, 200, 35, 175, 350));
        Game.createCommunityCards();
        testGame.doCommunityCard(10, "Sibren");
        assertTrue(testGame.getSpecificPlayer("Sibren").isJailed());
        assertEquals(1500, testGame.getSpecificPlayer("Sibren").getMoney());
    }

    @Test
    void ccReceiveFromEveryone(){
        testGame.addPlayer("Sibren", "icon");
        Game.createCommunityCards();
        testGame.doCommunityCard(13, "Sibren");
        assertTrue(testGame.getSpecificPlayer("Sibren").isJailed());
        assertEquals(1500, testGame.getSpecificPlayer("Sibren").getMoney());
    }

}