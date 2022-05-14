package be.howest.ti.monopoly.logic.implementation.communityandchance.specific_cards;

import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityOrChanceCard;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class GoToJailCC extends CommunityOrChanceCard {

    public GoToJailCC(String description) {
        super(description);
    }

    @Override
    public void cardAction(Game game, Player player){
        player.setCurrentTile(new Tile("Jail", 10, "Jail", "Just visiting", "visiting"));
        player.setJailed(true);
    }
}
