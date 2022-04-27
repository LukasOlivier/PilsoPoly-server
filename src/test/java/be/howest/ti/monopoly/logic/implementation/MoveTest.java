package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @Test
    void testMove(){
        Move testMove = new Move("Med", "you can buy this propertie", "buy");
        assertEquals("buy", testMove.getActionType());
    }

}