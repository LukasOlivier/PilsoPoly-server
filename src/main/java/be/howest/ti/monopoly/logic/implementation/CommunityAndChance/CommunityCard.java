package be.howest.ti.monopoly.logic.implementation.CommunityAndChance;

import be.howest.ti.monopoly.logic.implementation.Game;

public class CommunityCard {

    private String description;

    public CommunityCard(String description) {
        this.description = description;
    }

    public void communityCardAction(Game game, String playerName){

    }

    @Override
    public String toString() {
        return description;
    }

}
