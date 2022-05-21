package be.howest.ti.monopoly.logic.implementation.tiles.properties;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.PlayerProperty;
import be.howest.ti.monopoly.logic.implementation.tiles.Colors;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;

public class Railroad extends Property {

    private static final int PRICE_ONE_RAILROAD = 25;

    public Railroad(String name, int position) {
        super(name, position, "railroad", 4, Colors.BLACK.toString(), 4,100,200);
    }

    @Override
    public int computeRent(Game game, PlayerProperty playerProperty, Player debtPlayer, Player player) {
        return PRICE_ONE_RAILROAD * debtPlayer.checkHowManyUtilities("railroad");
    }
}
