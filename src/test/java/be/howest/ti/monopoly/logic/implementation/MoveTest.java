package be.howest.ti.monopoly.logic.implementation;
import be.howest.ti.monopoly.logic.implementation.Tiles.Property;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {


    @Test
    void testDiceRoll() {
        Game testGame = new Game(2, "PilsoPoly", 0);
        Player alice = new Player("Alice", "dummy");
        testGame.addPlayer("Alice", "dummy");
        testGame.addPlayer("Bob", "dummy");

        List<Integer> diceRollResult = List.of(2, 1);
        int placesToMove = Move.calculatePlacesToMove(diceRollResult);
        Move.makeMove(alice, placesToMove);

        assertEquals("Baltic", alice.getCurrentTile());
    }

    @Test
    void testNextPlayerCanRoll() {
        Game testGame = new Game(2, "PilsoPoly", 0);
        Player alice = new Player("Alice", "dummy");
        testGame.addPlayer("Alice", "dummy");
        testGame.addPlayer("Bob", "dummy");

        List<Integer> diceRollResult = List.of(3, 1); //Tax tile
        int placesToMove = Move.calculatePlacesToMove(diceRollResult);
        Move.makeMove(alice, placesToMove);
        Move.checkIfPlayerCanRollAgain(testGame, alice);

        assertEquals("Bob", testGame.getCurrentPlayer());
    }

    @Test
    void testBuyBeforeNextTurn() {
        Game testGame = new Game(2, "PilsoPoly", 0);
        Player alice = new Player("Alice", "dummy");
        testGame.addPlayer("Alice", "dummy");
        testGame.addPlayer("Bob", "dummy");

        List<Integer> diceRollResult = List.of(2, 1); //Baltic tile
        int placesToMove = Move.calculatePlacesToMove(diceRollResult);
        Move.makeMove(alice, placesToMove);
        Move.checkIfPlayerCanRollAgain(testGame, alice);

        assertEquals("Alice", testGame.getCurrentPlayer());
        assertFalse(testGame.isCanRoll());
    }


    @Test
    void testDoubleThrow() {
        Game testGame = new Game(2, "PilsoPoly", 0);
        Player alice = new Player("Alice", "dummy");
        testGame.addPlayer("Alice", "dummy");
        testGame.addPlayer("Bob", "dummy");

        List<Integer> diceRollResult = List.of(2, 2);
        Dice.checkIfRolledDouble(testGame,alice, diceRollResult);

        int placesToMove = Move.calculatePlacesToMove(diceRollResult);
        Move.makeMove(alice, placesToMove);
        Move.checkIfPlayerCanRollAgain(testGame, alice);

        assertEquals("Alice", testGame.getCurrentPlayer());
    }
}
