package be.howest.ti.monopoly.logic.implementation.communityandchance.specific_cards;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityOrChanceCard;
import be.howest.ti.monopoly.logic.implementation.Game;

public class PayCC extends CommunityOrChanceCard {

    private int amountToPay;

    public PayCC(String description, int amountToPay){
        super(description);
        this.amountToPay = amountToPay;
    }

    @Override
    public void cardAction(Game game, Player player){
        player.removeMoney(amountToPay);
    }
}
