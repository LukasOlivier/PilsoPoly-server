package be.howest.ti.monopoly.logic.implementation;
import be.howest.ti.monopoly.logic.implementation.Tiles.Property;
import be.howest.ti.monopoly.logic.implementation.Tiles.Tile;
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

        Dice diceRoll = new Dice(2,1);
        int placesToMove = Move.calculatePlacesToMove(diceRoll);
        Move.makeMove(alice, placesToMove);

        assertEquals("Cara", alice.getCurrentTile());
    }

    @Test
    void testNextPlayerCanRoll() {
        Game testGame = new Game(2, "PilsoPoly", 0);
        Player alice = new Player("Alice", "dummy");
        testGame.addPlayer("Alice", "dummy");
        testGame.addPlayer("Bob", "dummy");


        Dice diceRollResult = new Dice(3,1); //Tax tile
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

        Dice diceRollResult = new Dice(2,1); //Baltic tile
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

        Dice diceRollResult = new Dice(2,2);
        diceRollResult.checkIfRolledDouble(testGame,alice);

        int placesToMove = Move.calculatePlacesToMove(diceRollResult);
        Move.makeMove(alice, placesToMove);
        Move.checkIfPlayerCanRollAgain(testGame, alice);

        assertEquals("Alice", testGame.getCurrentPlayer());
    }

    @Test
    void checkPassedGo() {
        Game testGame = new Game(2, "PilsoPoly", 0);
        Player alice = new Player("Alice", "dummy");
        testGame.addPlayer("Alice", "dummy");
        Dice diceRollResult = new Dice(1,2);

        int placesToMove = Move.calculatePlacesToMove(diceRollResult);
        Move.makeMove(alice, placesToMove);

        assertEquals(1500, alice.getMoney());

        alice.setCurrentTile(Tile.getTileFromPosition(39));
        Move.makeMove(alice, placesToMove);

        assertEquals(1700, alice.getMoney());
    }


}
