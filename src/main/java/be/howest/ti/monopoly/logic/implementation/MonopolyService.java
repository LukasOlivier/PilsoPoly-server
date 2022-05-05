package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.web.Request;
import io.vertx.core.json.JsonObject;

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
        return List.of(
                //String name, int position, String type
                new Tile("Go", 0, "Go"),
                new Street("Mediterranean", 1, "street", 2, "PURPLE", 10, 30, 90, 160, 250, 50, 2, 30, 60),
                new Tile("Community Chest I", 2, "community chest"),
                new Street("Baltic", 3, "street", 2, "PURPLE", 20, 60, 180, 320, 450, 50, 4, 30, 60),
                new Tile("Tax Income", 4, "Tax Income"),
                new Railroad("Reading RR", 5, "railroad", 4, "BLACK", 25, 100, 200),
                new Street("Oriental", 6, "street", 3, "LIGHTBLUE", 30, 90, 270, 400, 550, 50, 6, 50, 100),
                new Tile("Chance I", 7, "chance"),
                new Street("Vermont", 8, "street", 3, "LIGHTBLUE", 30, 90, 270, 400, 550, 50, 6, 50, 100),
                new Street("Connecticut", 9, "street", 3, "LIGHTBLUE", 40, 100, 300, 450, 600, 50, 8, 50, 120),
                new Tile("Jail", 10, "Jail"),
                new Street("Saint Charles Place", 11, "street", 3, "VIOLET", 50, 150, 450, 625, 750, 100, 10, 70, 140),
                new Utility("Electric Company", 12, "utility", 2, "WHITE", 75, 150),
                new Street("States", 13, "street", 3, "VIOLET", 50, 150, 450, 625, 750, 100, 10, 70, 140),
                new Street("Virginia", 14, "street", 3, "VIOLET", 60, 180, 500, 700, 900, 100, 12, 80, 160),
                new Railroad("Pennsylvania RR", 15, "railroad", 4, "BLACK", 25, 100, 200),
                new Street("Saint James", 16, "street", 3, "ORANGE", 70, 200, 550, 750, 950, 100, 14, 90, 180),
                new Tile("Community Chest II", 17, "community chest"),
                new Street("Tennessee", 18, "street", 3, "ORANGE", 70, 200, 550, 750, 950, 100, 14, 90, 180),
                new Street("New York", 19, "street", 3, "ORANGE", 80, 220, 600, 800, 1000, 100, 16, 100, 200),
                new Tile("Free Parking", 20, "Free Parking"),
                new Street("Kentucky Avenue", 21, "street", 3, "RED", 90, 250, 700, 875, 1050, 150, 18, 110, 220),
                new Tile("Chance II", 22, "chance"),
                new Street("Indiana Avenue", 23, "street", 3, "RED", 100, 300, 750, 925, 1100, 150, 20, 120, 240),
                new Street("Illinois Avenue", 24, "street", 3, "RED", 100, 300, 750, 925, 1100, 150, 20, 120, 240),
                new Railroad("Baltimore and Ohio RR", 25, "railroad", 4, "BLACK", 25, 100, 200),
                new Street("Atlantic", 26, "street", 3, "YELLOW", 110, 330, 800, 975, 1150, 150, 22, 130, 260),
                new Street("Ventnor", 27, "street", 3, "YELLOW", 110, 330, 800, 975, 1150, 150, 22, 130, 260),
                new Utility("Water Works", 28, "utility", 2, "WHITE", 75, 150),
                new Street("Marvin Gardens", 29, "street", 3, "YELLOW", 120, 360, 850, 1025, 1200, 150, 24, 280, 280),
                new Tile("Go to Jail", 30, "Go to Jail"),
                new Street("Pacific", 31, "street", 3, "DARKGREEN", 130, 390, 900, 1100, 1275, 200, 26, 150, 300),
                new Street("North Carolina", 32, "street", 3, "DARKGREEN", 130, 390, 900, 1100, 1275, 200, 26, 150, 300),
                new Tile("Community Chest III", 33, "community chest"),
                new Street("Pennsylvania", 34, "street", 3, "DARKGREEN", 150, 450, 1000, 1200, 1400, 200, 28, 160, 320),
                new Railroad("Short Line RR", 35, "railroad", 4, "BLACK", 25, 100, 200),
                new Tile("Chance III", 36, "chance"),
                new Street("Park Place", 37, "street", 2, "DARKBLUE", 175, 500, 1100, 1300, 1500, 200, 35, 175, 350),
                new Tile("Luxury Tax", 38, "Luxury Tax"),
                new Street("Boardwalk", 39, "street", 3, "DARKBLUE", 200, 600, 1400, 1700, 2000, 200, 50, 200, 400)
        );
    }

    @Override
    public void addGame(Game game) {
        allGames.put(game.getId(), game);
    }

    @Override
    public int getGameMapSize(){
        return allGames.size();
    }

    @Override
    public List<JsonObject> getAllGames() {
        List<JsonObject> listOfGames = new ArrayList<>();
        for (Map.Entry<String, Game> entry : allGames.entrySet()) {
            listOfGames.add(entry.getValue().showSpecificGameInfo());
        }
        return listOfGames;
    }

    @Override
    public Game getDummyGame(){
        Game dummyGame = new Game();
        return dummyGame;
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

    @Override
    public Game getGameById(String id){
        return allGames.get(id);
    }

    @Override
    public void joinGame(String gameId, String playerName, String icon) {
        Game game = getGameById(gameId);
        game.addPlayer(playerName, icon);
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

    /*public int buyProperty(Request request){
        Game game = getGameById(request.getGameId());
        String playerName = request.getPlayerName();
        Player player = game.getSpecificPlayer(playerName);
        String propertyName = request.getPropertyName();
        Tile tile = getTile(propertyName);
        if (tile.getType() == "street" || tile.getType() == "railroad" || tile.getType() == "utility"){
            Property tileToProperty = (Property) tile;
            if (player.getMoney() >= tileToProperty.getCost()){
                if (Boolean.TRUE.equals(checkIfAlreadyBought(tileToProperty.getName(), game))){
                    PlayerProperty boughtProperty = new PlayerProperty(tileToProperty.getName());
                    player.addProperties(boughtProperty);
                    player.removeMoney(tileToProperty.getCost());
                    return 200;
                }
                else {
                    throw new IllegalStateException("property is already bought");
                }
            }else{
                throw new IllegalStateException("you do not have enough money");
            }
        }else{
            throw new IllegalArgumentException("you can not buy a tile of any other type");
        }
    }*/

    //games/{gameId}/players/{playerName}/properties/{propertyName}/visitors/{debtorName}/rent
    public Player collectDebt(Request request){
        Game game = getGameById(request.getGameId());
        Player player = game.getSpecificPlayer(request.getParameterValue("playerName"));
        Player debtPlayer = game.getSpecificPlayer(request.getParameterValue("debtorName"));
        Tile tile = getTile(request.getPropertyName());
        if (Boolean.TRUE.equals(checkIfAlreadyBought(tile.getName(), game))){
            Property tileToProperty = (Property) tile;
            player.rem
        }
        return player;
    }

    public Boolean checkIfAlreadyBought(String name, Game game) {
        for (Player player : game.getPlayers()) {
            for (PlayerProperty playerProperty : player.getProperties()) {
                if (playerProperty.getProperty() == name) {
                    return false;
                }
            }
        }
        return true;
    }
}
