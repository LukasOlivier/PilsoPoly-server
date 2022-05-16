package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.List;
import java.util.Objects;

import static be.howest.ti.monopoly.logic.implementation.Jail.checkIfJailedByDoubleThrow;

public class Move {
    private final String tile;
    private final String description;
    private final String actionType;

    public Move(String tile, String description, String actionType) {
        this.tile = tile;
        this.description = description;
        this.actionType = actionType;
    }

    public String getTile() {
        return tile;
    }

    public String getDescription() {
        return description;
    }

    public String getActionType() {
        return actionType;
    }

    public static Move makeMove(Player player, int placesToMove, Game game) {
        if (!player.isJailed()){

            int amountOfTiles = 40;
            int currentPosition = (player.currentTile.getPosition() + (placesToMove)) % amountOfTiles;
            player.currentTile = Tile.getTileFromPosition(currentPosition);
            Tile.takeTileAction(player.currentTile, player, game);
            checkIfPassedGo(player);
        }
        if (player.getFirstThrow()){
            player.setFirstThrow();
        }
        return new Move(player.getCurrentTile(), player.currentTile.getDescription(), player.currentTile.getActionType());
    }


    public static void checkIfPassedGo(Player player) {
        int rewardForPassingGo = 200;
        if (!passGoWithoutReward(player) && (loopedTheBoard(player))) {
            player.addMoney(rewardForPassingGo);
        }
    }

    private static boolean loopedTheBoard(Player player) {
        int positionOfFirstTileOfBoard = 0;
        return player.currentTile.getPosition() - player.getPreviousTile().getPosition() < positionOfFirstTileOfBoard;
    }

    public static int calculatePlacesToMove(Dice diceRoll) {
        int placesToMove = 0;
        placesToMove = placesToMove + diceRoll.getDiceOne() + diceRoll.getDiceTwo();
        return placesToMove;
    }

    public static void checkIfPlayerCanRollAgain(Game game, Player player) {
        if (Objects.equals(player.currentTile.getActionType(), "buy")) {
            game.setCanRoll(false);
        } else if (player.getAmountOfDoubleThrows() >= 1 && !checkIfJailedByDoubleThrow(player, game)) {
            game.setCurrentPlayer(player.getName());
            game.setCanRoll(true);

        } else {
            game.setCanRoll(true);
            Turn.setNextPlayer(game, player);
        }
    }

    private static boolean passGoWithoutReward(Player player) {
        return player.getFirstThrow() || player.isJailed();
    }
}
