package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.Tiles.Street;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerPropertyTest {

    @Test
    void testPlayerProperties() {
        PlayerProperty testProperty = new PlayerProperty(new Street("Mediterranean", 1, "street", 2, "PURPLE", 10, 30, 90, 160, 250, 50, 2, 30, 60), "Street");
        assertEquals("Mediterranean", testProperty.getProperty());
    }

    @Test
    void addHouseWhenStreetNotOwned() {
        PlayerProperty testProperty = new PlayerProperty(new Street("Boardwalk", 39, "street", 2, "DARKBLUE", 200, 600, 1400, 1700, 2000, 200, 50, 200, 400));
        Player player = new Player("niels", "beer");
        assertThrows(IllegalStateException.class, () -> {
            testProperty.addHouse(player, List.of(testProperty));
        });
    }

    @Test
    void addHouseWhenStreetOwned() {
        PlayerProperty testProperty = new PlayerProperty(new Street("Boardwalk", 39, "street", 2, "DARKBLUE", 200, 600, 1400, 1700, 2000, 200, 50, 200, 400));
        Player player = new Player("niels", "beer");
        testProperty.addHouse(player, List.of(
                testProperty,
                new PlayerProperty(new Street("Park Place", 37, "street", 2, "DARKBLUE", 175, 500, 1100, 1300, 1500, 200, 35, 175, 350))));
        assertEquals(1, testProperty.getHouseCount());
    }

    @Test
    void addHouseWithIncorrectHouseCount() {
        PlayerProperty testProperty = new PlayerProperty(new Street("Boardwalk", 39, "street", 2, "DARKBLUE", 200, 600, 1400, 1700, 2000, 200, 50, 200, 400));
        Player player = new Player("niels", "beer");
        testProperty.addHouse(player, List.of(
                testProperty,
                new PlayerProperty(new Street("Park Place", 37, "street", 2, "DARKBLUE", 175, 500, 1100, 1300, 1500, 200, 35, 175, 350))));
        assertThrows(IllegalStateException.class, () -> {
           testProperty.addHouse(player, List.of(
                   testProperty,
                   new PlayerProperty(new Street("Park Place", 37, "street", 2, "DARKBLUE", 175, 500, 1100, 1300, 1500, 200, 35, 175, 350))));
        });
    }

    @Test
    void seeIfMoneyIsRemoved() {
        PlayerProperty testProperty = new PlayerProperty(new Street("Boardwalk", 39, "street", 2, "DARKBLUE", 200, 600, 1400, 1700, 2000, 200, 50, 200, 400));
        Player player = new Player("niels", "beer");
        testProperty.addHouse(player, List.of(
                testProperty,
                new PlayerProperty(new Street("Park Place", 37, "street", 2, "DARKBLUE", 175, 500, 1100, 1300, 1500, 200, 35, 175, 350))));
        assertEquals(1300, player.getMoney());
    }
}