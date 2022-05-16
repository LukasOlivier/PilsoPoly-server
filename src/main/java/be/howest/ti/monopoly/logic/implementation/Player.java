package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({ "previousTile", "amountOfDoubleThrows", "firstThrow" })

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
    public Tile previousTile;
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
        this.previousTile = new Tile("Go", 0, "Go", "passes 'GO!' and receives 200 for it", "go");
    }

    public boolean getFirstThrow(){
        return this.firstThrow;
    }

    public Player(String name, String icon) {
        this(name, new Tile("Go", 0, "Go", "passes 'GO!' and receives 200 for it", "go"), false, 1500, false, 0, 0, icon);
    }


    public void addProperty(PlayerProperty newProperty) {
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

    public void fine() {
        if (this.money >= 50) {
            this.money = this.money - 50;
            this.jailed = false;
        } else {
            throw new IllegalStateException("Not enough money");
        }
    }

    public void addGetOutOfJailFreeCard(){
        getOutOfJailFreeCards++;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public void setCurrentTile(int location){
        //
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

    public void setJailed(boolean jailed) {
        this.jailed = jailed;
    }

    public int getTurnsInJail() {
        return turnsInJail;
    }

    public void addTurnInJail(){
        turnsInJail++;
    }

    public void addDoubleThrow() {
        amountOfDoubleThrows++;
    }

    public void resetDoubleThrows() {
        amountOfDoubleThrows = 0;
    }

    public int getAmountOfDoubleThrows() {
        return amountOfDoubleThrows;
    }

    public void addMoney(int amount) {
        this.money += amount;
    }
}
