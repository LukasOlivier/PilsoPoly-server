package be.howest.ti.monopoly.logic.implementation;


import be.howest.ti.monopoly.logic.implementation.Tiles.Property;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import be.howest.ti.monopoly.logic.implementation.Tiles.Street;
import be.howest.ti.monopoly.logic.implementation.Tiles.Tile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
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

    private static final int MULTIPLIER_FOR_ONE_UTILITY_TILE = 4;
    private static final int MULTIPLIER_FOR_TWO_UTILITY_TILES    = 10;

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

    public void addMoney(int amount){money += amount;}

    public void transfer(Player debtPlayer, int amount){
        removeMoney(amount);
        debtPlayer.addMoney(amount);
    }

    public void payRent(PlayerProperty playerProperty, Property property, Game game, Player debtPlayer){
        switch (currentTile.getType()){
            case ("utility"):
                payRentUtility(game, debtPlayer);
                break;
            case ("street"):
                payRentStreet(playerProperty, property, debtPlayer);
                break;
            case ("railroad"):
                transfer(debtPlayer, (25 * checkHowManyUtilitys("railroad")));
                break;
            default:
                throw new IllegalArgumentException("you can not ask rent for any other type");
        }
    }


    public void payRentUtility(Game game, Player debtPlayer){
        int oneUtilityTile = 1;
        int indexOfLastTurn = game.getTurns().size() - 1;
        int lastDiceRollOne = game.getTurns().get(indexOfLastTurn).getRoll().get(0);
        int lastDiceRollTwo =  + game.getTurns().get(indexOfLastTurn).getRoll().get(1) ;
        int lastDiceRoll = lastDiceRollOne + lastDiceRollTwo;
        if (checkHowManyUtilitys("utility") > oneUtilityTile){
            transfer(debtPlayer,MULTIPLIER_FOR_TWO_UTILITY_TILES * lastDiceRoll);
        }else{
            transfer(debtPlayer, MULTIPLIER_FOR_ONE_UTILITY_TILE * lastDiceRoll);
        }
    }

    public void payRentStreet(PlayerProperty playerProperty, Property property, Player debtPlayer){
        switch (playerProperty.getHouseCount()){
            case 1:
                transfer(debtPlayer, property.getRentWithOneHouse());
            case 2:
                transfer(debtPlayer, property.getRentWithTwoHouses());
            case 3:
                transfer(debtPlayer, property.getRentWithThreeHouses());
            case 4:
                transfer(debtPlayer, property.getRentWithFourHouses());
            default:
                if (playerProperty.getHotelCount() > 0){
                    transfer(debtPlayer,property.getRentWithHotel());
                }else{
                    transfer(debtPlayer,property.getRent());
                }
        }
    }

    private int checkHowManyUtilitys(String type) {
        int countTheTypes = 0;
        int addPropertyType = 0;
        for (PlayerProperty playerProperty : this.properties) {
            if (Objects.equals(playerProperty.getPropertyType(), type)) {
                countTheTypes += addPropertyType;
            }
        }
        return countTheTypes;
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

    public void addGetOutOfJailFreeCard(){
        getOutOfJailFreeCards++;
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
}
