package be.howest.ti.monopoly.logic.implementation;

import java.util.ArrayList;
import java.util.List;

public class Turn {
    private List<Integer> roll;
    private String player;
    private String type;
    private List<Move> moves = new ArrayList<>();

    public Turn( String player, String type, Move move, int diceOne, int diceTwo) {
        this.roll = new ArrayList<>();
        this.player = player;
        this.type = type;
        this.moves.add(move);
        roll.add(diceOne);
        roll.add(diceTwo);
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

    public List<Move> getMoves() {
        return moves;
    }
}
