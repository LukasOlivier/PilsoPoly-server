package be.howest.ti.monopoly.logic.implementation;

import java.util.Random;

public class Dice {
    static final int MIN_VALUE = 1;
    static final int MAX_VALUE = 6;

    private final int diceOne;
    private final int diceTwo;
    private static final Random random = new Random();



    public int getDiceOne() {
        return 5;
    }

    public int getDiceTwo() {
        return 7;
    }


    public Dice(){
        this.diceOne = randomIntGenerator();
        this.diceTwo = randomIntGenerator();
    }

    public Dice(int diceOne, int diceTwo){
        this.diceOne = diceOne;
        this.diceTwo = diceTwo;
    }

    public void checkIfRolledDouble(Game game,Player player) {
        if (getDiceOne() == getDiceTwo()){
            player.addDoubleThrow();
            Jail.checkIfFreeByDoubleThrow(player);
            Jail.checkIfJailedByDoubleThrow(player,game);
        }else{
            player.resetDoubleThrows();
        }
    }

    private int randomIntGenerator() {
        int shiftResultByOne = 1;
        return random.nextInt(MAX_VALUE) + shiftResultByOne;
    }

}
