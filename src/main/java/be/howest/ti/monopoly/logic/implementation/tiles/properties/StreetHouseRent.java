package be.howest.ti.monopoly.logic.implementation.tiles.properties;
import java.util.Objects;

public class StreetHouseRent {
    private final int rentWithOneHouse;
    private final int rentWithTwoHouses;
    private final int rentWithThreeHouses;
    private final int rentWithFourHouses;
    private final int rentWithHotel;

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
