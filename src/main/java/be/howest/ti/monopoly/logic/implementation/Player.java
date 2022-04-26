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
    private final String taxSystem = "ESTIMATE";
    private List<PlayerPropertie> properties = new ArrayList<>();
    private int debt;

    public Player(String name, String currentTile, boolean jailed, int money, boolean bankrupt, int getOutOfJailFreeCards, int debt) {
        this.name = name;
        this.currentTile = currentTile;
        this.jailed = jailed;
        this.money = money;
        this.bankrupt = bankrupt;
        this.getOutOfJailFreeCards = getOutOfJailFreeCards;
        this.debt = debt;
    }

    public Player(String name) {
        this(name,"Go",false, 1500, false, 0, 0);
    }

    public void addProperties(PlayerPropertie newProperty){
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

    public int getGetOutOfJailFreeCards() {
        return getOutOfJailFreeCards;
    }

    public String getTaxSystem() {
        return taxSystem;
    }

    public List<PlayerPropertie> getProperties() {
        return properties;
    }

    public int getDebt() {
        return debt;
    }
}
