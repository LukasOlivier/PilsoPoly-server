package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.StreetHouseRent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnTest {

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
    void getPlayer() {
        Turn turn = new Turn(Sibren.getName(), "DEFAULT");
        assertEquals("Sibren", turn.getPlayer());
    }

    @Test
    void getType() {
        Turn turn = new Turn(Sibren.getName(), "DEFAULT");
        assertEquals("DEFAULT", turn.getType());
    }

    @Test
    void findNextPlayer() {
        Turn.findNextPlayer(testGame, Robin);
        assertEquals(Lukas.getName(), testGame.getCurrentPlayer());

        Turn.findNextPlayer(testGame, Lukas);
        assertEquals(Niels.getName(), testGame.getCurrentPlayer());

        Sibren.setBankrupt();
        Turn.findNextPlayer(testGame, Niels);
        assertEquals(Robin.getName(), testGame.getCurrentPlayer());

        Lukas.setJailed(true);
        Turn.findNextPlayer(testGame, Robin);
        assertEquals(Lukas.getName(), testGame.getCurrentPlayer());
    }

    @Test
    void testSettersAndGetters() {
        Turn turn = new Turn(Sibren.getName(), "DEFAULT");
        Dice dice = new Dice();
        turn.setRoll(dice);
        assertEquals(dice, turn.getRoll());
    }
}
