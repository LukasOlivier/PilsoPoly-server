package be.howest.ti.monopoly.logic.implementation;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.tiles.specialtiles.ChanceTile;
import be.howest.ti.monopoly.logic.implementation.tiles.specialtiles.CommunityTile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {


    @Test
    void testDiceRoll() {
        Game testGame = new Game(2, "PilsoPoly", 0);
        Player alice = new Player("Alice", "dummy");
        testGame.addPlayer("Alice", "dummy");
        testGame.addPlayer("Bob", "dummy");

        Dice diceRoll = new Dice(2,1);
        int placesToMove = Move.calculatePlacesToMove(diceRoll);
        Move.makeMove(alice, placesToMove, testGame);

        assertEquals("Cara", alice.getCurrentTileName());
    }

    @Test
    void testNextPlayerCanRoll() {
        Game testGame = new Game(2, "PilsoPoly", 0);
        Player alice = new Player("Alice", "dummy");
        testGame.addPlayer("Alice", "dummy");
        testGame.addPlayer("Bob", "dummy");


        Dice diceRollResult = new Dice(3,1); //Tax tile
        int placesToMove = Move.calculatePlacesToMove(diceRollResult);

        Move.makeMove(alice, placesToMove, testGame);
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
        CommunityTile.createCommunityCards();
        ChanceTile.createChanceCards();
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
