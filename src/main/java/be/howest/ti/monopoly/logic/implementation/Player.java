package be.howest.ti.monopoly.logic.implementation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties({ "previousTile", "amountOfDoubleThrows", "firstThrow" })
public class Player {
    private final String name;
    private Tile currentTile;
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

    public Player(String name, Tile currentTile, String icon) {
        this.name = name;
        this.currentTile = currentTile;
        this.jailed = false;
        this.money = 1500;
        this.bankrupt = false;
        this.firstThrow = true;
        this.getOutOfJailFreeCards = 0;
        this.debt = 0;
        this.icon = icon;
        this.previousTile = new Tile("Go", 0, "Go", "passes 'GO!' and receives 200 for it", "go");
    }

    public Tile getPreviousTile() {
        return previousTile;
    }

    public void setPreviousTile(Tile previousTile) {
        this.previousTile = previousTile;
    }

    public void setPreviousTile(Game game, int location) {
        this.previousTile = Tile.getTileFromPosition(game, location);
    }

    public boolean getFirstThrow(){
        return this.firstThrow;
    }

    public Player(String name, String icon) {
        this(name, new Tile("Go", 0, "Go", "passes 'GO!' and receives 200 for it", "go"), icon);
    }

    public void addProperty(PlayerProperty newProperty) {
        properties.add(newProperty);
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    public Tile getCurrentTile() {
        return currentTile;
    }

    @JsonProperty("currentTile")
    public String getCurrentTileName() {
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
        removePropertiesFromPlayer();
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

    public void payRent(PlayerProperty playerProperty, Game game, Player debtPlayer){
        int rent = playerProperty.getProperty().computeRent(game, playerProperty, debtPlayer, this);
        transfer(debtPlayer, rent);
        checkIfPlayerIsBankrupt(game);
    }

    public int checkHowManyUtilities(String type) {
        int countTheTypes = 0;
        int addPropertyType = 1;
        for (PlayerProperty playerProperty : this.properties) {
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

    public void checkIfPlayerIsBankrupt(Game game) {
        if (this.getMoney() < 0){
            this.setBankrupt();
            game.isEveryoneBankrupt();
        }
    }

    private void removePropertiesFromPlayer() {
        for (PlayerProperty playerProperty : this.getProperties()) {
            playerProperty.getProperty().setBought(false);
            playerProperty.getProperty().setMortgaged(false);
        }
        this.getProperties().clear();
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
