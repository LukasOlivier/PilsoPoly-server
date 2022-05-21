package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SpecificGameInfoTest {
    @Test
    void testTaxSystem() {
        Player testPlayer = new Player("Sibren", "Beer");

        testPlayer.setTaxSystem("ESTIMATE");
        assertEquals("ESTIMATE", testPlayer.getTaxSystem());

        testPlayer.setTaxSystem("COMPUTE");
        assertEquals("COMPUTE", testPlayer.getTaxSystem());
    }
}
