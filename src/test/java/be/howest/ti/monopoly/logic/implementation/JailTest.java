package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.Tiles.Property;
import be.howest.ti.monopoly.logic.implementation.Tiles.Tile;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        List<Integer> diceRollDouble = List.of(2, 2);

        Move.makeMove(alice, Move.calculatePlacesToMove(diceRollDouble)); //Tax
        Dice.checkIfRolledDouble(testGame, alice,diceRollDouble);
        Move.makeMove(alice, Move.calculatePlacesToMove(diceRollDouble)); //Baltic
        Dice.checkIfRolledDouble(testGame, alice,diceRollDouble);
        alice.addProperties( new PlayerProperty((Property) alice.currentTile));
        Move.makeMove(alice, Move.calculatePlacesToMove(diceRollDouble)); //Electric Company
        Dice.checkIfRolledDouble(testGame, alice,diceRollDouble);
        Jail.checkIfJailedByDoubleThrow(alice,testGame);

        assertEquals("Jail", alice.getCurrentTile());
        assertTrue(alice.isJailed());
    }

    @Test
    void testFreeByDoubleThrows() {
        Game testGame = new Game(2, "PilsoPoly", 0);
        Player alice = new Player("Alice", "dummy");
        testGame.addPlayer("Alice", "dummy");
        List<Integer> diceRollDouble = List.of(1,1);
        alice.setJailed(true);
        alice.setCurrentTile(Tile.getTileFromPosition(Game.getGameTiles(),10)); //Jail tile

        Dice.checkIfRolledDouble(testGame,alice,diceRollDouble);
        Move.makeMove(alice, Move.calculatePlacesToMove(diceRollDouble));

        assertFalse(alice.isJailed());
        assertEquals("Electric Company",alice.getCurrentTile());
    }

    @Test
    void test) {
        Game testGame = new Game(2, "PilsoPoly", 0);
        Player alice = new Player("Alice", "dummy");
        testGame.addPlayer("Alice", "dummy");
        List<Integer> diceRollDouble = List.of(1,1);
        alice.setJailed(true);
        alice.setCurrentTile(Tile.getTileFromPosition(Game.getGameTiles(),10)); //Jail tile

        Dice.checkIfRolledDouble(testGame,alice,diceRollDouble);
        Move.makeMove(alice, Move.calculatePlacesToMove(diceRollDouble));

        assertFalse(alice.isJailed());
        assertEquals("Electric Company",alice.getCurrentTile());
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
}
