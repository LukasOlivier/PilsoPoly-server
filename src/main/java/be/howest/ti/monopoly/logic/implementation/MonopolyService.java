package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.Tiles.*;
import be.howest.ti.monopoly.web.Request;

import java.util.*;


public class MonopolyService extends ServiceAdapter {
    Map<String, Game> allGames = new HashMap<>();

    @Override
    public String getVersion() {
        return "0.0.1";
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
        return Game.getGameTiles();
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
    public Game getDummyGame() {
        // Do we keep this?
        return new Game(4, "PilsoPoly", 99);
    }

    @Override
    public List<String> getChanceCards() {
        return List.of(
                "Advance to Boardwalk",
                "Advance to Go (Collect $200)",
                "Advance to Illinois Avenue. If you pass Go, collect $200",
                "Advance to St. Charles Place. If you pass Go, collect $200",
                "Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental to which they are otherwise entitled",
                "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown.",
                "Bank pays you dividend of $50",
                "Get Out of Jail Free",
                "Go Back 3 Spaces",
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


    public void buyProperty(Request request) {
        Game game = getGameById(request.getGameId());
        String playerName = request.getPathParameterValue("playerName");
        Player player = game.getSpecificPlayer(playerName);
        String propertyName = request.getPropertyName();
        Tile tileToBuy = player.currentTile;
        try {
            checkIfTileCanBeBought(propertyName,player,tileToBuy);
            Property tileToProperty = (Property) tileToBuy;
            PlayerProperty boughtProperty = new PlayerProperty(tileToProperty);
            player.addProperties(boughtProperty);
            player.removeMoney(tileToProperty.getCost());
            tileToProperty.setBought(true);
            checkIfPlayerCanRollAgain(game,player);
        } catch (IllegalStateException e) {
            throw new IllegalStateException("failed to buy property");
        }
    }

    public void checkIfTileCanBeBought(String propertyName, Player player, Tile tileToBuy) {
        if (getTile(propertyName).getPosition() != player.currentTile.getPosition()) {
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
        game.addPlayer(playerName, icon);
        if (game.getCurrentPlayer() == null) {
            game.setCurrentPlayer(playerName);
        }
    }

    @Override
    public void startPlayerAuction(Request request) {
        Game game = getGameById(request.getGameId());
        String playerName = request.playerThatStartedAuction();
        String propertyName = request.getPropertyName();
        int bid = request.getStartBid();
        int duration = request.getDuration();
        game.startPlayerAuction(bid, duration, playerName, propertyName);
    }

    @Override
    public void placeBidOnPlayerAuction(Request request) {
        Game game = getGameById(request.getGameId());
        String bidder = request.getBidder();
        int amount = request.getAmount();
        game.placeBidOnPlayerAuction(bidder, amount);
    }

    @Override
    public Auction getPlayerAuctions(Request request) {
        Game game = getGameById(request.getGameId());
        return game.getAuction();
    }

    @Override
    public void fine(Request request) {
        Game game = getGameById(request.getGameId());
        Player player = game.getSpecificPlayer(request.getPathParameterValue("playerName"));
        player.fine();
    }

    @Override
    public void free(Request request) {
        Game game = getGameById(request.getGameId());
        Player player = game.getSpecificPlayer(request.getPathParameterValue("playerName"));
        player.free();
    }

    @Override
    public void setBankrupt(Request request) {
        Game game = getGameById(request.getGameId());
        Player player = game.getSpecificPlayer(request.getPathParameterValue("playerName"));
        player.setBankrupt();
        game.isEveryoneBankrupt();
    }

    @Override
    public void useComputeTax(Request request) {
        Game game = getGameById(request.getGameId());
        Player player = game.getSpecificPlayer(request.getPathParameterValue("playerName"));
        player.setTaxSystem("COMPUTE");
    }

    @Override
    public void useEstimateTax(Request request) {
        Game game = getGameById(request.getGameId());
        Player player = game.getSpecificPlayer(request.getPathParameterValue("playerName"));
        player.setTaxSystem("ESTIMATE");
    }


    @Override
    public void rollDice(Request request) {
        Game game = getGameById(request.getGameId());
        Player player = game.getSpecificPlayer(request.getPathParameterValue("playerName"));
        if (Objects.equals(game.getCurrentPlayer(), player.getName())) {
            player.previousTile = player.currentTile;
            List<Integer> diceRollResult = Dice.rollDice();
            int placesToMove = calculatePlacesToMove(diceRollResult);
            Jail.checkIfFreeByWaitingTurns(player);
            game.addTurn(new Turn(player.getName(), "DEFAULT", Move.makeMove(player, placesToMove), diceRollResult.get(0), diceRollResult.get(1)));
            checkIfPlayerRolledDouble(player,diceRollResult);
            checkIfPlayerCanRollAgain(game,player);
            System.out.println(player.currentTile.getName());
            System.out.println(diceRollResult);
            System.out.println();
        } else {
            throw new IllegalMonopolyActionException("Not your turn!");
        }
    }

    private int calculatePlacesToMove(List<Integer> diceRoll) {
        int placesToMove = 0;
        for (Integer diceNumber : diceRoll) {
            placesToMove += diceNumber;
        }
        return placesToMove;
    }

    public void setNextPlayer(Game game, Player currentPlayer) {
        int indexOfNextPlayer = game.getPlayers().indexOf(currentPlayer) + 1;
        if (indexOfNextPlayer >= game.getPlayers().size()) {
            indexOfNextPlayer = 0;
        }
        game.setCurrentPlayer(game.getPlayers().get(indexOfNextPlayer).getName());
    }

    public void checkIfPlayerRolledDouble(Player player, List<Integer> diceRollResult){
        if (Dice.checkIfRolledDouble(diceRollResult)) {
            player.addDoubleThrow();
            checkIfJailedByDoubleThrow(player);
        }else{
            player.resetDoubleThrows();
        }
    }
    public void checkIfPlayerCanRollAgain(Game game,Player player) {
        if (Objects.equals(player.currentTile.getActionType(), "buy")){
            game.setCurrentPlayer("waiting for buy");
        }
        else if (player.getAmountOfDoubleThrows() >= 1 && !checkIfJailedByDoubleThrow(player)){
            game.setCurrentPlayer(player.getName());
        }else{
            setNextPlayer(game,player);
        }
    }

    public boolean checkIfJailedByDoubleThrow(Player player) {
        return Jail.checkIfFreeByDoubleThrow(player) || Jail.checkIfJailedByDoubleThrow(player);
    }
}


