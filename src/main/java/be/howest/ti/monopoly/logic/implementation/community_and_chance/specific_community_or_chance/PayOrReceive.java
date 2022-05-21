package be.howest.ti.monopoly.logic.implementation.community_and_chance.specific_community_or_chance;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.community_and_chance.CommunityOrChanceCard;

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
