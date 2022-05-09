package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void findPlayer(){
        Game testGame = new Game();
        assertEquals("Lukas",testGame.getSpecificPlayer("Lukas").getName());
        assertThrows(MonopolyResourceNotFoundException.class, () -> testGame.getSpecificPlayer("Houdini"));
    }
}