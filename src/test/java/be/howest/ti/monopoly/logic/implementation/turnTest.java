package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class turnTest {

    @Test
    void testTurn(){
        Move testMove = new Move("Med", "you can buy this propertie", "buy");
        List<Integer> roll = new ArrayList<>();
        roll.add(1);
        roll.add(1);
        Turn testTurn = new Turn(roll, "Robin", "buy", testMove);
        assertEquals("buy", testTurn.getType());
    }

}