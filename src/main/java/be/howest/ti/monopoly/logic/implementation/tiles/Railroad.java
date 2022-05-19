package be.howest.ti.monopoly.logic.implementation.tiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.PlayerProperty;

public class Railroad extends Property{

    private static final int PRICE_ONE_RAILROAD = 25;

    public Railroad(String name, int position, String type, int groupSize, String color, int rent, int mortgage, int cost) {
        super(name, position, type, groupSize, color, rent,mortgage,cost);
    }

    @Override
    public int computeRent(Game game, PlayerProperty playerProperty, Player debtPlayer, Player player) {
        return PRICE_ONE_RAILROAD * debtPlayer.checkHowManyUtilities("railroad");
    }
}