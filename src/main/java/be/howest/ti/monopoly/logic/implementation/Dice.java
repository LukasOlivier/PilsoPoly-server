package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.Tiles.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    static int minvalue = 2;
    static int maxvalue = 2;
    static int amountOfDice = 2;


    public static List<Integer> rollDice() {
        List<Integer> diceRoll = new ArrayList<>();
        for (int i = 0; i < amountOfDice; i++) {
            diceRoll.add(randomIntGenerator(minvalue, maxvalue));
        }
        return diceRoll;
    }

    public static void checkIfRolledDouble(Game game,Player player, List<Integer> diceRoll) {
        if (Objects.equals(diceRoll.get(0), diceRoll.get(1))){
            player.addDoubleThrow();
            Jail.checkIfFreeByDoubleThrow(player);
            Jail.checkIfJailedByDoubleThrow(player,game);
        }else{
            player.resetDoubleThrows();
        }
    }

    private static int randomIntGenerator(int minvalue, int maxvalue) {
        int offByOne = 1;
        maxvalue = maxvalue + offByOne;
        return ThreadLocalRandom.current().nextInt(minvalue, maxvalue);
    }


}
