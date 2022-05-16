package be.howest.ti.monopoly.logic.implementation.communityandchance.specific_cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Move;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityOrChanceCard;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class AdvanceToNearest extends CommunityOrChanceCard {

    String type;

    public AdvanceToNearest(String description, String type) {
        super(description);
        this.type = type;
    }

    @Override
    public void cardAction(Game game, Player player){

    }


}
