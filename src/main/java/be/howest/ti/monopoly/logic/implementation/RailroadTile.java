package be.howest.ti.monopoly.logic.implementation;

public class RailroadTile extends Tile{

    private int mortgage;
    private int rent;
    private int groupSize;
    private String color;

    public RailroadTile(String name, int position, String type, String nameAsPathParameter, int mortgage, int rent, int groupSize, String color) {
        super(name, position, type, nameAsPathParameter);
        this.mortgage = mortgage;
        this.rent = rent;
        this.groupSize = groupSize;
        this.color = color;
    }

    public int getMortgage() {
        return mortgage;
    }

    public int getRent() {
        return rent;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public String getColor() {
        return color;
    }
}
