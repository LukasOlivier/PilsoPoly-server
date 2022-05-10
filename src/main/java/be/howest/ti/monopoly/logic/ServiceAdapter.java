package be.howest.ti.monopoly.logic;
import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.Tile;
import be.howest.ti.monopoly.web.Request;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Map;


public class ServiceAdapter implements IService {

    @Override
    public String getVersion() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getCommunityCards() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Tile> getTiles() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addGame(Game game) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game getGame() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game getDummyGame() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, Game> getAllGames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<JsonObject> mapToList(Map<String, Game> mapOfGames) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clearGameList() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getGameMapSize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getChanceCards() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, Game> filterGamesByNumberOfPlayers(int aInt, Map<String, Game> mapToFilter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, Game> filterGamesByPrefix(String aString, Map<String, Game> mapToFilter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, Game> filterGamesByStarted(boolean aBoolean, Map<String, Game> mapToFilter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tile getTile(int position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<JsonObject> filterGamesBy(String isStarted, String numberPlayers, String prefix) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tile getTile(String tileName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game getGameById(String id) {throw new UnsupportedOperationException();}

    @Override
    public void buyProperty(Request request) {throw new UnsupportedOperationException();}



    @Override
    public void joinGame(String gameId, String playerName, String icon) {
        throw new UnsupportedOperationException();
    }

    public void startPlayerAuction(Request request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void placeBidOnPlayerAuction(Request request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Auction getPlayerAuctions(Request request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fine(Request request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void free(Request request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBankrupt(Request request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void useComputeTax(Request request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void useEstimateTax(Request request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, Game> getGames() {
        throw new UnsupportedOperationException();
    }


}
