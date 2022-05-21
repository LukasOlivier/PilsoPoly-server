package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.tiles.*;
import java.util.*;


public class MonopolyService extends ServiceAdapter {
    Map<String, Game> allGames = new HashMap<>();

    @Override
    public String getVersion() {
        return "0.2.0";
    }

    @Override
    public List<String> getCommunityCards() {
        return List.of(
                "Advance to Go (Collect $200)",
                "Bank error in your favor. Collect $200",
                "Doctor's fee. Pay $50",
                "From sale of stock you get $50",
                "Get Out of Jail Free",
                "Go to Jail. Go directly to jail, do not pass Go, do not collect $200",
                "Holiday fund matures. Receive $100",
                "Income tax refund. Collect $20",
                "It is your birthday. Collect $10 from every player",
                "Life insurance matures. Collect $100",
                "Pay hospital fees of $100",
                "Pay school fees of $50",
                "Receive $25 consultancy fee",
                "You are assessed for street repair. $40 per house. $115 per hotel",
                "You have won second prize in a beauty contest. Collect $10",
                "You inherit $100");
    }

    @Override
    public List<Tile> getTiles() {
        return AllGameTiles.createGameTiles();
    }

    @Override
    public void addGame(Game game) {
        allGames.put(game.getId(), game);
    }

    @Override
    public void clearGameList() {
        allGames.clear();
    }

    @Override
    public int getGameMapSize() {
        return allGames.size();
    }

    @Override
    public Map<String, Game> getAllGames() {
        return allGames;
    }

    @Override
    public List<String> getChanceCards() {
        return List.of(
                "Advance to Boardwalk",
                "Advance to Go (Collect $200)",
                "Advance to Illinois Avenue. If you pass Go, collect $200",
                "Advance to St. Charles Place. If you pass Go, collect $200",
                "Advance to the nearest Railroad. If unowned, you may buy it from the Bank.",
                "Advance token to nearest Utility. If unowned, you may buy it from the Bank.",
                "Bank pays you dividend of $50",
                "Get Out of Jail Free",
                "Go to Jail. Go directly to Jail, do not pass Go, do not collect $200",
                "Make general repairs on all your property. For each house pay $25. For each hotel pay $100",
                "Speeding fine $15",
                "Take a trip to Reading Railroad. If you pass Go, collect $200",
                "You have been elected Chairman of the Board. Pay each player $50",
                "Your building loan matures. Collect $150"
        );
    }

    @Override
    public Tile getTile(int position) {
        for (Tile tile : getTiles()) {
            if (tile.getPosition() == position) {
                return tile;
            }
        }
        throw new MonopolyResourceNotFoundException("No such tile");
    }

    @Override
    public Tile getTile(String tileName) {
        for (Tile tile : getTiles()) {
            if (tile.getName().equals(tileName)) {
                return tile;
            }
        }
        throw new MonopolyResourceNotFoundException("No such tile");
    }

    @Override
    public void buyProperty(String gameId, String playerName, String propertyName) {
        Game game = getGameById(gameId);
        Player player = game.getSpecificPlayer(playerName);
        Tile tileToBuy = player.getCurrentTile();
        try {
            checkIfTileCanBeBought(propertyName, player, tileToBuy);
            Property tileToProperty = (Property) tileToBuy;
            PlayerProperty boughtProperty = new PlayerProperty(tileToProperty);
            player.addProperty(boughtProperty);
            player.removeMoney(tileToProperty.getCost());
            tileToProperty.setBought(true);
            Move.checkIfPlayerCanRollAgain(game, player);
        } catch (IllegalStateException e) {
            throw new IllegalStateException("failed to buy property");
        }
    }

    public void checkIfTileCanBeBought(String propertyName, Player player, Tile tileToBuy) {
        if (getTile(propertyName).getPosition() != player.getCurrentTile().getPosition()) {
            throw new IllegalStateException("you are not on that tile");
        }
        if (!Objects.equals(tileToBuy.getActionType(), "buy")) {
            throw new IllegalStateException("you can not buy a tile of any other type");
        }
        Property tileToProperty = (Property) tileToBuy;

        if (player.getMoney() < tileToProperty.getCost()) {
            throw new IllegalStateException("you do not have enough money");
        }
    }

    @Override
    public Game getGameById(String id) {
        return allGames.get(id);
    }

    @Override
    public void joinGame(String gameId, String playerName, String icon) {
        Game game = getGameById(gameId);
        if (game.isStarted()) {
            throw new IllegalStateException("already started");
        }
        game.addPlayer(playerName, icon);
    }

    @Override
    public void placeBidOnBankAuction(String gameId, String bidder, int amount) {
        Game game = getGameById(gameId);
        game.placeBidOnBankAuction(bidder, amount);
    }

    @Override
    public Auction getPlayerAuctions(String gameId) {
        Game game = getGameById(gameId);
        return game.getAuction();
    }

    @Override
    public void collectDebt(String gameId, String playerName, String debtPlayerName, String tileName){
        Game game = getGameById(gameId);
        Player player = game.getSpecificPlayer(playerName);
        Player debtPlayer = game.getSpecificPlayer(debtPlayerName);
        Tile tile = getTile(tileName);
        PlayerProperty playerProperty = findBoughtPropertyByOwner(tileName, debtPlayerName, game);
        try {
            checkIfPlayerNeedsToPayRent(tile, player, debtPlayerName, game, playerProperty);
            player.payRent(playerProperty,game,debtPlayer);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e);
        }
    }

    public void checkIfPlayerNeedsToPayRent(Tile tile, Player player, String debtPlayerName, Game game, PlayerProperty playerProperty){
        if (!Objects.equals(tile.getName(), player.getCurrentTile().getName())){
            throw new IllegalArgumentException("Player is not on the tile.");
        }
        if (findBoughtPropertyByOwner(player.getCurrentTile().getName(), debtPlayerName,game) == null){
            throw new IllegalArgumentException("The tile is not your property.");
        }
        if (!Objects.equals(playerProperty.getPropertyActionType(), "rent")){
            throw new IllegalArgumentException("This tile is not bought yet.");
        }
        if (playerProperty.isMortgage()){
            throw new IllegalArgumentException("This tile is mortgaged.");
        }
    }

    @Override
    public void setBankrupt(String playerName, String gameId) {
        Game game = getGameById(gameId);
        Player player = game.getSpecificPlayer(playerName);
        player.setBankrupt();
        if (Objects.equals(game.getCurrentPlayer(), player.getName())) {
            Turn.findNextPlayer(game, player);
        }
        game.isEveryoneBankrupt();
    }

    @Override
    public void useComputeTax(String playerName, String gameId) {
        Player player = getGameById(gameId).getSpecificPlayer(playerName);
        player.setTaxSystem("COMPUTE");
    }

    @Override
    public void useEstimateTax(String playerName, String gameId) {
        Player player = getGameById(gameId).getSpecificPlayer(playerName);
        player.setTaxSystem("ESTIMATE");
    }

    @Override
    public void getOutOfJailFine(String gameId, String playerName) {
        Player player = getGameById(gameId).getSpecificPlayer(playerName);
        player.fine();
    }

    @Override
    public void getOutOfJailFree(String gameId, String playerName) {
        Player player = getGameById(gameId).getSpecificPlayer(playerName);
        player.free();
    }

    @Override
    public void dontBuyProperty(String gameId, String playerName, String propertyName) {
        Game game = getGameById(gameId);
        game.dontBuyProperty(playerName, propertyName);
    }

    @Override
    public Game rollDice(String playerName, String gameId) {
        Game game = getGameById(gameId);
        Player player = game.getSpecificPlayer(playerName);
        if (Objects.equals(game.getCurrentPlayer(), player.getName()) && game.isCanRoll()) {
            game.addTurn(new Turn(player.getName(), "DEFAULT"));
            Dice diceRollResult = new Dice();
            diceRollResult.checkIfRolledDouble(game, player);

            int placesToMove = Move.calculatePlacesToMove(diceRollResult);
            Jail.checkIfFreeByWaitingTurns(player);

            game.getCurrentTurn().addMove(Move.makeMove(player, placesToMove, game));
            game.getCurrentTurn().setRoll(diceRollResult);

            Move.checkIfPlayerCanRollAgain(game, player);
            game.setLastDiceRoll(diceRollResult);
            return game;
        } else {
            throw new IllegalMonopolyActionException("Not your turn!");
        }
    }

    @Override
    public void buyHouse(String gameId, String playerName, String propertyName) {
        Game game = getGameById(gameId);
        Player player = game.getSpecificPlayer(playerName);
        PlayerProperty property = getCorrectProperty(player, propertyName);
        property.addHouse(player, player.getProperties());
    }

    @Override
    public void sellHouse(String gameId, String playerName, String propertyName) {
        Game game = getGameById(gameId);
        Player player = game.getSpecificPlayer(playerName);
        PlayerProperty property = getCorrectProperty(player, propertyName);
        property.sellHouse(player, player.getProperties());
    }

    @Override
    public void buyHotel(String gameId, String playerName, String propertyName) {
        Game game = getGameById(gameId);
        Player player = game.getSpecificPlayer(playerName);
        PlayerProperty property = getCorrectProperty(player, propertyName);
        property.buyHotel(player);
    }

    @Override
    public void sellHotel(String gameId, String playerName, String propertyName) {
        Game game = getGameById(gameId);
        Player player = game.getSpecificPlayer(playerName);
        PlayerProperty property = getCorrectProperty(player, propertyName);
        property.sellHotel(player);
    }

    public PlayerProperty getCorrectProperty(Player player, String propertyName) {
        for (PlayerProperty property : player.getProperties()) {
            if (property.getPropertyName().equals(propertyName)) {
                return property;
            }
        }
        return null;
    }

    @Override
    public void takeMortgage(String gameId, String playerName, String propertyName) {
        Game game = getGameById(gameId);
        Player player = game.getSpecificPlayer(playerName);
        PlayerProperty playerProperty = findBoughtPropertyByOwner(propertyName, player.getName(), game);
        try {
            checkIfTileCanBeMortgaged(playerProperty);
            Property property = (Property) getTile(propertyName);
            playerProperty.mortgageTheProperty(property, player);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e);
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e);
        }
    }

    public void checkIfTileCanBeMortgaged(PlayerProperty playerProperty) {
        if (playerProperty == null) {
            throw new IllegalArgumentException("Trying to mortgage someone else's tile");
        }
        if (playerProperty.isMortgage()) {
            throw new IllegalStateException("Property is already mortgaged");
        }
    }


    public void checkIfTileCanBeUnMortgaged(PlayerProperty playerProperty) {
        if (playerProperty == null) {
            throw new IllegalArgumentException("Trying to mortgage someone else's tile");
        }
        if (!playerProperty.isMortgage()) {
            throw new IllegalStateException("Property is not mortgaged");
        }
    }

    public PlayerProperty findBoughtPropertyByOwner(String name, String playerName, Game game) {
        for (Player player : game.getPlayers()) {
            for (PlayerProperty playerProperty : player.getProperties()) {
                if (Objects.equals(playerProperty.getPropertyName(), name) && Objects.equals(player.getName(), playerName)) {
                    return playerProperty;
                }
            }
        }
        return null;
    }

    @Override
    public void settleMortgage(String gameId, String playerName, String propertyName){
        Game game = getGameById(gameId);
        Player player = game.getSpecificPlayer(playerName);
        PlayerProperty playerProperty = findBoughtPropertyByOwner(propertyName, player.getName(), game);
        try {
            checkIfTileCanBeUnMortgaged(playerProperty);
            Property property = (Property) getTile(propertyName);
            playerProperty.settleMortgageTheProperty(property, player);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e);
        }
    }
}
