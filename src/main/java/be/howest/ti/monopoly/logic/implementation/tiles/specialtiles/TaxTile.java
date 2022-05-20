package be.howest.ti.monopoly.logic.implementation.tiles.specialtiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.PlayerProperty;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.tiles.properties.Street;

import java.util.Objects;

public class TaxTile extends Tile {

    private static final int INCOME_TAX = 200;
    private static final int ESTIMATE_TAX = 200;
    private static final double COMPUTE_TAX_MULTIPLIER = 0.1;

    public TaxTile(String name, int position, String type, String actionType) {
        super(name, position, type, "Pay your taxes!" , actionType);
    }

    public static int getIncomeTax() {
        return INCOME_TAX;
    }

    public static int getComputeTax(Player player) {
        int totalWorthToTheBank = player.getMoney() + getTotalTilesCost(player) + getTotalBuildingsCost(player);
        return (int) Math.round(COMPUTE_TAX_MULTIPLIER * totalWorthToTheBank);
    }

    private static int getTotalTilesCost(Player player) {
        int totalCost = 0;
        for (PlayerProperty playerProperty : player.getProperties()) {
            totalCost += playerProperty.property.getCost();
        }
        return totalCost;
    }

    private static int getTotalBuildingsCost(Player player) {
        int totalCost = 0;
        for (PlayerProperty playerProperty : player.getProperties()) {
            Street street = (Street) playerProperty.property;
            int houseCost = street.getHousePrice();
            totalCost = totalCost + (playerProperty.getHouseCount() * houseCost) + (playerProperty.getHotelCount() * houseCost);
        }
        return totalCost;
    }

    @Override
    public void tileAction(Game game, Player player){
        if (Objects.equals(this.getActionType(), "luxtax")) {
            player.removeMoney(INCOME_TAX);
        } else {
            if (Objects.equals(player.getTaxSystem(), "ESTIMATE")) {
                player.removeMoney(ESTIMATE_TAX);
            } else {
                player.removeMoney(TaxTile.getComputeTax(player));
            }
        }
    }
}
