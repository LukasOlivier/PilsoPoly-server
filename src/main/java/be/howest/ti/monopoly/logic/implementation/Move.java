package be.howest.ti.monopoly.logic.implementation;

public class Move {
    private String tile;
    private String description;
    private String actionType; //TODO: WHUT?

    public Move(String tile) {
        this.tile = tile;
        this.description = "";
        this.actionType = "";
    }

    public String getTile() {
        return tile;
    }

    public String getDescription() {
        return description;
    }

    public String getActionType() {
        return actionType;
    }
}
