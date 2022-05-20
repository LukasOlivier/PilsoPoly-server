package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.StreetHouseRent;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaxTest {
    @Test
    void testTaxSystem() {
        Player testPlayer = new Player("Sibren", "Beer");

        testPlayer.setTaxSystem("ESTIMATE");
        assertEquals("ESTIMATE", testPlayer.getTaxSystem());

        testPlayer.setTaxSystem("COMPUTE");
        assertEquals("COMPUTE", testPlayer.getTaxSystem());
    }

    @Test
    void testPrivateConstructor() {
        assertThrows(IllegalStateException.class, () -> {
            Tax test = new Tax();
        });
    }

    @Test
    void testIncomeTax() {
        assertEquals(200, Tax.getIncomeTax());
    }

    @Test
    void getComputeTax() {
        Player alice = new Player("Alice", "icon");

        Street parkPlace = new Street("Park Place", 37, "street", 2, "DARKBLUE", new StreetHouseRent(175, 500, 1100, 1300,1500), 200, 35, 175, 350);
        PlayerProperty parkPlaceProperty = new PlayerProperty(parkPlace);
        alice.addProperty(parkPlaceProperty);
        alice.removeMoney(parkPlace.getCost());

        Street boardwalk = new Street("Boardwalk", 39, "street", 2, "DARKBLUE", new StreetHouseRent(200, 600, 1400, 1700, 2000), 200, 50, 200, 400);
        PlayerProperty boardwalkProperty = new PlayerProperty(boardwalk);
        alice.addProperty(boardwalkProperty);
        alice.removeMoney(boardwalk.getCost());

        boardwalkProperty.addHouse(alice, List.of(boardwalkProperty, parkPlaceProperty));
        parkPlaceProperty.addHouse(alice, List.of(boardwalkProperty, parkPlaceProperty));

        assertEquals(150, Tax.getComputeTax(alice));
    }
}
