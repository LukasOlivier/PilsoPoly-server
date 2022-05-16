package be.howest.ti.monopoly.logic.implementation.Tiles;

import java.util.Objects;

public class Property extends Tile {
    private int cost;
    private int mortgage;
    protected int rent;
    private int groupSize;
    private String color;
    private boolean bought;
    private boolean mortgaged;



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

    public void setBought(boolean bought) {
        this.bought = bought;
        this.setActionType("rent");
        this.setDescription("should pay rent");
    }

    public boolean isBought() {
        return bought;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Property property = (Property) o;
        return bought == property.bought;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bought);
    }

    @Override
    public String getDescription() {
        if (isBought()) {
            if (isMortgaged()){
                return "no need to pay rent, the tile is mortgaged";
            }
            return "should pay rent";
        } else {
            return "can buy this property in direct sale";
        }
    }

    public boolean isMortgaged() {
        return mortgaged;
    }

    public void setMortgaged(boolean mortgaged) {
        this.mortgaged = mortgaged;
    }

    @Override
    public String getActionType() {
        if (isBought()) {
            if (isMortgaged()){
                return "mortgage";
            }
            return "rent";
        } else {
            return "buy";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Property property = (Property) o;
        return cost == property.cost && mortgage == property.mortgage && rent == property.rent && groupSize == property.groupSize && Objects.equals(color, property.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cost, mortgage, rent, groupSize, color);
    }
}
