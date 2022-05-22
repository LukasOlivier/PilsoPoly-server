package be.howest.ti.monopoly.logic.implementation.community_and_chance.specific_community_or_chance;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Move;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.community_and_chance.CommunityOrChanceCard;
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
        int currentPos = player.getCurrentTile().getPosition();
        List<Tile> tiles= game.getGameTiles();
        int positionOnBoard = currentPos;
        int placesToMove = 0;
        int sizeOfBoard = 39;
        while (!Objects.equals(tiles.get(positionOnBoard).getType(), type)){
            placesToMove ++;
            if (positionOnBoard >= sizeOfBoard) {
                positionOnBoard = 0;
            } else {
                positionOnBoard ++;
            }
        }
        game.getCurrentTurn().addMove(Move.makeMove(player, placesToMove, game));
    }
}
