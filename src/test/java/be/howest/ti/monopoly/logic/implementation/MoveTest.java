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
        testGame.addPlayer("Alice", "icon");
        testGame.addPlayer("Bob", "icon");
        testGame.addPlayer("Carol", "icon");
        testGame.addPlayer("David", "icon");
        Alice = testGame.getSpecificPlayer("Alice");
        Bob = testGame.getSpecificPlayer("Bob");
        Carol = testGame.getSpecificPlayer("Carol");
        David = testGame.getSpecificPlayer("David");
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

        assertEquals("Carol", testGame.getCurrentPlayer());

        Alice.addDoubleThrow();
        Alice.addDoubleThrow();
        Alice.addDoubleThrow();
        Move.checkIfPlayerCanRollAgain(testGame, Alice);
        assertTrue(Alice.isJailed());

        testGame.setCurrentPlayer(David.getName());
        David.setBankrupt();
        Move.checkIfPlayerCanRollAgain(testGame, David);
        assertNotEquals(David, testGame.getCurrentPlayer());
    }

    @Test
    void testBuyBeforeNextTurn() {
        Dice diceRollResult = new Dice(2,1); //Baltic tile
        int placesToMove = Move.calculatePlacesToMove(diceRollResult);
        Move.makeMove(Alice, placesToMove, testGame);

        Move.checkIfPlayerCanRollAgain(testGame, Alice);

        assertEquals("Alice", testGame.getCurrentPlayer());
        assertFalse(testGame.isCanRoll());
    }


    @Test
    void testDoubleThrow() {
        Dice diceRollResult = new Dice(2,2);
        diceRollResult.checkIfRolledDouble(testGame,Alice);

        int placesToMove = Move.calculatePlacesToMove(diceRollResult);

        Move.makeMove(Alice, placesToMove, testGame);
        Move.checkIfPlayerCanRollAgain(testGame, Alice);

        assertEquals("Alice", testGame.getCurrentPlayer());
    }

    @Test
    void checkPassedGo() {
        Dice diceRollResult = new Dice(4,2);

        int placesToMove = Move.calculatePlacesToMove(diceRollResult);
        Move.makeMove(Alice, placesToMove, testGame);
        assertEquals("Heineken", Alice.getCurrentTileName());
        assertEquals(1500, Alice.getMoney());

        Alice.setCurrentTile(Tile.getTileFromPosition(testGame,39));
        Move.makeMove(Alice, placesToMove,testGame);

        assertEquals(1700, Alice.getMoney());

        Bob.setJailed(true);
        Bob.setCurrentTile(testGame, 38);
        Move.makeMove(Bob, placesToMove, testGame);
        assertEquals(1500, Bob.getMoney());
    }

    @Test
    void testGetter(){
        Move move = new Move("Cara","You can buy this property in direct sale","buy");
        assertEquals("buy", move.getActionType());
        assertEquals("You can buy this property in direct sale", move.getDescription());
        assertEquals("Cara", move.getTile());
    }
}
