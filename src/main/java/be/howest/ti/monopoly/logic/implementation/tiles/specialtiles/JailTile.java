package be.howest.ti.monopoly.logic.implementation.tiles.specialtiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class JailTile extends Tile {
    public JailTile(String name, int position, String type, String description, String actionType) {
        super(name, position, type, description, actionType);
    }

    @Override
    public void tileAction(Game game, Player player) {
        player.setCurrentTile();
        player.setJailed(true);
    }
}
