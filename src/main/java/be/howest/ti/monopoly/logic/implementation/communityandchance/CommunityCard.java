package be.howest.ti.monopoly.logic.implementation.communityandchance;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;

public class CommunityCard {

    private String description;

    public CommunityCard(String description) {
        this.description = description;
    }

    public void communityCardAction(Game game, Player player){

    }

    @Override
    public String toString() {
        return description;
    }

}
