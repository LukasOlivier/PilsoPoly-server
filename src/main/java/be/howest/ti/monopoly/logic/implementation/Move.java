package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

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
        player.setPreviousTile(player.getCurrentTile());
        int amountOfTiles = 40;
        if (!player.isJailed()){
            int currentPosition = (player.getCurrentTile().getPosition() + (placesToMove)) % amountOfTiles;
            Tile tileToGo = Tile.getTileFromPosition(game, currentPosition);
            player.setCurrentTile(game, currentPosition);
            tileToGo.tileAction(game, player);
            checkIfPassedGo(player);
            player.checkIfPlayerIsBankrupt(game);
        }
        if (player.getFirstThrow()){
            player.setFirstThrow();
        }
        return new Move(player.getCurrentTileName(), player.getCurrentTile().getDescription(), player.getCurrentTile().getActionType());
    }


    public static void checkIfPassedGo(Player player) {
        int rewardForPassingGo = 200;
        if (!passGoWithoutReward(player) && (loopedTheBoard(player))) {
            player.addMoney(rewardForPassingGo);
        }
    }

    private static boolean loopedTheBoard(Player player) {
        int positionOfFirstTileOfBoard = 0;
        return player.getCurrentTile().getPosition() - player.getPreviousTile().getPosition() < positionOfFirstTileOfBoard;
    }

    public static int calculatePlacesToMove(Dice diceRoll) {
        int placesToMove = 0;
        placesToMove = placesToMove + diceRoll.getDiceOne() + diceRoll.getDiceTwo();
        return placesToMove;
    }

    public static void checkIfPlayerCanRollAgain(Game game, Player player) {
        if (Objects.equals(player.getCurrentTile().getActionType(), "buy")) {
            game.setCanRoll(false);
        } else if (player.getAmountOfDoubleThrows() >= 1 && !checkIfJailedByDoubleThrow(player, game) && !player.isBankrupt()) {
            game.setCurrentPlayer(player.getName());
            game.setCanRoll(true);
        } else {
            game.setCanRoll(true);
            Turn.findNextPlayer(game, player);
        }
    }

    private static boolean passGoWithoutReward(Player player) {
        return player.getFirstThrow() || player.isJailed();
    }
}
