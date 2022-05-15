package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.Tiles.Tile;

public class Jail {


    public static void checkIfFreeByWaitingTurns(Player player) {
        int amountToPayToGetOutOfJail = 50;
        int maxTurnsInJail = 3;
        if (player.isJailed()) {
            if (player.getTurnsInJail() == maxTurnsInJail) {
                player.setJailed(false);
                player.removeMoney(amountToPayToGetOutOfJail);
            } else {
                player.addTurnInJail();
            }
        }
    }

    public static void checkIfFreeByDoubleThrow(Player player) {
        if (player.isJailed()) {
            player.setJailed(false);
            player.resetDoubleThrows();
        }
    }



    public static boolean checkIfJailedByDoubleThrow(Player player, Game game) {
        int maxDoubleThrowsBeforeJail = 3;
        if (player.getAmountOfDoubleThrows() == maxDoubleThrowsBeforeJail) {
            player.setJailed(true);
            player.setCurrentTile(new Tile("Jail", 10, "Jail", "In jail", "jailed"));
            player.resetDoubleThrows();
            game.getCurrentTurn().addMove(new Move("Jail","In jail", "jailed"));
            return true;
        }
        return false;
    }
}
