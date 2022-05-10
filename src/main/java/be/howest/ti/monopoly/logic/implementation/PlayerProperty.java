package be.howest.ti.monopoly.logic.implementation;

import java.util.Objects;

public class PlayerProperty {
    public final Property property;
    private boolean mortgage;
    private int houseCount;
    private String type;

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


    public PlayerProperty(Property property, boolean mortgage, int houseCount, int hotelCount, String type) {
        this.property = property;
        this.mortgage = mortgage;
        this.houseCount = houseCount;
        this.hotelCount = hotelCount;
        this.type = type;
    }


    public PlayerProperty(Property property, String type) {
            this(property, false, 0, 0, type);
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

    public String getType(){ return type;}
}
