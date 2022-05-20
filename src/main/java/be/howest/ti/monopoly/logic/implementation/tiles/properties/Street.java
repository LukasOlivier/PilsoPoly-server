package be.howest.ti.monopoly.logic.implementation.tiles.properties;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.PlayerProperty;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Street extends Property {

    private final int housePrice;
    private final StreetHouseRent rentOfHouses;
    private final List<Integer> listOfRents = new ArrayList<>();

    public Street(String name, int position, int groupSize, String color, StreetHouseRent rentOfHouses, int housePrice, int rent, int mortgage, int cost) {
        super(name, position, "street", groupSize, color,rent,mortgage,cost);
        this.rentOfHouses = rentOfHouses;
        this.housePrice = housePrice;
        listOfRents.add(getRent());
        listOfRents.add(getRentWithOneHouse());
        listOfRents.add(getRentWithTwoHouses());
        listOfRents.add(getRentWithThreeHouses());
        listOfRents.add(getRentWithFourHouses());
        listOfRents.add(getRentWithHotel());
    }

    public int getRentWithOneHouse() {
        return rentOfHouses.getRentWithOneHouse();
    }

    public int getRentWithTwoHouses() {
        return rentOfHouses.getRentWithTwoHouses();
    }

    public int getRentWithThreeHouses() {
        return rentOfHouses.getRentWithThreeHouses();
    }

    public int getRentWithFourHouses() {
        return rentOfHouses.getRentWithFourHouses();
    }

    public int getRentWithHotel() {
        return rentOfHouses.getRentWithHotel();
    }

    public int getHousePrice() {
        return housePrice;
    }

    @Override
    public int getRent(){
        return super.rent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Street street = (Street) o;
        return housePrice == street.housePrice && Objects.equals(rentOfHouses, street.rentOfHouses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), housePrice, rentOfHouses);
    }

    @Override
    public int computeRent(Game game, PlayerProperty playerProperty, Player debtPlayer, Player player) {
        if (playerProperty.getHotelCount() == 1) {
            return listOfRents.get(5);
        }
        return listOfRents.get(playerProperty.getHouseCount());
    }

}
