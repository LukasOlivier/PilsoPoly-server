package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.tiles.specialtiles.GoToJailTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JailTest {

    Game testGame = new Game(2, "PilsoPoly", 0);
    Player alice;
    Player bob;

    @BeforeEach
    void createGame() {
        testGame.addPlayer("Alice", "dummy");
        testGame.addPlayer("Bob", "dummy");
        alice = testGame.getSpecificPlayer("Alice");
        bob = testGame.getSpecificPlayer("Bob");
    }

    @Test
    void testJailed() {
        alice.setJailed(true);
        assertTrue(alice.isJailed());
    }

    @Test
    void testGoToJailTile() {
        Tile goToJailTile = new GoToJailTile("Go to Jail", 30);
        goToJailTile.tileAction(testGame, alice);
        assertTrue(alice.isJailed());
    }

    @Test
    void testJailedByDoubleThrows() {
        testGame.addTurn(new Turn(alice.getName(), "DEFAULT"));
        Dice diceRollDouble = new Dice(2,2);

        Move.makeMove(alice, Move.calculatePlacesToMove(diceRollDouble), testGame); //Tax
        diceRollDouble.checkIfRolledDouble(testGame, alice);

        Move.makeMove(alice, Move.calculatePlacesToMove(diceRollDouble), testGame); //Baltic
        diceRollDouble.checkIfRolledDouble(testGame, alice);

        alice.addProperty(new PlayerProperty((Property) alice.getCurrentTile()));
        Move.makeMove(alice, Move.calculatePlacesToMove(diceRollDouble), testGame); //Electric Company
        diceRollDouble.checkIfRolledDouble(testGame, alice);
        Jail.checkIfJailedByDoubleThrow(alice,testGame);

        assertEquals("Jail", alice.getCurrentTileName());
        assertTrue(alice.isJailed());
    }

    @Test
    void testFreeByDoubleThrows() {
        Dice diceRollDouble = new Dice(1,1);
        alice.setJailed(true);
        alice.setCurrentTile(Tile.getTileFromPosition(testGame,10)); //Jail tile

        diceRollDouble.checkIfRolledDouble(testGame,alice);
        Move.makeMove(alice, Move.calculatePlacesToMove(diceRollDouble), testGame);

        assertFalse(alice.isJailed());
        assertEquals("Electric Company",alice.getCurrentTileName());
    }

    @Test
    void testNotFreeByDiceRoll() {
        Dice diceRoll = new Dice(3,1);
        alice.setJailed(true);
        alice.setCurrentTile(Tile.getTileFromPosition(testGame,10)); //Jail tile

        diceRoll.checkIfRolledDouble(testGame,alice);
        Jail.checkIfFreeByWaitingTurns(alice);

        Move.makeMove(alice, Move.calculatePlacesToMove(diceRoll), testGame);

        assertTrue(alice.isJailed());
        assertEquals("Jail",alice.getCurrentTileName());
    }

    @Test
    void fine() {
        alice.setJailed(true);
        alice.fine();

        assertEquals(1450, alice.getMoney());
        assertFalse(alice.isJailed());
    }


    @Test
    void free() {
        alice.addGetOutOfJailFreeCard();
        alice.setJailed(true);

        alice.free();

        assertEquals(0, alice.getGetOutOfJailFreeCards());
        assertFalse(alice.isJailed());
    }

    @Test
    void notEnoughGetOutOfJailFreeCards() {
        alice.setJailed(true);

        assertThrows(IllegalStateException.class, alice::free);
        assertTrue(alice.isJailed());
    }
}
