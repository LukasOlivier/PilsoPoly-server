package be.howest.ti.monopoly.logic.implementation.tiles;

import java.util.Objects;

public class Street extends Property {
    private int rentWithOneHouse;
    private int rentWithTwoHouses;
    private int rentWithThreeHouses;
    private int rentWithFourHouses;
    private int rentWithHotel;
    private int housePrice;

    public Street(String name, int position, String type, int groupSize, String color, int rentWithOneHouse, int rentWithTwoHouses, int rentWithThreeHouses, int rentWithFourHouses, int rentWithHotel, int housePrice, int rent, int mortgage, int cost) {
        super(name, position, type, groupSize, color,rent,mortgage,cost);
        this.rentWithOneHouse = rentWithOneHouse;
        this.rentWithTwoHouses = rentWithTwoHouses;
        this.rentWithThreeHouses = rentWithThreeHouses;
        this.rentWithFourHouses = rentWithFourHouses;
        this.rentWithHotel = rentWithHotel;
        this.housePrice = housePrice;
    }


    public int getRentWithOneHouse() {
        return rentWithOneHouse;
    }

    public int getRentWithTwoHouses() {
        return rentWithTwoHouses;
    }

    public int getRentWithThreeHouses() {
        return rentWithThreeHouses;
    }

    public int getRentWithFourHouses() {
        return rentWithFourHouses;
    }

    public int getRentWithHotel() {
        return rentWithHotel;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public int getRent(){
        return super.rent;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!super.equals(other)) return false;
        Street street = (Street) other;
        return rentWithOneHouse == street.rentWithOneHouse && rentWithTwoHouses == street.rentWithTwoHouses && rentWithThreeHouses == street.rentWithThreeHouses && rentWithFourHouses == street.rentWithFourHouses && rentWithHotel == street.rentWithHotel && housePrice == street.housePrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rentWithOneHouse, rentWithTwoHouses, rentWithThreeHouses, rentWithFourHouses, rentWithHotel, housePrice);
    }
}
