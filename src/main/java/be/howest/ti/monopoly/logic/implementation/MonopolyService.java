package be.howest.ti.monopoly.logic.implementation;
import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import io.vertx.core.json.JsonObject;

import java.util.*;


public class MonopolyService extends ServiceAdapter {
    Map<String, Game> allGames = new HashMap<>();

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public List<String > getCommunityCards() {
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
                new Tile("Go", 0, "Go", "Go"),
                new StreetTile("Mediterranean", 1, "street", "Mediterranean", 60, 30, 2, 10, 30, 90, 160, 250, 50, "PURPLE", 2, "PURPLE"),
                new Tile("Community Chest I", 2, "community chest", "Community_Chest_I"),
                new StreetTile("Baltic", 3, "street", "Baltic", 60, 30, 4, 20, 60, 180, 320, 450, 50, "PURPLE", 2, "PURPLE"),
                new Tile("Tax Income", 4, "Tax Income", "Tax_Income"),
                new RailroadTile("Reading RR", 5, "railroad", "Reading_RR", 200, 100, 25, 4, "BLACK"),
                new StreetTile("Oriental", 6, "street", "Oriental", 100, 50, 6, 30, 90, 270, 400, 550, 50, "LIGHTBLUE", 3, "LIGHTBLUE"),
                new Tile("Chance I", 7, "chance", "Chance_I"),
                new StreetTile("Vermont", 8, "street", "Vermont", 100, 50, 6, 30, 90, 270, 400, 550, 50, "LIGHTBLUE", 3, "LIGHTBLUE"),
                new StreetTile("Connecticut", 9, "street", "Connecticut", 120, 60, 8, 40, 100, 300, 450, 600, 50, "LIGHTBLUE", 3, "LIGHTBLUE"),
                new Tile("Jail", 10, "Jail", "Jail"),
                new StreetTile("Saint Charles Place", 11, "street", "Saint_Charles_Place", 140, 70, 10, 50, 150, 450, 625, 750, 100, "VIOLET", 3, "VIOLET"),
                new UtilityTile("Electric Company", 12, "utility", "Electric_Company", 150, 75, "4 or 10 times the dice roll", 2, "WHITE"),
                new StreetTile("States", 13, "street", "States", 140, 70, 10, 50, 150, 450, 625, 750, 100, "VIOLET", 3, "VIOLET"),
                new StreetTile("Virginia", 14, "street", "Virginia", 160, 80, 12, 60, 180, 500, 700, 900, 100, "VIOLET", 3, "VIOLET"),
                new RailroadTile("Pennsylvania RR", 15, "railroad", "Pennsylvania_RR", 200, 100, 25, 4, "BLACK"),
                new StreetTile("Saint James", 16, "street", "Saint_James", 180, 90, 14, 70, 200, 550, 750, 950, 100, "ORANGE", 3, "ORANGE"),
                new Tile("Community Chest II", 17, "community chest", "Community_Chest_II"),
                new StreetTile("Tennessee", 18, "street", "Tennessee", 180, 90, 14, 70, 200, 550, 750, 950, 100, "ORANGE", 3, "ORANGE"),
                new StreetTile("New York", 19, "street", "New_York", 200, 100, 16, 80, 220, 600, 800, 1000, 100, "ORANGE", 3, "ORANGE"),
                new Tile("Free Parking", 20, "Free Parking", "Free_Parking"),
                new StreetTile("Kentucky Avenue", 21, "street", "Kentucky_Avenue", 220, 110, 18, 90, 250, 700, 875, 1050, 150, "RED", 3, "RED"),
                new Tile("Chance II", 22, "chance", "Chance_II"),
                new StreetTile("Indiana Avenue", 23, "street", "Indiana_Avenue", 220, 110, 18, 90, 250, 700, 875, 1050, 150, "RED", 3, "RED"),
                new StreetTile("Illinois Avenue", 24, "street", "Illinois_Avenue", 240, 120, 20, 100, 300, 750, 925, 1100, 150, "RED", 3, "RED"),
                new RailroadTile("Baltimore and Ohio RR", 25, "railroad", "Baltimore_and_Ohio_RR", 200, 100, 25, 4, "BLACK"),
                new StreetTile("Atlantic", 26, "street", "Atlantic", 260, 130, 22, 110, 330, 800, 975, 1150, 150, "YELLOW", 3, "YELLOW"),
                new StreetTile("Ventnor", 27, "street", "Ventnor", 260, 130, 22, 110, 330, 800, 975, 1150, 150, "YELLOW", 3, "YELLOW"),
                new UtilityTile("Water Works", 28, "utility", "Water_Works", 150, 75, "4 or 10 times the dice roll", 2, "WHITE"),
                new StreetTile("Marvin Gardens", 29, "street", "Marvin_Gardens", 280, 140, 24, 120, 360, 850, 1025, 1200, 150, "YELLOW", 3, "YELLOW"),
                new Tile("Go to Jail", 30, "Go to Jail", "Go_to_Jail"),
                new StreetTile("Pacific", 31, "street", "Pacific", 300, 150, 26, 130, 390, 900, 1100, 1275, 200, "DARKGREEN", 3, "DARKGREEN"),
                new StreetTile("North Carolina", 32, "street", "North_Carolina", 300, 150, 26, 130, 390, 900, 1100, 1275, 200, "DARKGREEN", 3, "DARKGREEN"),
                new Tile("Community Chest III", 33, "community chest", "Community_Chest_III"),
                new StreetTile("Pennsylvania", 34, "street", "Pennsylvania", 320, 160, 28, 150, 450, 1000, 1200, 1400, 200, "DARKGREEN", 3, "DARKGREEN"),
                new RailroadTile("Short Line RR", 35, "railroad", "Short_Line_RR", 200, 100, 25, 4, "BLACK"),
                new Tile("Chance III", 36, "chance", "Chance_III"),
                new StreetTile("Park Place", 37, "street", "Park_Place", 350, 175, 35, 175, 500, 1100, 1300, 1500, 200, "DARKBLUE", 2, "DARKBLUE"),
                new Tile("Luxury Tax", 38, "Luxury Tax", "Luxury_Tax"),
                new StreetTile("Boardwalk", 39, "street", "Boardwalk", 400, 200, 50, 200, 600, 1400, 1700, 2000, 200, "DARKBLUE", 2, "DARKBLUE")
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
}
