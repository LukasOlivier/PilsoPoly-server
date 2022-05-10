package be.howest.ti.monopoly.logic.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {
    private final String name;
    private String currentTile;
    private boolean jailed;
    private int money;
    private boolean bankrupt;
    private int getOutOfJailFreeCards;
    private final String taxSystem = "ESTIMATE";
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

    public int removeMoney(int amount){
        money -= amount;
        return amount;
    }
    public void addMoney(int amount){money += amount;}

    public int payRent(PlayerProperty playerProperty, Tile tile, Property property, Game game){
        int rentToReceive = 0;
        switch (tile.getType()){
            case ("utility"):
                if (checkHowManyUtilitys("utility") > 1){
                    rentToReceive = removeMoney(10 * game.getNumberOfPlayers());
                }else{
                    rentToReceive = removeMoney(4 * game.getNumberOfPlayers());
                }
                break;
            case ("street"):
                switch (playerProperty.getHouseCount()){
                    case 1:
                        rentToReceive = removeMoney(property.getRentWithOneHouse());
                        break;
                    case 2:
                        rentToReceive = removeMoney(property.getRentWithTwoHouses());
                        break;
                    case 3:
                        rentToReceive = removeMoney(property.getRentWithThreeHouses());
                        break;
                    case 4:
                        rentToReceive = removeMoney(property.getRentWithFourHouses());
                        break;
                    default:
                        if (playerProperty.getHotelCount() > 0){
                            rentToReceive = removeMoney(property.getRentWithHotel());
                        }else{
                            rentToReceive = removeMoney(property.getRent());
                        }
                        break;
                }
                break;
            case ("railroad"):
                rentToReceive = checkHowManyUtilitys("street");
                removeMoney(25 * rentToReceive);
                break;
            default:
                throw new IllegalArgumentException("you can not ask rent for any other type");
        }
        return rentToReceive;
    }

    private int checkHowManyUtilitys(String type) {
        int countTheTypes = 0;
        for (PlayerProperty playerProperty : this.properties){
            if (playerProperty.getType() == type){
                countTheTypes += 1;
            }
        }
        return countTheTypes;
    }

}
