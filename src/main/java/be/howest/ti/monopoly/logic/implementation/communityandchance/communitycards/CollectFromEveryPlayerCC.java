package be.howest.ti.monopoly.logic.implementation.communityandchance.communitycards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityCard;

public class CollectFromEveryPlayerCC extends CommunityCard {

    int amountToReceive;

    public CollectFromEveryPlayerCC(String description, int amountToReceive) {
        super(description);
        this.amountToReceive = amountToReceive;
    }

    @Override
    public void communityCardAction(Game game, String playerName){
        int fullAmount = amountToReceive * game.getNumberOfPlayers();
        game.getSpecificPlayer(playerName).addMoney(fullAmount);
    }

}
