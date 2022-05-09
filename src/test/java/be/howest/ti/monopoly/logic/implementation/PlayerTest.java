package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayer(){
        Player testPlayer = new Player("Sibren", "Beer");
        assertEquals("Sibren", testPlayer.getName());
        assertEquals("Beer", testPlayer.getIcon());
    }

    @Test
    void testBankruptcy(){
        Player testPlayer = new Player("Sibren", "Beer");
        testPlayer.setBankrupt();
        assertTrue(testPlayer.isBankrupt());
    }

    @Test
    void buyProperty(){
        Player testPlayer = new Player("Sibren", "Beer");
        testPlayer.removeMoney(500);
        assertEquals(1000, testPlayer.getMoney());
    }

    @Test
    void testTax(){
        Player testPlayer = new Player("Sibren", "Beer");
        assertEquals("ESTIMATE", testPlayer.getTaxSystem());
        testPlayer.setTaxSystem("COMPUTE");
        assertEquals("COMPUTE", testPlayer.getTaxSystem());
    }



}