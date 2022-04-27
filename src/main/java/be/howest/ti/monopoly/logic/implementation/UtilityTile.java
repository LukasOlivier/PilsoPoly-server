package be.howest.ti.monopoly.logic.implementation;

public class UtilityTile extends Tile{

    private int cost;
    private int mortgage;
    private String rent;
    private int groupSize;
    private String color;

    public UtilityTile(String name, int position, String type, String nameAsPathParameter, int cost, int mortgage, String rent, int groupSize, String color) {
        super(name, position, type, nameAsPathParameter);
        this.cost = cost;
        this.mortgage = mortgage;
        this.rent = rent;
        this.groupSize = groupSize;
        this.color = color;
    }

    public int getCost() {
        return cost;
    }

    public int getMortgage() {
        return mortgage;
    }

    public String getRent() {
        return rent;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public String getColor() {
        return color;
    }
}
