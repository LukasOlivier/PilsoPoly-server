package be.howest.ti.monopoly.logic.implementation.tiles.specialtiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class GoToJailTile extends Tile {

    private static final int JAIL_POSITION = 10;

    public GoToJailTile(String name, int position) {
        super(name, position, "Go to Jail", "has to go to jail", "jail");
    }

    @Override
    public void tileAction(Game game, Player player) {
        player.setCurrentTile(game, JAIL_POSITION);
        player.setPreviousTile(game, JAIL_POSITION);
        player.setJailed(true);
    }
}
