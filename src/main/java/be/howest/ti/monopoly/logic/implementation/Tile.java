package be.howest.ti.monopoly.logic.implementation;

public class Tile {

    private String name;
    private int position;
    private String type;

    public Tile(String name, int position, String type) {
        this.name = name;
        this.position = position;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }
}