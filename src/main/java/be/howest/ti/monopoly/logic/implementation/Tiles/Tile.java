package be.howest.ti.monopoly.logic.implementation.Tiles;

import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.Tax;

import java.util.List;
import java.util.Objects;

public class Tile {

    private String name;
    private int position;
    private String type;
    private String description;
    public String actionType;

    public void setDescription(String description) {
        this.description = description;
    }

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

    public static Tile getTileFromPosition(List<Tile> tiles, int position) {
        for (Tile tile : tiles) {
            if (tile.getPosition() == position) {
                return tile;
            }
        }
        return null;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public static void takeTileAction(Tile tile, Player player) {
        switch (tile.getActionType()) {
            case "jail":
                player.currentTile = new Tile("Jail", 10, "Jail", "In jail", "jailed");
                player.setJailed(true);
                break;
            case "luxtax":
                player.removeMoney(Tax.getIncomeTax());
                break;
            case "incometax":
                if (Objects.equals(player.getTaxSystem(), "ESTIMATE")) {
                    player.removeMoney(Tax.getEstimateTax());
                } else {
                    player.removeMoney(Tax.getComputeTax(player));
                }
                break;
            default:
        }
    }

    public boolean isBought() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return position == tile.position && Objects.equals(name, tile.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
}