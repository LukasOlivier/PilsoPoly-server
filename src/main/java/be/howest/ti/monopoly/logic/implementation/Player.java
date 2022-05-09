package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.ServiceAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Player {
    private final String name;
    public Tile currentTile;
    private boolean jailed;
    private int money;
    private boolean bankrupt;
    private int getOutOfJailFreeCards;
    private String taxSystem = "ESTIMATE";
    private List<PlayerProperty> properties = new ArrayList<>();
    private int debt;
    private final String icon;
    private static Tile previousTile = null;

    public Player(String name, Tile currentTile, boolean jailed, int money, boolean bankrupt, int getOutOfJailFreeCards, int debt, String icon) {
        this.name = name;
        this.currentTile = currentTile;
        this.jailed = jailed;
        this.money = money;
        this.bankrupt = bankrupt;
        this.getOutOfJailFreeCards = getOutOfJailFreeCards;
        this.debt = debt;
        this.icon = icon;
    }

    public Player(String name, String icon) {
        this(name, new Tile("Go", 0, "Go"), false, 1500, false, 0, 0, icon);
    }

    public void addProperties(PlayerProperty newProperty) {
        properties.add(newProperty);
    }

    public String getName() {
        return name;
    }

    public String getCurrentTile() {
        return currentTile.getName();
    }

    public boolean isJailed() {
        return jailed;
    }

    public int getMoney() {
        return money;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public void setBankrupt() {
        this.bankrupt = true;
    }

    public int getGetOutOfJailFreeCards() {
        return getOutOfJailFreeCards;
    }

    public String getTaxSystem() {
        return taxSystem;
    }

    public List<PlayerProperty> getProperties() {
        return properties;
    }

    public int getDebt() {
        return debt;
    }

    public String getIcon() {
        return icon;
    }

    public void fine() {
        if (this.money >= 50) {
            this.money = this.money - 50;
            this.jailed = false;
        } else {
            throw new IllegalStateException("Not enough money");
        }
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    private void removeMoney(int amount) {
        money -= amount;
    }

    private void addMoney(int amount) {
        money += amount;
    }

    private int calculateTax() {
        return (int) Math.round(0.1 * (getMoney() + getTotalTilesCost() + getTotalBuildingsCost()));
    }

    private int getTotalTilesCost() {
        int totalCost = 0;
        for (PlayerProperty playerProperty : getProperties()) {
            totalCost += playerProperty.property.getCost();
        }
        return totalCost;
    }

    private int getTotalBuildingsCost() {
        int totalCost = 0;
        for (PlayerProperty playerProperty : getProperties()) {
            Street street = (Street) playerProperty.property;
            int houseCost = street.getHousePrice();
            totalCost = totalCost + (playerProperty.getHouseCount() * houseCost) + (playerProperty.getHotelCount() * houseCost);
        }
        return totalCost;
    }

    private void CheckIfPassedGo(List<Turn> turns) {
        if ((currentTile.getPosition() - previousTile.getPosition() < 1 && !jailed) || Objects.equals(previousTile, new Tile("Go", 0, "Go")) && turns.size() > 1){
            addMoney(200);
            System.out.println("passed Go");
        }
    }

    public void free() {
        if (this.getOutOfJailFreeCards >= 1) {
            this.getOutOfJailFreeCards--;
            this.jailed = false;
        } else {
            throw new IllegalStateException("Not enough get-out-of-jail-free cards");
        }
    }

    public void setTaxSystem(String preferredTaxSystem) {
        this.taxSystem = preferredTaxSystem;
    }

    public Turn rollDice(List<Tile> tiles, List<Turn> turns) {
        int diceOne = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        int diceTwo = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        if (jailed){
            jailFreeDiceRoll(diceOne,diceTwo);
        }else{
            int currentPosition = (currentTile.getPosition() + (diceOne + diceTwo)) % 40;
            currentTile = getTileFromPosition(tiles, currentPosition);
            takeTileAction(currentTile);
            CheckIfPassedGo(turns);
        }
        previousTile = currentTile;
        System.out.print(diceOne);
        System.out.print(" - ");
        System.out.println(diceTwo);
        System.out.println(currentTile.getName());
        return new Turn(getName(),"DEFAULT",new Move(currentTile.getName()),diceOne,diceTwo);
    }

    private Tile getTileFromPosition(List<Tile> tiles, int position) {
        for (Tile tile : tiles) {
            if (tile.getPosition() == position) {
                return tile;
            }
        }
        return null;
    }


    private void takeTileAction(Tile tile) {
        System.out.println(tile.getType());
        switch (tile.getType()) {
            case "Go to Jail":
                setCurrentTile(new Tile("Go to Jail", 30, "Go to Jail"));
                jailed = true;
                break;
            case "Luxury Tax":
                removeMoney(100);
                break;
            case "Tax Income":
                if (Objects.equals(getTaxSystem(), "ESTIMATE")) {
                    removeMoney(200);
                } else {
                    removeMoney(calculateTax());
                }
                break;
            default:
                // code block
        }
    }

    public void jailFreeDiceRoll(int diceOne, int diceTwo){
        if (diceOne == diceTwo){
            jailed = false;
        }
    }
}
