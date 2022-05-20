package be.howest.ti.monopoly.logic.implementation.cummunityandchance;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;

public abstract class CommunityOrChanceCard {

    private final String description;

    protected CommunityOrChanceCard(String description) {
        this.description = description;
    }

    public abstract void cardAction(Game game, Player player);

    @Override
    public String toString() {
        return description;
    }

}
