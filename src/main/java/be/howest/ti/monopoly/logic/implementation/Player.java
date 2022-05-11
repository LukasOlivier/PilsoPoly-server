package be.howest.ti.monopoly.logic.implementation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
        this(name, new Tile("Go", 0, "Go","passes 'GO!' and receives 200 for it", "go"), false, 1500, false, 0, 0, icon);
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

    public int removeMoney(int amount){
        money -= amount;
        return amount;
    }
    public void addMoney(int amount){money += amount;}

    public int payRent(PlayerProperty playerProperty, Property property, Game game){
        int rentToReceive = 0;
        switch (currentTile.getType()){
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
        if (!firstThrow && (currentTile.getPosition() - previousTile.getPosition() < 1 && !jailed || Objects.equals(previousTile, new Tile("Go", 0, "Go","passes 'GO!' and receives 200 for it", "go")))){
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
        int diceOne = 3;
        int diceTwo = 3;
        if (!isJailed(diceOne,diceTwo)){
            int currentPosition = (currentTile.getPosition() + (diceOne + diceTwo)) % 40;
            currentTile = Tile.getTileFromPosition(tiles, currentPosition);
            takeTileAction(currentTile);
            checkIfPassedGo();
        }
        previousTile = currentTile;
        firstThrow = false;
        return new Turn(getName(),"DEFAULT",new Move(currentTile.getName(),currentTile.getDescription(), currentTile.getActionType()),diceOne,diceTwo);
    }

    private boolean isJailed(int diceOne, int diceTwo){
        if (!jailed){
            return false;
        }
        if (diceOne == diceTwo){
            jailed = false;
            return false;
        }
        if(turnsInJail >= 3){
            jailed = false;
            removeMoney(50);
            return false;
        }
        turnsInJail++;
        return true;
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

    public void addDoubleThrow(){
        amountOfDoubleThrows++;
    }

    public void resetDoubleThrows(){
        amountOfDoubleThrows = 0;
    }

    public int getAmountOfDoubleThrows(){
        return amountOfDoubleThrows;
    }
}
