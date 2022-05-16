package be.howest.ti.monopoly.logic.implementation;
import be.howest.ti.monopoly.logic.implementation.Tiles.Property;
import be.howest.ti.monopoly.logic.implementation.Tiles.Street;

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

    public void sellHouse(Player player, List<PlayerProperty> otherProperties) {
        if ( canRemoveHouse() && houseCountIsCorrect(otherProperties, false) ) {
            houseCount -= 1;
            addHousePrice(player);
        } else {
            throw new IllegalStateException("could not sell house");
        }
    }

    public void buyHotel(Player player, List<PlayerProperty> otherProperties) {
        if ( canBuyHotel() ) {
            hotelCount = 1;
            houseCount = 0;
        } else {
            throw new IllegalStateException("Cant buy hotel");
        }
    }

    private void addHousePrice(Player player) {
        Street street = (Street) property;
        int housePrice = street.getHousePrice();
        player.addMoney(housePrice);
    }

    private void withdrawHousePrice(Player player) {
        Street street = (Street) property;
        int housePrice = street.getHousePrice();
        player.removeMoney(housePrice);
    }

    private boolean canAddHouse() {
        int MAX_HOUSE_COUNT = 4;
        return getHouseCount() < MAX_HOUSE_COUNT;
    }

    private boolean canRemoveHouse() {
        int MIN_HOUSE_COUNT = 0;
        return getHouseCount() > MIN_HOUSE_COUNT;
    }

    private boolean playerOwnsStreet(List<PlayerProperty> playerProperties) {
        int amount = 0;
        int groupSize = property.getGroupSize();
        String streetColor = property.getColor();
        for ( PlayerProperty prop : playerProperties ) {
            if ( prop.property.getColor().equals(streetColor) ) {
                amount += 1;
            }
        }
        return amount == groupSize;
    }

    private boolean houseCountIsCorrect(List<PlayerProperty> playerProperties, boolean buy) {
        int maxHouseDifference = 1;
        int housesOnCurrentProperty = getHouseCount();
        int currentHousesAfterAction = getHouseCount();
        if (buy) {
            currentHousesAfterAction += 1;
        } else {
            currentHousesAfterAction -= 1;
        }
        for ( PlayerProperty prop : playerProperties ) {
            if ( Math.abs(currentHousesAfterAction - prop.getHouseCount()) > maxHouseDifference ) {
                return false;
            }
        }
        return true;
    }

    private boolean canBuyHotel() {
        int MAX_HOUSE_COUNT = 4;
        return houseCount == MAX_HOUSE_COUNT && hotelCount == 0;
    }
}
