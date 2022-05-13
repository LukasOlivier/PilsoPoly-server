package be.howest.ti.monopoly.logic.implementation.communityandchance.communitycards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityCard;

public class CollectFromEveryPlayerCC extends CommunityCard {

    int amount;

    public CollectFromEveryPlayerCC(String description, int amountToReceive) {
        super(description);
        this.amount = amountToReceive;
    }

    @Override
    public void communityCardAction(Game game, Player player){
        int thisPlayer = 1;
        int fullAmount = amount * (game.getNumberOfPlayers() - thisPlayer);
        for (Player person: game.getPlayers()){
            if (person == player){
                person.addMoney(fullAmount);
            } else {
                person.removeMoney(amount);
            }
        }
    }

}
