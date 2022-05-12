package be.howest.ti.monopoly.logic.implementation.CommunityAndChance;

import be.howest.ti.monopoly.logic.implementation.Game;

public class ReceiveCC extends CommunityCard{

    private int amountToReceive;

    public ReceiveCC(String description, int amountToReceive) {
        super(description);
        this.amountToReceive = amountToReceive;
    }

    @Override
    public void communityCardAction(Game game, String playerName){
        game.getSpecificPlayer(playerName).addMoney(amountToReceive);
    }
}
