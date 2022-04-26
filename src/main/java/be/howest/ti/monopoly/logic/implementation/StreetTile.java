package be.howest.ti.monopoly.logic.implementation;

public class StreetTile extends Tile{

    private int cost;
    private int mortgage;
    private int rent;
    private int rentWithOneHouse;
    private int rentWithTwoHouses;
    private int rentWithThreeHouses;
    private int rentWithFourHouses;
    private int rentWithHotel;
    private int housePrice;
    private String streetColor;
    private int groupSize;
    private String color;

    public StreetTile(String name, int position, String type, String nameAsPathParameter, int cost, int mortgage, int rent, int rentWithOneHouse, int rentWithTwoHouses, int rentWithThreeHouses, int rentWithFourHouses, int rentWithHotel, int housePrice, String streetColor, int groupSize, String color) {
        super(name, position, type, nameAsPathParameter);
        this.cost = cost;
        this.mortgage = mortgage;
        this.rent = rent;
        this.rentWithOneHouse = rentWithOneHouse;
        this.rentWithTwoHouses = rentWithTwoHouses;
        this.rentWithThreeHouses = rentWithThreeHouses;
        this.rentWithFourHouses = rentWithFourHouses;
        this.rentWithHotel = rentWithHotel;
        this.housePrice = housePrice;
        this.streetColor = streetColor;
        this.groupSize = groupSize;
        this.color = color;
    }

    public int getCost() {
        return cost;
    }

    public int getMortgage() {
        return mortgage;
    }

    public int getRent() {
        return rent;
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

    public String getStreetColor() {
        return streetColor;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public String getColor() {
        return color;
    }
}
