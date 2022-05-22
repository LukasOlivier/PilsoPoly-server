package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.StreetHouseRent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PlayerPropertyTest {

    private PlayerProperty mediterranean = new PlayerProperty(new Street("Mediterranean", 1, 2, "PURPLE", new StreetHouseRent(10, 30, 90, 160, 250), 50, 2, 30, 60));
    public PlayerProperty boardwalk = new PlayerProperty(new Street("Boardwalk", 39, 2, "DARKBLUE", new StreetHouseRent(200, 600, 1400, 1700, 2000), 200, 50, 200, 400));
    public PlayerProperty parkPlace = new PlayerProperty(new Street("Park Place", 37, 2, "DARKBLUE", new StreetHouseRent(175, 500, 1100, 1300,1500), 200, 35, 175, 350));


    @AfterEach
    void createProperties() {
        PlayerProperty mediterranean = new PlayerProperty(new Street("Mediterranean", 1, 2, "PURPLE", new StreetHouseRent(10, 30, 90, 160, 250), 50, 2, 30, 60));
        PlayerProperty boardwalk = new PlayerProperty(new Street("Boardwalk", 39, 2, "DARKBLUE", new StreetHouseRent(200, 600, 1400, 1700, 2000), 200, 50, 200, 400));
        PlayerProperty parkPlace = new PlayerProperty(new Street("Park Place", 37, 2, "DARKBLUE", new StreetHouseRent(175, 500, 1100, 1300,1500), 200, 35, 175, 350));
    }

    @Test
    void testPlayerProperties() {
        PlayerProperty testProperty = new PlayerProperty(new Street("Mediterranean", 1, 2, "PURPLE", new StreetHouseRent(10, 30, 90, 160, 250), 50, 2, 30, 60));
        assertEquals("Mediterranean", testProperty.getPropertyName());
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
        Player player = new Player("niels", "beer");
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        assertThrows(IllegalStateException.class, () -> {
            boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        });
    }

    @Test
    void sellHouseWithIncorrectHouseCount() {
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
    void seeIfHousePriceIsRemoved() {
        Player player = new Player("niels", "beer");
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        assertEquals(1300, player.getMoney());
    }

    @Test
    void seeIfHousePriceIsAdded() {
        Player player = new Player("niels", "beer");
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        boardwalk.sellHouse(player, List.of(boardwalk, parkPlace));
        assertEquals(1400, player.getMoney());
    }

    @Test
    void testTakeMortgage(){
        Street street = new Street("Boardwalk", 39, 2, "DARKBLUE", new StreetHouseRent(200, 600, 1400, 1700, 2000), 200, 50, 200, 400);
        Property property = (Property) street;
        final PlayerProperty boardwalk = new PlayerProperty(street);
        Player player = new Player("niels", "beer");
        player.addProperty(boardwalk);
        boardwalk.mortgageTheProperty(property, player);
        assertEquals(1700, player.getMoney());
        assertTrue(boardwalk.isMortgage());
    }

    @Test
    void testSettleMortgage(){
        Street street = new Street("Boardwalk", 39, 2, "DARKBLUE", new StreetHouseRent(200, 600, 1400, 1700, 2000), 200, 50, 200, 400);
        final PlayerProperty boardwalk = new PlayerProperty(street);
        Player player = new Player("niels", "beer");
        player.addProperty(boardwalk);
        boardwalk.mortgageTheProperty((Property) street, player);
        assertEquals(1700, player.getMoney());
        assertTrue(boardwalk.isMortgage());
        boardwalk.settleMortgageTheProperty((Property) street, player);
        assertEquals(1500, player.getMoney());
        assertFalse(boardwalk.isMortgage());
    }

    @Test
    void addHotel() {
        Player player = new Player("niels", "beer");
        player.addMoney(1000);
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        parkPlace.addHouse(player, List.of(boardwalk, parkPlace));
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        parkPlace.addHouse(player, List.of(boardwalk, parkPlace));
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        parkPlace.addHouse(player, List.of(boardwalk, parkPlace));
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        parkPlace.addHouse(player, List.of(boardwalk, parkPlace));
        boardwalk.buyHotel(player);
        assertEquals(1, boardwalk.getHotelCount());
        assertThrows( IllegalStateException.class, () -> {
            boardwalk.buyHotel(player);
        } );
        assertEquals(0, boardwalk.getHouseCount());
    }

    @Test
     void sellHotel() {
        Player player = new Player("niels", "beer");
        player.addMoney(1000);
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        parkPlace.addHouse(player, List.of(boardwalk, parkPlace));
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        parkPlace.addHouse(player, List.of(boardwalk, parkPlace));
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        parkPlace.addHouse(player, List.of(boardwalk, parkPlace));
        boardwalk.addHouse(player, List.of(boardwalk, parkPlace));
        parkPlace.addHouse(player, List.of(boardwalk, parkPlace));
        boardwalk.buyHotel(player);
        assertEquals(1, boardwalk.getHotelCount());
        boardwalk.sellHotel(player);
        assertEquals(0, boardwalk.getHotelCount());
        assertThrows(IllegalStateException.class, () -> {
           boardwalk.sellHotel(player);
        });
        assertEquals(4, boardwalk.getHouseCount());
    }
}