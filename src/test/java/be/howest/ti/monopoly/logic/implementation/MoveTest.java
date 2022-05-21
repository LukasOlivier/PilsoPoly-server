package be.howest.ti.monopoly.logic.implementation;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    Game testGame = new Game(4, "PilsoPoly", 5);

    Player Alice;
    Player Bob;
    Player Carol;
    Player David;

    @BeforeEach
    void addPlayers(){
        testGame.addPlayer("Sibren", "icon");
        testGame.addPlayer("Robin", "icon");
        testGame.addPlayer("Lukas", "icon");
        testGame.addPlayer("Niels", "icon");
        Alice = testGame.getSpecificPlayer("Lukas");
        Bob = testGame.getSpecificPlayer("Niels");
        Carol = testGame.getSpecificPlayer("Sibren");
        David = testGame.getSpecificPlayer("Robin");
    }

    @Test
    void testDiceRoll() {
        Dice diceRoll = new Dice(2,1);
        int placesToMove = Move.calculatePlacesToMove(diceRoll);
        Move.makeMove(Alice, placesToMove, testGame);

        assertEquals("Cara", Alice.getCurrentTileName());
    }

    @Test
    void testNextPlayerCanRoll() {
        Dice diceRollResult = new Dice(3,1); //Tax tile
        int placesToMove = Move.calculatePlacesToMove(diceRollResult);

        Move.makeMove(Bob, placesToMove, testGame);
        Move.checkIfPlayerCanRollAgain(testGame, Bob);

        assertEquals("Sibren", testGame.getCurrentPlayer());
    }

    @Test
    void testBuyBeforeNextTurn() {
        Game testGame = new Game(2, "PilsoPoly", 0);
        Player alice = new Player("Alice", "dummy");
        testGame.addPlayer("Alice", "dummy");
        testGame.addPlayer("Bob", "dummy");

        Dice diceRollResult = new Dice(2,1); //Baltic tile
        int placesToMove = Move.calculatePlacesToMove(diceRollResult);
        Move.makeMove(alice, placesToMove, testGame);

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

        Move.makeMove(alice, placesToMove, testGame);
        Move.checkIfPlayerCanRollAgain(testGame, alice);

        assertEquals("Alice", testGame.getCurrentPlayer());
    }

    @Test
    void checkPassedGo() {
        Game testGame = new Game(2, "PilsoPoly", 0);
        Player alice = new Player("Alice", "dummy");
        testGame.addPlayer("Alice", "dummy");
        Dice diceRollResult = new Dice(4,2);

        int placesToMove = Move.calculatePlacesToMove(diceRollResult);
        Move.makeMove(alice, placesToMove, testGame);
        assertEquals("Heineken", alice.getCurrentTileName());
        assertEquals(1500, alice.getMoney());

        alice.setCurrentTile(Tile.getTileFromPosition(testGame,39));
        Move.makeMove(alice, placesToMove,testGame);

        assertEquals(1700, alice.getMoney());
    }

    @Test
    void testGetter(){
        Move move = new Move("Cara","You can buy this property in direct sale","buy");
        assertEquals("buy", move.getActionType());
        assertEquals("You can buy this property in direct sale", move.getDescription());
        assertEquals("Cara", move.getTile());
    }
}
