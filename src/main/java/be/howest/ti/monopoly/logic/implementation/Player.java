package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles1.Tile;
import be.howest.ti.monopoly.logic.implementation.tiles1.Property;
import be.howest.ti.monopoly.logic.implementation.tiles1.Street;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private Tile previousTile;
    private boolean firstThrow;
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
        this.firstThrow = true;
        this.getOutOfJailFreeCards = getOutOfJailFreeCards;
        this.debt = debt;
        this.icon = icon;
        this.previousTile = new Tile("Go", 0, "Go", "passes 'GO!' and receives 200 for it", "go");
    }

    public Tile getPreviousTile() {
        return previousTile;
    }

    public void setPreviousTile(Tile previousTile) {
        this.previousTile = previousTile;
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

    public void addMoney(int amount){money += amount;}

    public void transfer(Player debtPlayer, int amount){
        removeMoney(amount);
        debtPlayer.addMoney(amount);
    }

    public void payRent(PlayerProperty playerProperty, Property property, Game game, Player debtPlayer){
        switch (currentTile.getType()){
            case ("utility"):
                payRentUtilityGetDiceRoll(game, debtPlayer);
                break;
            case ("street"):
                payRentStreet(playerProperty, property, debtPlayer);
                break;
            case ("railroad"):
                payRentRailRoad(debtPlayer);
                break;
            default:
                throw new IllegalArgumentException("you can not ask rent for any other type");
        }
    }

    public void payRentRailRoad(Player debtPlayer){
        int amountOfUtilitys = checkHowManyUtilitys("railroad", debtPlayer);
        transfer(debtPlayer, (25 * amountOfUtilitys));
    }

    public void payRentUtilityGetDiceRoll(Game game, Player debtPlayer){
        int indexOfLastTurn = game.getTurns().size() - 1;
        int lastDiceRollOne = game.getTurns().get(indexOfLastTurn).getRoll().getDiceOne();
        int lastDiceRollTwo = game.getTurns().get(indexOfLastTurn).getRoll().getDiceTwo();
        int lastDiceRoll = lastDiceRollOne + lastDiceRollTwo;
        payRentUtility(lastDiceRoll, debtPlayer);
    }

    public void payRentUtility(int thrownAmount, Player debtPlayer){
        int oneUtilityTile = 1;
        if (checkHowManyUtilitys("utility", debtPlayer) > oneUtilityTile){
            transfer(debtPlayer,MULTIPLIER_FOR_TWO_UTILITY_TILES * thrownAmount);
        }else{
            transfer(debtPlayer, MULTIPLIER_FOR_ONE_UTILITY_TILE * thrownAmount);
        }
    }

    public void payRentStreet(PlayerProperty playerProperty, Property property, Player debtPlayer){
        Street street = (Street) property;
        switch (playerProperty.getHouseCount()){
            case 1:
                transfer(debtPlayer, street.getRentWithOneHouse());
                break;
            case 2:
                transfer(debtPlayer, street.getRentWithTwoHouses());
                break;
            case 3:
                transfer(debtPlayer, street.getRentWithThreeHouses());
                break;
            case 4:
                transfer(debtPlayer, street.getRentWithFourHouses());
                break;
            default:
                if (playerProperty.getHotelCount() > 0){
                    transfer(debtPlayer,street.getRentWithHotel());
                }else{
                    transfer(debtPlayer,street.getRent());
                }
        }
    }

    private int checkHowManyUtilitys(String type, Player player) {
        int countTheTypes = 0;
        int addPropertyType = 1;
        for (PlayerProperty playerProperty : player.properties) {
            if (Objects.equals(playerProperty.getPropertyType(), type)) {
                countTheTypes += addPropertyType;
            }
        }
        return countTheTypes;
    }

    public void setFirstThrow() {
        this.firstThrow = false;
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

    public void setCurrentTile(Game game, int location){
        this.currentTile = Tile.getTileFromPosition(game, location);
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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return amountOfDoubleThrows == player.amountOfDoubleThrows && Objects.equals(name, player.name) && Objects.equals(icon, player.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, icon);
    }
}
