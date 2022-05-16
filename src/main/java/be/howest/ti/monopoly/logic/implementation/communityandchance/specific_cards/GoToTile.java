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
        Tile tile = Tile.getTileFromPosition(position);
        int positionOfJail = 10;
        player.setPreviousTile(player.currentTile);
        if (position == positionOfJail){
            player.setJailed(true);
        }
        player.setCurrentTile(tile);
        Move.checkIfPassedGo(player);
    }

}
