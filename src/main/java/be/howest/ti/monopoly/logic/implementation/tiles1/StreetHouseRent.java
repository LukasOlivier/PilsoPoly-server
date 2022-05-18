package be.howest.ti.monopoly.logic.implementation.tiles1;

import java.util.Objects;

public class StreetHouseRent {
    private int rentWithOneHouse;
    private int rentWithTwoHouses;
    private int rentWithThreeHouses;
    private int rentWithFourHouses;
    private int rentWithHotel;

    public StreetHouseRent(int rentWithOneHouse, int rentWithTwoHouses, int rentWithThreeHouses, int rentWithFourHouses, int rentWithHotel) {
        this.rentWithOneHouse = rentWithOneHouse;
        this.rentWithTwoHouses = rentWithTwoHouses;
        this.rentWithThreeHouses = rentWithThreeHouses;
        this.rentWithFourHouses = rentWithFourHouses;
        this.rentWithHotel = rentWithHotel;
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

    public void setRentWithOneHouse(int rentWithOneHouse) {
        this.rentWithOneHouse = rentWithOneHouse;
    }

    public void setRentWithTwoHouses(int rentWithTwoHouses) {
        this.rentWithTwoHouses = rentWithTwoHouses;
    }

    public void setRentWithThreeHouses(int rentWithThreeHouses) {
        this.rentWithThreeHouses = rentWithThreeHouses;
    }

    public void setRentWithFourHouses(int rentWithFourHouses) {
        this.rentWithFourHouses = rentWithFourHouses;
    }

    public void setRentWithHotel(int rentWithHotel) {
        this.rentWithHotel = rentWithHotel;
    }

    public int getRentWithFourHouses() {
        return rentWithFourHouses;
    }

    public int getRentWithHotel() {
        return rentWithHotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreetHouseRent rent = (StreetHouseRent) o;
        return rentWithOneHouse == rent.rentWithOneHouse && rentWithTwoHouses == rent.rentWithTwoHouses && rentWithThreeHouses == rent.rentWithThreeHouses && rentWithFourHouses == rent.rentWithFourHouses && rentWithHotel == rent.rentWithHotel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentWithOneHouse, rentWithTwoHouses, rentWithThreeHouses, rentWithFourHouses, rentWithHotel);
    }
}
