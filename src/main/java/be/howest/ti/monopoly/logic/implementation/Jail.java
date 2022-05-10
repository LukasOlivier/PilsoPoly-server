package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.Tiles.Tile;

public class Jail {


    public static void checkIfFreeByWaitingTurns(Player player) {
        if (player.isJailed()) {
            int amountToPayToGetOutOfJail = 50;
            int maxTurnsInJail = 3;
            if (player.getTurnsInJail() == maxTurnsInJail) {
                player.setJailed(false);
                player.removeMoney(amountToPayToGetOutOfJail);
                System.out.println("free by waiting turns");
            }
        }
    }

    public static boolean checkIfFreeByDoubleThrow(Player player) {
        if (player.isJailed()) {
            player.setJailed(false);
            player.resetDoubleThrows();
            System.out.println("free by double");
            return true;
        }
        return false;
    }

    public static boolean checkIfJailedByDoubleThrow(Player player) {
        int maxDoubleThrowsBeforeJail = 3;
        if (player.getAmountOfDoubleThrows() == maxDoubleThrowsBeforeJail) {
            player.setJailed(true);
            player.setCurrentTile(new Tile("Jail", 10, "Jail", "In jail", "jailed"));
            player.resetDoubleThrows();
            System.out.println("jailed by double");
            return true;
        }
        return false;
    }
}
