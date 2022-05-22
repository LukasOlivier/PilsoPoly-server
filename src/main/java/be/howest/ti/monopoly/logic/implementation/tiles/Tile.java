package be.howest.ti.monopoly.logic.implementation.tiles;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import java.util.Objects;

public class Tile {

    private final String name;
    private final int position;
    private final String type;
    private String description;
    private String actionType;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return position == tile.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, type);
    }

    public static Tile getTileFromPosition(Game game, int position) {
        for (Tile tile : game.getGameTiles()) {
            if (tile.getPosition() == position) {
                return tile;
            }
        }
        return null;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public void tileAction(Game game, Player player) {
        // nothing happens on this tile
    }
}
