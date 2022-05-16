package be.howest.ti.monopoly.logic.implementation;
import be.howest.ti.monopoly.logic.implementation.Tiles.Property;
import be.howest.ti.monopoly.logic.implementation.Tiles.Street;
import io.vertx.core.Handler;

import java.util.List;


public class PlayerProperty {
    public final Property property;
    private boolean mortgage;
    private int houseCount;
    private int hotelCount;

    private final static int MIN_HOUSE_COUNT = 0;
    private final static int MAX_HOUSE_COUNT = 4;
    private final static int MAX_HOTEL_COUNT = 1;


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

    public void setMortgage(boolean mortgage) {
        this.mortgage = mortgage;
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
            throw new IllegalStateException("could not buy hotel");
        }
    }

    public void sellHotel(Player player, List<PlayerProperty> properties) {
        if ( canSellHotel() ) {
            hotelCount = 0;
            houseCount = MAX_HOUSE_COUNT;
        } else {
            throw new IllegalStateException("could not sell hotel");
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
        return getHouseCount() < MAX_HOUSE_COUNT;
    }

    private boolean canRemoveHouse() {
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


    public void mortgageTheProperty(Property property, Player player) {
        player.addMoney(property.getMortgage());
        property.setActionType("mortgage");
        setMortgage(true);
    }

    private boolean canBuyHotel() {
        return houseCount == MAX_HOUSE_COUNT && hotelCount == 0;
    }

    private boolean canSellHotel() {
        return hotelCount == MAX_HOTEL_COUNT && houseCount == 0;

    }

    public void settleMortgageTheProperty(Property property, Player player) {
        player.removeMoney(property.getMortgage());
        property.setActionType("rent");
        setMortgage(false);
    }
}
