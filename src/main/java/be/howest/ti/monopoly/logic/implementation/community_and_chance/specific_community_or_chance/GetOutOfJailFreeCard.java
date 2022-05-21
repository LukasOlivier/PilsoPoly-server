package be.howest.ti.monopoly.logic.implementation.community_and_chance.specific_community_or_chance;

import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.community_and_chance.CommunityOrChanceCard;
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
