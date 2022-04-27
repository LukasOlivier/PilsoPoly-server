package be.howest.ti.monopoly.logic.implementation;

import java.util.ArrayList;
import java.util.List;

public class Turn {
    private List<Integer> roll = new ArrayList<>();
    private String player;
    private String type;
    private Move moves;

    public Turn(List<Integer> roll, String player, String type, Move moves) {
        this.roll = roll;
        this.player = player;
        this.type = type;
        this.moves = moves;
    }

    public List<Integer> getRoll() {
        return roll;
    }

    public String getPlayer() {
        return player;
    }

    public String getType() {
        return type;
    }

    public Move getMoves() {
        return moves;
    }
}
