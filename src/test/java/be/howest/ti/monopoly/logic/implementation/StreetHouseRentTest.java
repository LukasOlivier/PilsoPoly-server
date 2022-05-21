package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.properties.StreetHouseRent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StreetHouseRentTest {
    @Test
    void testEquals() {
        StreetHouseRent streetHouseRent = new StreetHouseRent(100, 300, 750, 925, 1100);
        StreetHouseRent streetHouseRentTwo = new StreetHouseRent(100, 300, 750, 925, 1100);
        assertEquals(streetHouseRentTwo, streetHouseRent);
    }
}