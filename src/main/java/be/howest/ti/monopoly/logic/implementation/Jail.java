package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.List;

public class Jail {


    public static void checkIfFreeByWaitingTurns(Player player) {
        int amountToPayToGetOutOfJail = 50;
        int maxTurnsInJail = 3;
        if (player.isJailed()){
            if (player.getTurnsInJail() == maxTurnsInJail) {
                player.setJailed(false);
                player.removeMoney(amountToPayToGetOutOfJail);
            } else {
                player.setJailed(true);
                player.addTurnInJail();
            }
        }
    }

    public static void checkIfFreeByDoubleThrow(Player player) {
        if (player.isJailed() && player.getAmountOfDoubleThrows() >= 1) {
            player.setJailed(false);
            player.resetDoubleThrows();
        } else {
            checkIfFreeByWaitingTurns(player);
        }
    }


    public static boolean checkIfJailedByDoubleThrow(Player player, Game game) {
        int maxDoubleThrowsBeforeJail = 3;
        if (player.getAmountOfDoubleThrows() == maxDoubleThrowsBeforeJail) {
            player.setJailed(true);
            player.setCurrentTile(new Tile("Jail", 10, "Jail", "In jail", "jailed"));
            player.resetDoubleThrows();
            player.setPreviousTile(Tile.getTileFromPosition(10));
            return true;
        }
        return false;
    }
}
