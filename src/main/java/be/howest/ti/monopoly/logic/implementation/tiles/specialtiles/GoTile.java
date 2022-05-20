package be.howest.ti.monopoly.logic.implementation.tiles.specialtiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class GoTile extends Tile {
    public GoTile(String name, int position, String type, String description, String actionType) {
        super(name, position, type, description, actionType);
    }

    @Override
    public void tileAction(Game game, Player player){
        // make abstract or DO something
    }
}
