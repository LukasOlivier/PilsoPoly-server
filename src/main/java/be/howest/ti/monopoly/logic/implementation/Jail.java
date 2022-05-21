package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class Jail {

    private Jail() {
        throw new IllegalStateException("Utility class");
    }

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
        int jailPos = 10;
        if (player.getAmountOfDoubleThrows() == maxDoubleThrowsBeforeJail) {
            player.setJailed(true);
            player.setCurrentTile(Tile.getTileFromPosition(game, jailPos));
            player.resetDoubleThrows();
            player.setPreviousTile(Tile.getTileFromPosition(game,jailPos));
            return true;
        }
        return false;
    }
}
