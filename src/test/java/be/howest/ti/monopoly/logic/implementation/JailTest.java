package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JailTest {
    @Test
    void testJailed() {
        Game testGame = new Game(2, "PilsoPoly", 0);
        Player alice = new Player("Alice", "dummy");
        testGame.addPlayer("Alice", "dummy");

        alice.setJailed(true);

        assertTrue(alice.isJailed());
    }

    @Test
    void testJailedByDoubleThrows() {
        Game testGame = new Game(2, "PilsoPoly", 0);
        Player alice = new Player("Alice", "dummy");
        testGame.addPlayer("Alice", "dummy");
        testGame.addPlayer("Bob", "dummy");
        testGame.addTurn(new Turn(alice.getName(), "DEFAULT"));
        Dice diceRollDouble = new Dice(2,2);

        Move.makeMove(alice, Move.calculatePlacesToMove(diceRollDouble)); //Tax
        diceRollDouble.checkIfRolledDouble(testGame, alice);
        Move.makeMove(alice, Move.calculatePlacesToMove(diceRollDouble)); //Baltic
        diceRollDouble.checkIfRolledDouble(testGame, alice);
        alice.addProperties( new PlayerProperty((Property) alice.currentTile));
        Move.makeMove(alice, Move.calculatePlacesToMove(diceRollDouble)); //Electric Company
        diceRollDouble.checkIfRolledDouble(testGame, alice);
        Jail.checkIfJailedByDoubleThrow(alice,testGame);

        assertEquals("Jail", alice.getCurrentTile());
        assertTrue(alice.isJailed());
    }

    @Test
    void testFreeByDoubleThrows() {
        Game testGame = new Game(2, "PilsoPoly", 0);
        Player alice = new Player("Alice", "dummy");
        testGame.addPlayer("Alice", "dummy");
        Dice diceRollDouble = new Dice(1,1);
        alice.setJailed(true);
        alice.setCurrentTile(Tile.getTileFromPosition(10)); //Jail tile

        diceRollDouble.checkIfRolledDouble(testGame,alice);
        Move.makeMove(alice, Move.calculatePlacesToMove(diceRollDouble));

        assertFalse(alice.isJailed());
        assertEquals("Electric Company",alice.getCurrentTile());
    }

    @Test
    void testNotFreeByDiceRoll() {
        Game testGame = new Game(2, "PilsoPoly", 0);
        Player alice = new Player("Alice", "dummy");
        testGame.addPlayer("Alice", "dummy");
        Dice diceRoll = new Dice();
        alice.setJailed(true);
        alice.setCurrentTile(Tile.getTileFromPosition(10)); //Jail tile

        diceRoll.checkIfRolledDouble(testGame,alice);
        Jail.checkIfFreeByWaitingTurns(alice);
        Move.makeMove(alice, Move.calculatePlacesToMove(diceRoll));

        assertTrue(alice.isJailed());
        assertEquals("Jail",alice.getCurrentTile());
    }

    @Test
    void fine() {
        Player testPlayer = new Player("Sibren", "Beer");
        testPlayer.setJailed(true);

        testPlayer.fine();

        assertEquals(1450, testPlayer.getMoney());
        assertFalse(testPlayer.isJailed());
    }


    @Test
    void free() {
        Player testPlayer = new Player("Sibren", "Beer");
        testPlayer.addGetOutOfJailFreeCard();
        testPlayer.setJailed(true);

        testPlayer.free();

        assertEquals(0, testPlayer.getGetOutOfJailFreeCards());
        assertFalse(testPlayer.isJailed());
    }
    @Test
    void notEnoughGetOutOfJailFreeCards() {
        Player testPlayer = new Player("Sibren", "Beer");
        testPlayer.setJailed(true);

        assertThrows(IllegalStateException.class, testPlayer::free);
        assertTrue(testPlayer.isJailed());
    }
}
