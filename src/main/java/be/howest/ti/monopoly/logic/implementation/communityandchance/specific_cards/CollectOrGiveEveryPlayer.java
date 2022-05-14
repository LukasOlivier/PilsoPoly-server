package be.howest.ti.monopoly.logic.implementation.communityandchance.specific_cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityOrChanceCard;

public class CollectOrGiveEveryPlayer extends CommunityOrChanceCard {

    int amount;

    public CollectOrGiveEveryPlayer(String description, int amount) {
        super(description);
        this.amount = amount;
    }

    @Override
    public void cardAction(Game game, Player player){
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
