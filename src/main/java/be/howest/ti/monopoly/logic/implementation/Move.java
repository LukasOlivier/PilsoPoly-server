package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.Tiles.Tile;

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

    public static Move makeMove(Player player, int placesToMove) {
        if (!player.isJailed()){
            int endOfBoardPosition = 40;
            int currentPosition = (player.currentTile.getPosition() + (placesToMove)) % endOfBoardPosition;
            player.currentTile = Tile.getTileFromPosition(Game.getGameTiles(), currentPosition);
            Tile.takeTileAction(player.currentTile, player);
            checkIfPassedGo(player);
        }
        return new Move(player.getCurrentTile(), player.currentTile.getDescription(), player.currentTile.getActionType());
    }


    private static void checkIfPassedGo(Player player) {
        Tile goTile = new Tile("Go", 0, "Go", "passes 'GO!' and receives 200 for it", "go");
        int rewardForPassingGo = 200;
        if (!passGoWithoutReward(player) && ((loopedTheBoard(player) || Objects.equals(player.previousTile, goTile)))) {
            player.addMoney(rewardForPassingGo);
        }
    }

    private static boolean loopedTheBoard(Player player) {
        int positionOfFirstTileOfBoard = 1;
        return player.currentTile.getPosition() - player.previousTile.getPosition() < positionOfFirstTileOfBoard;
    }

    public static int calculatePlacesToMove(List<Integer> diceRoll) {
        int placesToMove = 0;
        for (Integer diceNumber : diceRoll) {
            placesToMove += diceNumber;
        }
        return placesToMove;
    }

    @Override
    public String toString() {
        return "Move{" +
                "tile='" + tile + '\'' +
                ", description='" + description + '\'' +
                ", actionType='" + actionType + '\'' +
                '}';
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
