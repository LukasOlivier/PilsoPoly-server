package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnTest {

    @Test
    void getPlayer() {
        Player player = new Player("Sibren", "beer");
        Turn turn = new Turn(player.getName(), "DEFAULT");
        assertEquals("Sibren", turn.getPlayer());
    }

    @Test
    void getType() {
        Player player = new Player("Sibren", "beer");
        Turn turn = new Turn(player.getName(), "DEFAULT");
        assertEquals("DEFAULT", turn.getType());
    }


}