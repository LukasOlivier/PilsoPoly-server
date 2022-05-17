package be.howest.ti.monopoly.logic.implementation;
import be.howest.ti.monopoly.logic.implementation.communityandchance.specific_cards.*;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CommunityAndChanceCardTests {

    int gameMapSize = 5;
    Game testGame = new Game(4, "PilsoPoly", gameMapSize);

    Player Lukas;
    Player Niels;
    Player Sibren;
    Player Robin;
    Property Mediterranean = new Street("Mediterranean", 1, "street", 2, "PURPLE", 10, 30, 90, 160, 250, 50, 2, 30, 60);
    Property Baltic = new Street("Baltic", 3, "street", 2, "PURPLE", 20, 60, 180, 320, 450, 50, 4, 30, 60);

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
    void PayAndReceive(){
        new PayOrReceive("pay the bank 100 dollars", -100).cardAction(testGame, Sibren);
        assertEquals(1400, Sibren.getMoney());

        new PayOrReceive("You won 250 dollars", 200).cardAction(testGame, Niels);
        assertEquals(1700, Niels.getMoney());
        assertEquals(1500, Lukas.getMoney());
    }

    @Test
    void GetOutOfJailFreeCard(){
        Player Niels = testGame.getSpecificPlayer("Niels");

        assertEquals(0, Niels.getGetOutOfJailFreeCards());
        new GetOutOfJailFreeCard("Get out of jail for  free").cardAction(testGame, Niels);
        assertEquals(1, Niels.getGetOutOfJailFreeCards());
    }

    @Test
    void fromEveryone(){
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
        Niels.setFirstThrow();
        Lukas.setFirstThrow();
        testGame.addTurn(new Turn(Niels.getName(), "DEFAULT"));

        // Does not pass Go
        new GoToTile("Advance to Illinois Avenue. If you pass Go, collect $200", 24).cardAction(testGame, Niels);
        assertEquals("Illinois Avenue", Niels.getCurrentTile());
        assertEquals(1500, Niels.getMoney());

        // Does pass Go
        new GoToTile("Advance to St. Charles Place. If you pass Go, collect $200", 11).cardAction(testGame, Niels);
        assertEquals("Saint Charles Place", Niels.getCurrentTile());
        assertEquals(1700, Niels.getMoney());

        // Go to Go tile
        new GoToTile("Go To Go", 0).cardAction(testGame, Niels);
        assertEquals("Go", Niels.getCurrentTile());
        assertEquals(1900, Niels.getMoney());

        // Go to Jail Tile
        new GoToTile("Go To Jail",10).cardAction(testGame, Niels);
        assertEquals("Jail", Niels.getCurrentTile());
        assertEquals(1900, Niels.getMoney());
        assertTrue(Niels.isJailed());

        testGame.addTurn(new Turn(Lukas.getName(), "DEFAULT"));
        // Does not pass Go
        new GoToTile("Advance to Illinois Avenue. If you pass Go, collect $200", 24).cardAction(testGame, Lukas);
        assertEquals("Illinois Avenue", Lukas.getCurrentTile());
        assertEquals(1500, Lukas.getMoney());

        // Go To Jail with passing Go
        new GoToTile("Go to jail passing go", 10).cardAction(testGame, Lukas);
        assertTrue(Lukas.isJailed());
        assertEquals("Jail", Lukas.getCurrentTile());
        assertEquals(1500, Lukas.getMoney());
    }

    @Test
    void advanceToNearest(){
        Niels.setFirstThrow();
        Lukas.setFirstThrow();

        testGame.addTurn(new Turn(Lukas.getName(), "DEFAULT"));
        new AdvanceToNearest("Go to nearest railroad", "railroad").cardAction(testGame, Lukas);
        assertEquals("Reading RR",Lukas.getCurrentTile());

        // Advance To Nearest with passing GO
        testGame.addTurn(new Turn(Niels.getName(), "DEFAULT"));
        Move.makeMove(Niels, 35, testGame);
        System.out.println(Niels.getCurrentTile());
        System.out.println(Niels.getPreviousTile());
        new AdvanceToNearest("Go to nearest utility", "utility").cardAction(testGame, Niels);
        assertEquals("Electric Company",Niels.getCurrentTile());
        assertEquals(1700, Lukas.getMoney());
    }
}
