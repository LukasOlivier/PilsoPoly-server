package be.howest.ti.monopoly.logic.implementation.tiles1;

import java.util.Objects;

public class Street extends Property {
    private int housePrice;
    private StreetHouseRent rentOfHouses;

    public Street(String name, int position, String type, int groupSize, String color, StreetHouseRent rentOfHouses, int housePrice, int rent, int mortgage, int cost) {
        super(name, position, type, groupSize, color,rent,mortgage,cost);
        this.rentOfHouses = rentOfHouses;
        this.housePrice = housePrice;
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
}
