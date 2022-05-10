package be.howest.ti.monopoly.logic.implementation.Tiles;

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
