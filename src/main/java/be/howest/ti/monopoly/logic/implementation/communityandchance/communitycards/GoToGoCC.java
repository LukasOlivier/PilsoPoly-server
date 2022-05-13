package be.howest.ti.monopoly.logic.implementation.communityandchance.communitycards;

import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityCard;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tiles.Tile;

public class GoToGoCC extends CommunityCard {

    public GoToGoCC(String description) {
        super(description);
    }

    @Override
    public void communityCardAction(Game game, Player player){
        player.setCurrentTile(new Tile("Go", 0, "Go", "passes 'GO!' and receives 200 for it", "go"));
    }
}
