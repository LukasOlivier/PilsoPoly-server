package be.howest.ti.monopoly.logic.implementation.communityandchance;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;

public class CommunityOrChanceCard {

    private String description;

    public CommunityOrChanceCard(String description) {
        this.description = description;
    }

    public void cardAction(Game game, Player player){

    }

    @Override
    public String toString() {
        return description;
    }

}
