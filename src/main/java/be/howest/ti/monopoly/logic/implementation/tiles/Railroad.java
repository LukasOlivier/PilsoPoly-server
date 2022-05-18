package be.howest.ti.monopoly.logic.implementation.tiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.PlayerProperty;

import java.util.List;

public class Railroad extends Property{

    public Railroad(String name, int position, String type, int groupSize, String color, int rent, int mortgage, int cost) {
        super(name, position, type, groupSize, color, rent,mortgage,cost);
    }

    @Override
    public int computeRent(Game game, PlayerProperty playerProperty, Player debtPlayer, Player player) {
        return 0;
    }
}