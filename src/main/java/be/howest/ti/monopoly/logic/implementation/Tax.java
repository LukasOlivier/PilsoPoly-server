package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.Tiles.Street;

public class Tax {
    static int incomeTax = 200;
    static int estimateTax = 200;
    static double computeTaxMultiplier = 0.1;


    public static int getIncomeTax() {
        return incomeTax;
    }

    public static int getEstimateTax() {
        return estimateTax;
    }

    public static int getComputeTax(Player player) {
        int totalWorthToTheBank = player.getMoney() + getTotalTilesCost(player) + getTotalBuildingsCost(player);
        int roundedToInt = (int) Math.round(computeTaxMultiplier * totalWorthToTheBank);
        return roundedToInt;
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
}