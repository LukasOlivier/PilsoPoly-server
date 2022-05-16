package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.Objects;

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
            player.currentTile = Tile.getTileFromPosition(Game.getGameTiles(), currentPosition);
            Tile.takeTileAction(player.currentTile, player, game);
            checkIfPassedGo(player);
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
        return player.currentTile.getPosition() - player.previousTile.getPosition() < positionOfFirstTileOfBoard;
    }

    @Override
    public String toString() {
        return "Move{" +
                "tile='" + tile + '\'' +
                ", description='" + description + '\'' +
                ", actionType='" + actionType + '\'' +
                '}';
    }

    private static boolean passGoWithoutReward(Player player) {
        return player.getFirstThrow() || player.isJailed();
    }
}
