package be.howest.ti.monopoly.logic.implementation.communityandchance.specific_cards;

import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityOrChanceCard;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class GoToGo extends CommunityOrChanceCard {

    public GoToGo(String description) {
        super(description);
    }

    @Override
    public void cardAction(Game game, Player player){
        player.setCurrentTile(new Tile("Go", 0, "Go", "passes 'GO!' and receives 200 for it", "go"));
    }
}
