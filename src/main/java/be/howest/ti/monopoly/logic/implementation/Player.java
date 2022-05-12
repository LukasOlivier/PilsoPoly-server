package be.howest.ti.monopoly.logic.implementation;

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

    public Tile previousTile = null;
    private boolean firstThrow = true;
    private int turnsInJail = 0;

    private int amountOfDoubleThrows = 0;

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
        this(name, new Tile("Go", 0, "Go", "passes 'GO!' and receives 200 for it", "go"), false, 1500, false, 0, 0, icon);
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


    public void removeMoney(int amount) {
        money -= amount;
    }

    public void addMoney(int amount) {
        money += amount;
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

    private void checkIfPassedGo() {
        if (!firstThrow && (currentTile.getPosition() - previousTile.getPosition() < 1 && !jailed || Objects.equals(previousTile, new Tile("Go", 0, "Go", "passes 'GO!' and receives 200 for it", "go")))) {
            addMoney(200);
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


    public Turn rollDice(List<Tile> tiles) {

        int diceOne = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        int diceTwo = ThreadLocalRandom.current().nextInt(1, 6 + 1);

        checkIfRolledTwice(diceOne, diceTwo);
        if (!isJailed(diceOne, diceTwo)) {
            int currentPosition = (currentTile.getPosition() + (diceOne + diceTwo)) % 40;
            currentTile = Tile.getTileFromPosition(tiles, currentPosition);
            takeTileAction(currentTile);
            checkIfPassedGo();
        }
        previousTile = currentTile;
        firstThrow = false;
        return new Turn(getName(), "DEFAULT", new Move(currentTile.getName(), currentTile.getDescription(), currentTile.getActionType()), diceOne, diceTwo);
    }

    public void checkIfRolledTwice(int diceOne, int diceTwo) {
        if (diceOne == diceTwo) {
            addDoubleThrow();
        } else {
            resetDoubleThrows();
        }
    }

    private boolean isJailed(int diceOne, int diceTwo) {
        if (!jailed) {
            if (getAmountOfDoubleThrows() >= 3) {
                resetDoubleThrows();
                jailed = true;
                setCurrentTile(new Tile("Jail", 10, "Jail", "In jail", "jailed"));
                return true;
            }
            return false;
        } else {
            if (diceOne == diceTwo) {
                jailed = false;
                return false;
            }
            if (turnsInJail >= 3) {
                jailed = false;
                removeMoney(50);
                return false;
            }
            turnsInJail++;
            return true;
        }
    }

    private void takeTileAction(Tile tile) {
        switch (tile.getType()) {
            case "Go to Jail":
                setCurrentTile(new Tile("Jail", 10, "Jail", "In jail", "jailed"));
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

    public void setJailed(boolean jailed) {
        this.jailed = jailed;
    }

    public void addDoubleThrow() {
        amountOfDoubleThrows++;
    }

    public void resetDoubleThrows() {
        amountOfDoubleThrows = 0;
    }

    private int getAmountOfDoubleThrows() {
        return amountOfDoubleThrows;
    }
}
