package be.howest.ti.monopoly.logic.implementation.communityandchance.communitycards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.PlayerProperty;
import be.howest.ti.monopoly.logic.implementation.communityandchance.CommunityCard;

public class StreetRepairCC extends CommunityCard {

    public int amountPerHotel;
    public int amountPerHouse;

    public StreetRepairCC(String description, int amountPerHouse,int amountPerHotel) {
        super(description);
        this.amountPerHotel = amountPerHotel;
        this.amountPerHouse = amountPerHouse;
    }

    @Override
    public void communityCardAction(Game game, Player player){
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
