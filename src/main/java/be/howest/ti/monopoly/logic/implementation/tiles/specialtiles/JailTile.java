package be.howest.ti.monopoly.logic.implementation.tiles.specialtiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class JailTile extends Tile {
    public JailTile(String name, int position, String description, String actionType) {
        super(name, position, "Jail", description, actionType);
    }

    @Override
    public void tileAction(Game game, Player player) {
        player.setJailed(true);
    }
}
