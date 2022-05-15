package be.howest.ti.monopoly.logic.implementation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveTest {
    Game testGame = new Game(2,"PilsoPoly", 0);
    Player alice = new Player("Alice","dummy");
    Player bob = new Player("Bob","dummy");

    @Test
    void testDiceRoll() {
        List<Integer> diceRollResult = Dice.rollDice();
    }
}
