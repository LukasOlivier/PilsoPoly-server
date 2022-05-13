package be.howest.ti.monopoly.logic.implementation.communityandchance.communitycards;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityCard;
import be.howest.ti.monopoly.logic.implementation.Game;

public class PayCC extends CommunityCard {

    private int amountToPay;

    public PayCC(String description, int amountToPay){
        super(description);
        this.amountToPay = amountToPay;
    }

    @Override
    public void communityCardAction(Game game, String playerName){
        game.getSpecificPlayer(playerName).removeMoney(amountToPay);
    }
}
