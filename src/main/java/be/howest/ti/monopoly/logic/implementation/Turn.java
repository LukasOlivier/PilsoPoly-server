package be.howest.ti.monopoly.logic.implementation;

import java.util.ArrayList;
import java.util.List;

public class Turn {
    private Dice roll;
    private final String player;
    private final String type;
    private final List<Move> moves = new ArrayList<>();

    public Turn(String player, String type) {
        this.player = player;
        this.type = type;
    }

    public void setRoll(Dice diceRoll) {
        this.roll = diceRoll;
    }

    public static void findNextPlayer(Game game, Player currentPlayer) {
        int indexOfNextPlayer = game.getPlayers().indexOf(currentPlayer) + 1;
        if (indexOfNextPlayer >= game.getPlayers().size()) {
            indexOfNextPlayer = 0;
        }
        if (game.getPlayers().get(indexOfNextPlayer).isBankrupt()){
            findNextPlayer(game, game.getPlayers().get(indexOfNextPlayer));
        }else{
            game.setCurrentPlayer(game.getPlayers().get(indexOfNextPlayer).getName());
        }
    }

    public void addMove(Move move){
        this.moves.add(move);
    }

    public Dice getRoll() {
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
