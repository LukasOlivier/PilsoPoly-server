package be.howest.ti.monopoly.logic.implementation.cummunityandchance.specific.community.or.chance;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.cummunityandchance.CommunityOrChanceCard;

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
