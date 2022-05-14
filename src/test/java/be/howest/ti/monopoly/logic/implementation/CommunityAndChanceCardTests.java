package be.howest.ti.monopoly.logic.implementation;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CommunityAndChanceCardTests {

    int gameMapSize = 5;
    Game testGame = new Game(3, "PilsoPoly", gameMapSize);
    Property Mediterranean = new Street("Mediterranean", 1, "street", 2, "PURPLE", 10, 30, 90, 160, 250, 50, 2, 30, 60);
    Property Baltic = new Street("Baltic", 3, "street", 2, "PURPLE", 20, 60, 180, 320, 450, 50, 4, 30, 60);


    @Test
    void ccPayAndReceive(){
        testGame.addPlayer("Lukas", "icon");
        Player Lukas = testGame.getSpecificPlayer("Lukas");
        Game.createCommunityCards();
        assertEquals(1500, Lukas.getMoney());
        testGame.doCommunityCard(0, Lukas);
        assertEquals(1450, Lukas.getMoney());
        testGame.doCommunityCard(7, Lukas);
        assertEquals(1550, Lukas.getMoney());
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
