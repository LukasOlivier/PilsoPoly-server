package be.howest.ti.monopoly.logic.implementation.communityandchance.communitycards;

import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityCard;
import be.howest.ti.monopoly.logic.implementation.Game;

public class ReceiveCC extends CommunityCard {

    private int amountToReceive;

    public ReceiveCC(String description, int amountToReceive) {
        super(description);
        this.amountToReceive = amountToReceive;
    }

    @Override
    public void communityCardAction(Game game, Player player){
        player.addMoney(amountToReceive);
    }
}
