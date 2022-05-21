package be.howest.ti.monopoly.logic.implementation.tiles.properties;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.PlayerProperty;
import be.howest.ti.monopoly.logic.implementation.tiles.Colors;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Utility extends Property {

    private static final int MULTIPLIER_FOR_ONE_UTILITY_TILE = 4;
    private static final int MULTIPLIER_FOR_TWO_UTILITY_TILES = 10;
    String utilityRent;

    public Utility(String name, int position) {
        super(name, position, "utility", 2, Colors.WHITE.toString(), -1, 75, 150);
        this.utilityRent = "4 or 10 times the dice roll";
    }

    @JsonProperty("rent")
    public String getRentDescription() {
        return utilityRent;
    }

    @Override
    public int computeRent(Game game, PlayerProperty playerProperty, Player debtPlayer, Player player) {
        int thrownAmount = game.getLastDiceRollFullAmount();
        int oneUtilityTile = 1;
        if (debtPlayer.checkHowManyUtilities("utility") > oneUtilityTile) {
            return(MULTIPLIER_FOR_TWO_UTILITY_TILES * thrownAmount);
        } else {
            return(MULTIPLIER_FOR_ONE_UTILITY_TILE * thrownAmount);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Utility utility = (Utility) o;
        return Objects.equals(utilityRent, utility.utilityRent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), utilityRent);
    }
}
