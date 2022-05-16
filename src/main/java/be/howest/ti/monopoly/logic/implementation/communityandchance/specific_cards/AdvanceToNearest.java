package be.howest.ti.monopoly.logic.implementation.communityandchance.specific_cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityOrChanceCard;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.List;
import java.util.Objects;

public class AdvanceToNearest extends CommunityOrChanceCard {

    String type;

    public AdvanceToNearest(String description, String type) {
        super(description);
        this.type = type;
    }

    @Override
    public void cardAction(Game game, Player player){
        int currentPos = player.currentTile.getPosition();
        List<Tile> tiles= Tile.getGameTiles();
        boolean found = false;
        int i = currentPos;
        while (!found){
            if (Objects.equals(tiles.get(i).getActionType(), type)) {
                found = true;
                currentPos = i;
            }
            if (i >= 39) {
                i = 0;
            } else {
                i ++;
            }
        }
        player.setCurrentTile(currentPos);
    }


}
