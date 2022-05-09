package be.howest.ti.monopoly.logic.implementation;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private String currentTile;
    private boolean jailed;
    private int money;
    private boolean bankrupt;
    private int getOutOfJailFreeCards;
    private String taxSystem = "ESTIMATE";
    private List<PlayerProperty> properties = new ArrayList<>();
    private int debt;
    private final String icon;

    public Player(String name, String currentTile, boolean jailed, int money, boolean bankrupt, int getOutOfJailFreeCards, int debt ,String icon) {
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
        this(name,"Go",false, 1500, false, 0, 0, icon);
    }

    public void addProperties(PlayerProperty newProperty){
        properties.add(newProperty);
    }

    public String getName() {
        return name;
    }

    public String getCurrentTile() {
        return currentTile;
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


    public void removeMoney(int amount){money -= amount;}

    public void fine() {
        if (this.money >= 50){
            this.money = this.money - 50;
            this.jailed = false;
        }
        else {
            throw new IllegalStateException("Not enough money");
        }
    }

    public void free() {
        if (this.getOutOfJailFreeCards >= 1){
            this.getOutOfJailFreeCards--;
            this.jailed = false;
        }
        else {
            throw new IllegalStateException("Not enough get-out-of-jail-free cards");
        }
    }


    public void setTaxSystem(String preferredTaxSystem) {
        this.taxSystem = preferredTaxSystem;
    }

}
