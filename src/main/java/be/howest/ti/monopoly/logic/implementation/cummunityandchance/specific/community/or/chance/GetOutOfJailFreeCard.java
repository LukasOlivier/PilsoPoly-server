package be.howest.ti.monopoly.logic.implementation.cummunityandchance.specific.community.or.chance;

import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.cummunityandchance.CommunityOrChanceCard;
import be.howest.ti.monopoly.logic.implementation.Game;

public class GetOutOfJailFreeCard extends CommunityOrChanceCard {

    public GetOutOfJailFreeCard(String description) {
        super(description);
    }

    @Override
    public void cardAction(Game game, Player player){
        player.addGetOutOfJailFreeCard();
    }

}
