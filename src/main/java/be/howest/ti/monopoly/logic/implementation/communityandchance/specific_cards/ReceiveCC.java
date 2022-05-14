package be.howest.ti.monopoly.logic.implementation.communityandchance.specific_cards;

import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityOrChanceCard;
import be.howest.ti.monopoly.logic.implementation.Game;

public class ReceiveCC extends CommunityOrChanceCard {

    private int amountToReceive;

    public ReceiveCC(String description, int amountToReceive) {
        super(description);
        this.amountToReceive = amountToReceive;
    }

    @Override
    public void cardAction(Game game, Player player){
        player.addMoney(amountToReceive);
    }
}
