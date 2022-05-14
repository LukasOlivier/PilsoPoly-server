package be.howest.ti.monopoly.logic.implementation;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;

import java.util.List;


public class PlayerProperty {
    public final Property property;
    private boolean mortgage;
    private int houseCount;
    private int hotelCount;

    public PlayerProperty(Property property, boolean mortgage, int houseCount, int hotelCount) {
        this.property = property;
        this.mortgage = mortgage;
        this.houseCount = houseCount;
        this.hotelCount = hotelCount;
    }

    public PlayerProperty(Property property) {
        this(property, false,0,0);
    }

    public String getProperty() {
        return property.getName();
    }

    public boolean isMortgage() {
        return mortgage;
    }

    public int getHouseCount() {
        return houseCount;
    }

    public int getHotelCount() {
        return hotelCount;
    }

    public void addHouse(Player player, List<PlayerProperty> otherProperties) {
        if ( canAddHouse() && playerOwnsStreet(otherProperties) && houseCountIsCorrect(otherProperties, true) ) {
            houseCount += 1;
            withdrawHousePrice(player);
        } else {
            throw new IllegalStateException("could not buy house");
        }
    }

    public void withdrawHousePrice(Player player) {
        Street street = (Street) property;
        int housePrice = street.getHousePrice();
        player.removeMoney(housePrice);
    }

    public boolean canAddHouse() {
        int MAX_HOUSE_COUNT = 4;
        return getHouseCount() < MAX_HOUSE_COUNT;
    }

    public boolean playerOwnsStreet(List<PlayerProperty> playerProperties) {
        int amount = 0;
        int groupSize = property.getGroupSize();
        String streetColor = property.getColor();
        for ( PlayerProperty p : playerProperties ) {
            if ( p.property.getColor().equals(streetColor) ) {
                amount += 1;
            }
        }
        return amount == groupSize;
    }

    public boolean houseCountIsCorrect(List<PlayerProperty> playerProperties, boolean buy) {
        int maxHouseDifference = 1;
        int housesOnCurrentProperty = getHouseCount();
        int currentHousesAfterAction = getHouseCount();
        if (buy) {
            currentHousesAfterAction += 1;
        } else {
            currentHousesAfterAction -= 1;
        }
        for ( PlayerProperty p : playerProperties ) {
            if ( Math.abs(currentHousesAfterAction - p.getHouseCount()) > maxHouseDifference ) {
                return false;
            }
        }
        return true;
    }


    public void removeHouse() {
        if ( getHouseCount() > 0 ) {
            houseCount -= 1;
        } else {
            throw new IllegalStateException("Can not have less than 0 houses");
        }
    }
}
