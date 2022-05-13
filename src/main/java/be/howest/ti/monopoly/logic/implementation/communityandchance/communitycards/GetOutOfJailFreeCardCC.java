package be.howest.ti.monopoly.logic.implementation.communityandchance.communitycards;

import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityCard;
import be.howest.ti.monopoly.logic.implementation.Game;

public class GetOutOfJailFreeCardCC extends CommunityCard {

    public GetOutOfJailFreeCardCC(String description) {
        super(description);
    }

    @Override
    public void communityCardAction(Game game, String playerName){
        game.getSpecificPlayer(playerName).addGetOutOfJailFreeCard();
    }

}
