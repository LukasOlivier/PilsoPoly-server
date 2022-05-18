package be.howest.ti.monopoly.logic.implementation.communityandchance.specific_cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityOrChanceCard;

public class PayOrReceive extends CommunityOrChanceCard {

    private final int amount;

    public PayOrReceive(String description, int amount) {
        super(description);
        this.amount = amount;
    }

    @Override
    public void cardAction(Game game, Player player){
        player.addMoney(amount);
    }
}
