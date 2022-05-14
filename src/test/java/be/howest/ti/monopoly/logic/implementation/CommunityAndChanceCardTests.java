package be.howest.ti.monopoly.logic.implementation;
import be.howest.ti.monopoly.logic.implementation.communityandchance.specific_cards.*;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CommunityAndChanceCardTests {

    int gameMapSize = 5;
    Game testGame = new Game(4, "PilsoPoly", gameMapSize);

    Property Mediterranean = new Street("Mediterranean", 1, "street", 2, "PURPLE", 10, 30, 90, 160, 250, 50, 2, 30, 60);
    Property Baltic = new Street("Baltic", 3, "street", 2, "PURPLE", 20, 60, 180, 320, 450, 50, 4, 30, 60);

    @Test
    void PayAndReceive(){
        testGame.addPlayer("Sibren", "icon");
        testGame.addPlayer("Robin", "icon");
        testGame.addPlayer("Lukas", "icon");
        testGame.addPlayer("Niels", "icon");
        Player Lukas = testGame.getSpecificPlayer("Lukas");
        Player Niels = testGame.getSpecificPlayer("Niels");
        Player Sibren = testGame.getSpecificPlayer("Sibren");
        Player Robin = testGame.getSpecificPlayer("Robin");

        new PayCC("pay the bank 100 dollars", 100).cardAction(testGame, Sibren);
        assertEquals(1400, Sibren.getMoney());

        new ReceiveCC("You won 250 dollars", 200).cardAction(testGame, Niels);
        assertEquals(1700, Niels.getMoney());
        assertEquals(1500, Lukas.getMoney());
    }

    @Test
    void GetOutOfJailFreeCard(){
        testGame.addPlayer("Niels", "icon");
        Player Niels = testGame.getSpecificPlayer("Niels");

        assertEquals(0, Niels.getGetOutOfJailFreeCards());
        new GetOutOfJailFreeCard("Get out of jail for  free").cardAction(testGame, Niels);
        assertEquals(1, Niels.getGetOutOfJailFreeCards());
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
    void fromEveryone(){
        testGame.addPlayer("Sibren", "icon");
        testGame.addPlayer("Robin", "icon");
        testGame.addPlayer("Lukas", "icon");
        testGame.addPlayer("Niels", "icon");
        Player Lukas = testGame.getSpecificPlayer("Lukas");
        Player Niels = testGame.getSpecificPlayer("Niels");
        Player Sibren = testGame.getSpecificPlayer("Sibren");


        new CollectOrGiveEveryPlayer("Collect $10 from everyone", 10).cardAction(testGame, Lukas);
        assertEquals(1530,Lukas.getMoney());
        assertEquals(1490, Niels.getMoney());
        new CollectOrGiveEveryPlayer("Give everyone $10", -10).cardAction(testGame, Lukas);
        assertEquals(1500, Lukas.getMoney());
        assertEquals(1500, Sibren.getMoney());
    }

    @Test
    void Repairs(){
        List<PlayerProperty> listOfPlayerProperties = new ArrayList<>();
        PlayerProperty playerPropertyMed = new PlayerProperty(Mediterranean);
        PlayerProperty playerPropertyBal = new PlayerProperty(Baltic);
        listOfPlayerProperties.add(playerPropertyMed);
        listOfPlayerProperties.add(playerPropertyBal);

        testGame.addPlayer("Sibren", "icon");
        testGame.addPlayer("Robin", "icon");
        testGame.addPlayer("Lukas", "icon");
        testGame.addPlayer("Niels", "icon");
        Player Sibren = testGame.getSpecificPlayer("Sibren");

        Sibren.setCurrentTile(Mediterranean);
        Sibren.addProperty(playerPropertyMed);

        Sibren.setCurrentTile(Baltic);
        Sibren.addProperty(playerPropertyBal);

        // Balance is down 150 after buying these houses. (1350)
        playerPropertyMed.addHouse(Sibren, listOfPlayerProperties);
        playerPropertyBal.addHouse(Sibren, listOfPlayerProperties);

        playerPropertyMed.addHouse(Sibren, listOfPlayerProperties);

        new Repairs("$40 per house. $115 per hotel", 40, 115).cardAction(testGame, Sibren);
        assertEquals(1230,Sibren.getMoney());

        new Repairs("$25 per house. $100 per hotel", 25, 100).cardAction(testGame, Sibren);
        assertEquals(1155,Sibren.getMoney());
    }

    @Test
    void GoToTile(){
        testGame.addPlayer("Sibren", "icon");
        testGame.addPlayer("Robin", "icon");
        testGame.addPlayer("Lukas", "icon");
        testGame.addPlayer("Niels", "icon");
        Player Lukas = testGame.getSpecificPlayer("Lukas");
        Player Niels = testGame.getSpecificPlayer("Niels");
        Player Sibren = testGame.getSpecificPlayer("Sibren");
        Player Robin = testGame.getSpecificPlayer("Robin");

        // Does not pass Go
        new GoToTile("Advance to Illinois Avenue. If you pass Go, collect $200", 24).cardAction(testGame, Lukas);
        assertEquals("Illinois Avenue", Lukas.getCurrentTile());
        assertEquals(1500, Lukas.getMoney());

        // Does pass Go
        System.out.println(Niels.currentTile);
        Niels.setCurrentTile(Tile.getTileFromPosition(35));
        System.out.println(Niels.currentTile);
        new GoToTile("Advance to St. Charles Place. If you pass Go, collect $200", 11).cardAction(testGame, Niels);
        System.out.println(Niels.currentTile);
        assertEquals("Saint Charles Place", Niels.getCurrentTile());
        assertEquals(1700, Niels.getMoney());
    }
}
