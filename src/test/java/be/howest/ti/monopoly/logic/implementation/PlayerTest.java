package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayer(){
        Player testPlayer = new Player("Sibren", "Beer");
        assertEquals("Sibren", testPlayer.getName());
        assertEquals("Beer", testPlayer.getIcon());
    }
}