package be.howest.ti.monopoly.logic.implementation;

public class Property extends Tile {
    private int cost;
    private int mortgage;
    protected int rent;
    private int groupSize;
    private String color;




    public Property(String name, int position, String type, int groupSize, String color, int rent, int mortgage, int cost) {
        super(name, position, type);
        this.groupSize = groupSize;
        this.color = color;
        this.rent = rent;
        this.mortgage = mortgage;
        this.cost = cost;
    }

    public int getMortgage() {
        return mortgage;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public String getColor() {
        return color;
    }

    public int getCost() {
        return cost;
    }
    public int getRent() {
        return rent;
    }


    public int getRentWithOneHouse() {return getRentWithOneHouse();}


    public int getRentWithTwoHouses() {return getRentWithTwoHouses();}



    public int getRentWithThreeHouses() {
        return getRentWithThreeHouses();
    }

    public int getRentWithFourHouses() {
        return getRentWithFourHouses();
    }


    public int getRentWithHotel() {
        return getRentWithHotel();
    }


    public int getHousePrice() {
        return getHousePrice();
    }
}
