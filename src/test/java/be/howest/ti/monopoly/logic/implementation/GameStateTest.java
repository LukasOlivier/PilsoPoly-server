package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @Test
    void testGameState(){
        DummyGame dummyGame = new DummyGame(2);
        dummyGame.addPlayers("Robin", "glass");
        dummyGame.addPlayers("Sibren", "beer");
        assertEquals(12, dummyGame.getAvailableHotels());
    }

}