package be.howest.ti.monopoly.logic.implementation.communityandchance.specific_cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Move;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityOrChanceCard;
import be.howest.ti.monopoly.logic.implementation.tiles1.Tile;

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
        List<Tile> tiles= game.getGameTiles();
        boolean found = false;
        int i = currentPos;
        int placesToMove = 0;
        int sizeOfList = 39;
        while (!found){
            placesToMove ++;
            if (Objects.equals(tiles.get(i).getType(), type)) {
                found = true;
                currentPos = i;
            }
            if (i >= sizeOfList) {
                i = 0;
            } else {
                i ++;
            }
        }
        game.getCurrentTurn().addMove(Move.makeMove(player, placesToMove, game));
        player.setCurrentTile(game, currentPos);
    }
}
