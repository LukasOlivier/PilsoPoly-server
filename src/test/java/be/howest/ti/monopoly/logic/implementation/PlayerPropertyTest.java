package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerPropertyTest {

    @Test
    void testPlayerProperties() {
        PlayerProperty testProperty = new PlayerProperty(new Street("Mediterranean", 1, "street", 2, "PURPLE", 10, 30, 90, 160, 250, 50, 2, 30, 60), "Street");
        assertEquals("Mediterranean", testProperty.getProperty());
    }
}