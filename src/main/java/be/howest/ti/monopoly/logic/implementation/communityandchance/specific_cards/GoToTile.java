package be.howest.ti.monopoly.logic.implementation.communityandchance.specific_cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Move;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityOrChanceCard;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class GoToTile extends CommunityOrChanceCard {

    private final int position;

    public GoToTile(String description, int position) {
        super(description);
        this.position = position;
    }


    @Override
    public void cardAction(Game game, Player player){
        int positionOfJail = 10;
        int amountOfTiles = 40;

        int placesToMove = position - player.currentTile.getPosition();
        if (placesToMove < 0) {
            placesToMove += amountOfTiles;
        }
        if (position == positionOfJail ){
            player.setJailed(true);
            player.setCurrentTile(Tile.getTileFromPosition(game, 10));
            player.setPreviousTile(Tile.getTileFromPosition(game, 10));
        } else {
            game.getCurrentTurn().addMove(Move.makeMove(player, placesToMove, game));
        }
    }
}
