package be.howest.ti.monopoly.logic.implementation.community_and_chance.specific_community_or_chance;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.PlayerProperty;
import be.howest.ti.monopoly.logic.implementation.community_and_chance.CommunityOrChanceCard;

public class Repairs extends CommunityOrChanceCard {

    private final int amountPerHotel;
    private final int amountPerHouse;

    public Repairs(String description, int amountPerHouse, int amountPerHotel) {
        super(description);
        this.amountPerHotel = amountPerHotel;
        this.amountPerHouse = amountPerHouse;
    }

    @Override
    public void cardAction(Game game, Player player){
        int amountOfHouses = 0;
        int amountOfHotels = 0;
        for (PlayerProperty playerProperty : player.getProperties()) {
            amountOfHouses += playerProperty.getHouseCount();
            amountOfHotels += playerProperty.getHotelCount();

        }
        int fullAmount = (amountOfHotels * amountPerHotel) + (amountOfHouses * amountPerHouse);
        player.removeMoney(fullAmount);
    }
}
