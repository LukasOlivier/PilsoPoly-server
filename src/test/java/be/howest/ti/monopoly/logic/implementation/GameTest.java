package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.Tiles.Property;
import be.howest.ti.monopoly.logic.implementation.Tiles.Street;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    int gameMapSize = 5;
    Game testGame = new Game(3, "PilsoPoly", gameMapSize);
    Property Mediterranean = new Street("Mediterranean", 1, "street", 2, "PURPLE", 10, 30, 90, 160, 250, 50, 2, 30, 60);
    Property Baltic = new Street("Baltic", 3, "street", 2, "PURPLE", 20, 60, 180, 320, 450, 50, 4, 30, 60);



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
    void ccPay(){
        testGame.addPlayer("Lukas", "icon");
        Game.createCommunityCards();

    }

    @Test
    void ccGetOutOfJail(){
        testGame.addPlayer("Lukas", "icon");
        Game.createCommunityCards();
        assertEquals(0, testGame.getSpecificPlayer("Lukas").getGetOutOfJailFreeCards());
        testGame.doCommunityCard(13, testGame.getSpecificPlayer("Lukas"));
        assertEquals(1, testGame.getSpecificPlayer("Lukas").getGetOutOfJailFreeCards());
    }

    @Test
    void ccGoToJail(){
        testGame.addPlayer("Niels", "icon");
        Game.createCommunityCards();
        testGame.doCommunityCard(11, testGame.getSpecificPlayer("Niels"));
        assertTrue(testGame.getSpecificPlayer("Niels").isJailed());
        assertEquals(1500, testGame.getSpecificPlayer("Niels").getMoney());
    }

    @Test
    void ccGoToJailPassingGo(){
        testGame.addPlayer("Sibren", "icon");
        testGame.getSpecificPlayer("Sibren").setCurrentTile(new Street("Park Place", 37, "street", 2, "DARKBLUE", 175, 500, 1100, 1300, 1500, 200, 35, 175, 350));
        Game.createCommunityCards();
        testGame.doCommunityCard(11, testGame.getSpecificPlayer("Sibren"));
        assertTrue(testGame.getSpecificPlayer("Sibren").isJailed());
        assertEquals(1500, testGame.getSpecificPlayer("Sibren").getMoney());
    }

    @Test
    void ccReceiveFromEveryone(){
        testGame.addPlayer("Sibren", "icon");
        testGame.addPlayer("Robin", "icon");
        testGame.addPlayer("Niels", "icon");
        Game.createCommunityCards();
        testGame.doCommunityCard(14, testGame.getSpecificPlayer("Sibren"));
        assertEquals(1520, testGame.getSpecificPlayer("Sibren").getMoney());
        assertEquals(1490, testGame.getSpecificPlayer("Robin").getMoney());
        assertEquals(1490, testGame.getSpecificPlayer("Niels").getMoney());
    }

    @Test
    void ccStreetRepair(){
        Game.createCommunityCards();

        List<PlayerProperty> listOfPlayerProperties = new ArrayList<>();
        PlayerProperty playerPropertyMed = new PlayerProperty(Mediterranean);
        PlayerProperty playerPropertyBal = new PlayerProperty(Baltic);
        listOfPlayerProperties.add(playerPropertyMed);
        listOfPlayerProperties.add(playerPropertyBal);

        testGame.addPlayer("Sibren", "icon");
        Player Sibren = testGame.getSpecificPlayer("Sibren");

        Sibren.setCurrentTile(Mediterranean);
        Sibren.addProperty(playerPropertyMed);

        Sibren.setCurrentTile(Baltic);
        Sibren.addProperty(playerPropertyBal);

        playerPropertyMed.addHouse(Sibren, listOfPlayerProperties);
        playerPropertyBal.addHouse(Sibren, listOfPlayerProperties);

        playerPropertyMed.addHouse(Sibren, listOfPlayerProperties);

        testGame.doCommunityCard(15, testGame.getSpecificPlayer("Sibren"));
        assertEquals(1230,Sibren.getMoney());
    }

}