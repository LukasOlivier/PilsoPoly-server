package be.howest.ti.monopoly.logic.implementation;

public class Property extends Tile {
    private int cost;
    private int mortgage;
    protected int rent;
    private int groupSize;
    private String color;
    private boolean bought;



    public Property(String name, int position, String type, int groupSize, String color, int rent, int mortgage, int cost) {
        super(name, position, type, "can buy this property in direct sale","buy");
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

    @Override
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
    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public boolean isBought() {
        return bought;
    }

    @Override
    public String getDescription() {
        if (isBought()) {
            return "should pay rent";
        } else {
            return "can buy this property in direct sale";
        }
    }
@Override
    public String getActionType() {
        if (isBought()) {
            return "rent";
        } else {
            return "buy";
        }
    }
}
