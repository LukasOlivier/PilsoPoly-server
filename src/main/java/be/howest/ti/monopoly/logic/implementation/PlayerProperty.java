package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.Street;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties({"getProperty"})
public class PlayerProperty {
    public final Property property;
    private boolean mortgage;
    private int houseCount;
    private int hotelCount;

    private static final int MIN_HOUSE_COUNT = 0;
    private static final int MAX_HOUSE_COUNT = 4;
    private static final int MAX_HOTEL_COUNT = 1;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerProperty that = (PlayerProperty) o;
        return mortgage == that.mortgage && Objects.equals(property, that.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(property, mortgage);
    }

    public PlayerProperty(Property property, boolean mortgage, int houseCount, int hotelCount) {
        this.property = property;
        this.mortgage = mortgage;
        this.houseCount = houseCount;
        this.hotelCount = hotelCount;
    }

    public PlayerProperty(Property property) {
        this(property, false, 0, 0);
    }

    @JsonIgnore
    public Property getProperty() {
        return property;
    }

    @JsonProperty("property")
    public String getPropertyName() {
        return property.getName();
    }

    public String getPropertyType() {
        return property.getType();
    }

    public String getPropertyActionType() {
        return property.getActionType();
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
        if (canAddHouse(player) && playerOwnsStreet(otherProperties) && houseCountIsCorrect(otherProperties, true)) {
            houseCount += 1;
            withdrawHousePrice(player);
        } else {
            throw new IllegalStateException("could not buy house");
        }
    }

    public void sellHouse(Player player, List<PlayerProperty> otherProperties) {
        if (canRemoveHouse() && houseCountIsCorrect(otherProperties, false)) {
            houseCount -= 1;
            addHousePrice(player);
        } else {
            throw new IllegalStateException("could not sell house");
        }
    }

    public void buyHotel(Player player) {
        if (canBuyHotel()) {
            hotelCount = 1;
            houseCount = 0;
            withdrawHousePrice(player);
        } else {
            throw new IllegalStateException("could not buy hotel");
        }
    }

    public void sellHotel(Player player) {
        if (canSellHotel()) {
            hotelCount = 0;
            houseCount = MAX_HOUSE_COUNT;
            addHousePrice(player);
        } else {
            throw new IllegalStateException("could not sell hotel");
        }
    }

    private void addHousePrice(Player player) {
        Street street = (Street) property;
        int housePriceHalf = street.getHousePrice() / 2;
        player.addMoney(housePriceHalf);
    }

    private void withdrawHousePrice(Player player) {
        Street street = (Street) property;
        int housePrice = street.getHousePrice();
        player.removeMoney(housePrice);
    }

    private boolean canAddHouse(Player player) {
        Street street = (Street) property;
        int housePrice = street.getHousePrice();
        return getHouseCount() < MAX_HOUSE_COUNT && player.getMoney() >= housePrice;
    }

    private boolean canRemoveHouse() {
        return getHouseCount() > MIN_HOUSE_COUNT;
    }

    private boolean playerOwnsStreet(List<PlayerProperty> playerProperties) {
        int amount = 0;
        int groupSize = property.getGroupSize();
        String streetColor = property.getColor();
        for (PlayerProperty prop : playerProperties) {
            if (prop.property.getColor().equals(streetColor)) {
                amount += 1;
            }
        }
        return amount == groupSize;
    }

    public boolean houseCountIsCorrect(List<PlayerProperty> playerProperties, boolean buy) {
        int newHouseCount = getHouseCount();
        if (buy) {
            newHouseCount += 1;
        } else {
            newHouseCount -= 1;
        }
        for (PlayerProperty prop : playerProperties) {
            if (matchColor(prop) && maxHouseDifferenceCorrect(newHouseCount, prop)) {
                    return false;
            }
        }
        return true;
    }

    private boolean matchColor(PlayerProperty prop) {
        return property.getColor().equals(prop.property.getColor());
    }

    private boolean maxHouseDifferenceCorrect(int newHouseCount, PlayerProperty prop) {
        int maxHouseDifference = 1;
        return Math.abs(newHouseCount - prop.getHouseCount()) > maxHouseDifference;
    }

    public void mortgageTheProperty(Property property, Player player) {
        player.addMoney(property.getMortgage());
        property.setMortgaged(true);
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
        property.setMortgaged(false);
        setMortgage(false);
    }
}
