package be.howest.ti.monopoly.logic.implementation.tiles.specialtiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class GoToJailTile extends Tile {
    public GoToJailTile(String name, int position) {
        super(name, position, "Go to Jail", "has to go to jail", "jail");
    }

    @Override
    public void tileAction(Game game, Player player) {
        player.setJailed(true);
    }
}
