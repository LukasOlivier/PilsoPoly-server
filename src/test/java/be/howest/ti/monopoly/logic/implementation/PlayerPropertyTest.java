package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerPropertyTest {

    private final PlayerProperty mediterranean = new PlayerProperty(new Street("Mediterranean", 1, "street", 2, "PURPLE", 10, 30, 90, 160, 250, 50, 2, 30, 60));

    @Test
    void testPlayerProperties() {
        assertEquals("Mediterranean", mediterranean.getProperty());
    }

    @Test
    void addHouseWhenStreetNotOwned() {
        Player player = new Player("niels", "beer");
        assertThrows(IllegalStateException.class, () -> {
            mediterranean.addHouse(player, List.of(mediterranean));
        });
    }

    @Test
    void addHouseWhenStreetOwned() {
        final PlayerProperty boardwalk = new PlayerProperty(new Street("Boardwalk", 39, "street", 2, "DARKBLUE", 200, 600, 1400, 1700, 2000, 200, 50, 200, 400));
        final PlayerProperty parkPlace = new PlayerProperty(new Street("Park Place", 37, "street", 2, "DARKBLUE", 175, 500, 1100, 1300, 1500, 200, 35, 175, 350));
        Player player = new Player("niels", "beer");
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        parkPlace.addHouse(player, List.of(boardwalk, parkPlace));
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        parkPlace.addHouse(player, List.of(boardwalk, parkPlace));
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        parkPlace.addHouse(player, List.of(boardwalk, parkPlace));
        assertEquals(3, boardwalk.getHouseCount());
    }

    @Test
    void addHouseWithIncorrectHouseCount() {
        final PlayerProperty boardwalk = new PlayerProperty(new Street("Boardwalk", 39, "street", 2, "DARKBLUE", 200, 600, 1400, 1700, 2000, 200, 50, 200, 400));
        final PlayerProperty parkPlace = new PlayerProperty(new Street("Park Place", 37, "street", 2, "DARKBLUE", 175, 500, 1100, 1300, 1500, 200, 35, 175, 350));
        Player player = new Player("niels", "beer");
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        assertThrows(IllegalStateException.class, () -> {
            boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        });
    }

    @Test
    void sellHouseWithIncorrectHouseCount() {
        final PlayerProperty boardwalk = new PlayerProperty(new Street("Boardwalk", 39, "street", 2, "DARKBLUE", 200, 600, 1400, 1700, 2000, 200, 50, 200, 400));
        final PlayerProperty parkPlace = new PlayerProperty(new Street("Park Place", 37, "street", 2, "DARKBLUE", 175, 500, 1100, 1300, 1500, 200, 35, 175, 350));
        Player player = new Player("niels", "beer");

        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        parkPlace.addHouse(player, List.of(boardwalk, parkPlace));
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        parkPlace.addHouse(player, List.of(boardwalk, parkPlace));

        boardwalk.sellHouse(player, List.of(boardwalk, parkPlace));
        assertThrows(IllegalStateException.class, () -> {
            boardwalk.sellHouse(player, List.of(boardwalk, parkPlace));
        });
    }

    @Test
    void seeIfMoneyIsRemoved() {
        final PlayerProperty boardwalk = new PlayerProperty(new Street("Boardwalk", 39, "street", 2, "DARKBLUE", 200, 600, 1400, 1700, 2000, 200, 50, 200, 400));
        final PlayerProperty parkPlace = new PlayerProperty(new Street("Park Place", 37, "street", 2, "DARKBLUE", 175, 500, 1100, 1300, 1500, 200, 35, 175, 350));
        Player player = new Player("niels", "beer");
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        assertEquals(1300, player.getMoney());
    }

    @Test
    void seeIfMoneyIsAdded() {
        final PlayerProperty boardwalk = new PlayerProperty(new Street("Boardwalk", 39, "street", 2, "DARKBLUE", 200, 600, 1400, 1700, 2000, 200, 50, 200, 400));
        final PlayerProperty parkPlace = new PlayerProperty(new Street("Park Place", 37, "street", 2, "DARKBLUE", 175, 500, 1100, 1300, 1500, 200, 35, 175, 350));
        Player player = new Player("niels", "beer");
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        boardwalk.sellHouse(player, List.of(boardwalk, parkPlace));
        assertEquals(1500, player.getMoney());
    }
}