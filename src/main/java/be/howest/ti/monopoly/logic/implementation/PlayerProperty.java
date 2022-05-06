package be.howest.ti.monopoly.logic.implementation;

import java.util.Objects;

public class PlayerProperty {
    private final String property;
    private boolean mortgage;
    private int houseCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerProperty that = (PlayerProperty) o;
        return mortgage == that.mortgage && Objects.equals(property, that.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(property, mortgage);
    }

    private int hotelCount;

    public PlayerProperty(String property, boolean mortgage, int houseCount, int hotelCount) {
        this.property = property;
        this.mortgage = mortgage;
        this.houseCount = houseCount;
        this.hotelCount = hotelCount;
    }

    public PlayerProperty(String property) {
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
