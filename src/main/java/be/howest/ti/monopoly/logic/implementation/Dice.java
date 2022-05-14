package be.howest.ti.monopoly.logic.implementation;

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

    public static boolean checkIfRolledDouble(List<Integer> diceRoll) {
        return Objects.equals(diceRoll.get(0), diceRoll.get(1));

    }

    private static int randomIntGenerator(int minvalue, int maxvalue) {
        int offByOne = 1;
        maxvalue = maxvalue + offByOne;
        return ThreadLocalRandom.current().nextInt(minvalue, maxvalue);
    }




}
