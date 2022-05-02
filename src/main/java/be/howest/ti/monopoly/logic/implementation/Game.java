package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.IService;
import be.howest.ti.monopoly.web.Request;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.vertx.core.json.JsonObject;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.IService;
import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.exceptions.InsufficientFundsException;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.MonopolyService;
import be.howest.ti.monopoly.web.exceptions.ForbiddenAccessException;
import be.howest.ti.monopoly.web.exceptions.InvalidRequestException;
import be.howest.ti.monopoly.web.exceptions.NotYetImplementedException;
import be.howest.ti.monopoly.web.tokens.PlainTextTokens;
import be.howest.ti.monopoly.web.tokens.TokenManager;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BearerAuthHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.openapi.RouterBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import be.howest.ti.monopoly.logic.implementation.MonopolyService;

@JsonIgnoreProperties()
public class Game {

    // TODO : available huizen zijn altijd hetzelfde bij aammaken van een game.
    private int numberOfPlayers;
    private boolean started;
    private LinkedList players;
    private String id;
    private String directSale;
    private int availableHouses;
    private int availableHotels;
    private List<Turn> turns = new ArrayList<>();
    private boolean canroll;
    private boolean ended;
    private String currentPlayer;
    private String winner;

    // This is to create a dummy game
    public Game (){
        this.numberOfPlayers = 4;
        this.id = "Dummy";
        this.players = new LinkedList<>();
        this.started = true;
        this.directSale = null;
        this.availableHouses = 31;
        this.availableHotels = 12;
        this.turns = new LinkedList<>();
        this.canroll = true;
        this.ended = false;
        this.currentPlayer = "Sibren";
        this.winner = null;
        addPlayer("Sibren", null);
        addPlayer("Niels", null);
        addPlayer("Lukas", null);
        addPlayer("Robin", null);
    }

    public Game(Request request, int size) {

        if (request == null) {
            throw new IllegalArgumentException();
        }

        int numberOfPlayers = (int) request.getCreateGameInfo().get("numberOfPlayers");
        String prefix = (String) request.getCreateGameInfo().get("prefix") + "_" + (size+1);

        setNumberOfPlayers(numberOfPlayers);
        this.started = false;
        this.players = new LinkedList<>();
        this.id = prefix;

    }

    public void setId(String id) {
        if (id == null || id.contains("-")) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        if (numberOfPlayers < 2 || numberOfPlayers > 8) {
            throw new IllegalArgumentException("Number of players should be between 2 and 8");
        } else {
            this.numberOfPlayers = numberOfPlayers;
        }
    }


    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public boolean isStarted() {
        return started;
    }

    public LinkedList getPlayers() {
        return players;
    }

    public String getId() {
        return id;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public void addPlayer(String name, String icon){
        players.add(new Player(name, icon));
    }

    public String getDirectSale() {
        return directSale;
    }

    public int getAvailableHouses() {
        return availableHouses;
    }

    public int getAvailableHotels() {
        return availableHotels;
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public boolean isCanroll() {
        return canroll;
    }

    public boolean isEnded() {
        return ended;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String getWinner() {
        return winner;
    }

    public JsonObject showSpecificGameInfo(){
        return new JsonObject()
                .put("numberOfPlayers", this.getNumberOfPlayers())
                .put("started", this.isStarted())
                .put("players", this.getPlayers())
                .put("id", this.getId());
    }

    public void setDirectSale(String directSale) {
        this.directSale = directSale;
    }

    public void setAvailableHouses(int availableHouses) {
        this.availableHouses = availableHouses;
    }

    public void setAvailableHotels(int availableHotels) {
        this.availableHotels = availableHotels;
    }
    // todo make this add turns
    public void setTurns(List<Turn> turns) {
        this.turns = turns;
    }

    public void setCanroll(boolean canroll) {
        this.canroll = canroll;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
