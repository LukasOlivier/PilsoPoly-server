package be.howest.ti.monopoly.logic.implementation;

import java.util.List;
import java.util.Objects;

public class Tile {

    private String name;
    private int position;
    private String type;
    private String description;

    private String actionType;

    public Tile(String name, int position, String type, String description, String actionType) {
        this.name = name;
        this.position = position;
        this.type = type;
        this.description = description;
        this.actionType = actionType;
    }

    public int getCost() {
        return -1;
    }

    public String getDescription() {
        return description;
    }

    public String getActionType() {
        return actionType;
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

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return position == tile.position && name.equals(tile.name) && type.equals(tile.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, type);

    }

    public static Tile getTileFromPosition(List<Tile> tiles, int position) {
        for (Tile tile : tiles) {
            if (tile.getPosition() == position) {
                return tile;
            }
        }
        return null;
    }

}