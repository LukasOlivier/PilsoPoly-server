package be.howest.ti.monopoly.logic.implementation.communityandchance.specific_cards;

import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityOrChanceCard;
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
