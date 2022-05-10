package be.howest.ti.monopoly.logic.implementation;

public class PlayerProperty {
    public final Property property;
    private boolean mortgage;
    private int houseCount;
    private int hotelCount;

    public PlayerProperty(Property property, boolean mortgage, int houseCount, int hotelCount) {
        this.property = property;
        this.mortgage = mortgage;
        this.houseCount = houseCount;
        this.hotelCount = hotelCount;
    }

    public PlayerProperty(Property property) {
        this(property, false,0,0);
    }

    public String getProperty() {
        return property.getName();
    }

    public boolean isMortgage() {
        return mortgage;
    }

    public int getHouseCount() {
        return houseCount;
    }

    public int getHotelCount() {
        return hotelCount;
    }

    public void addHouse() {
        if ( getHouseCount() < 4 ) {
            houseCount += 1;
        } else {
            throw new IllegalStateException("Can't have more than 4 houses");
        }
    }

    public void removeHouse() {
        if ( getHouseCount() > 0 ) {
            houseCount -= 1;
        } else {
            throw new IllegalStateException("Can not have less than 0 houses");
        }
    }
}
