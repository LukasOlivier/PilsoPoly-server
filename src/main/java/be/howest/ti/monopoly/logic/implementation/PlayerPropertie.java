package be.howest.ti.monopoly.logic.implementation;

public class PlayerPropertie {
    private final String property;
    private boolean mortgage;
    private int houseCount;
    private int hotelCount;

    public PlayerPropertie(String property, boolean mortgage, int houseCount, int hotelCount) {
        this.property = property;
        this.mortgage = mortgage;
        this.houseCount = houseCount;
        this.hotelCount = hotelCount;
    }

    public PlayerPropertie(String property) {
        this(property, false,0,0);
    }

    public String getProperty() {
        return property;
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
}
