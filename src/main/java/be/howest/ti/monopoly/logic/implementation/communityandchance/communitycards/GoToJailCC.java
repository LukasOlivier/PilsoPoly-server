package be.howest.ti.monopoly.logic.implementation.communityandchance.communitycards;

import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityCard;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tiles.Tile;

public class GoToJailCC extends CommunityCard {

    public GoToJailCC(String description) {
        super(description);
    }

    @Override
    public void communityCardAction(Game game, Player player){
        player.setCurrentTile(new Tile("Jail", 10, "Jail", "Just visiting", "visiting"));
        player.setJailed(true);
    }
}
