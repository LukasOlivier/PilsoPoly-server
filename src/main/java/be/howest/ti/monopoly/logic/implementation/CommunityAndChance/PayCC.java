package be.howest.ti.monopoly.logic.implementation.CommunityAndChance;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;

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
