package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerPropertyTest {

    @Test
    void testPlayerProperties(){
        PlayerProperty testProperty = new PlayerProperty("med");
        assertEquals("med", testProperty.getProperty());
    }
}