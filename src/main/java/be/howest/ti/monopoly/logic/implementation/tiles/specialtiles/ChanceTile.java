package be.howest.ti.monopoly.logic.implementation.tiles.specialtiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.community_and_chance.CommunityOrChanceCard;
import be.howest.ti.monopoly.logic.implementation.community_and_chance.specific_community_or_chance.*;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class ChanceTile extends Tile {

    private static final List<CommunityOrChanceCard> chanceCards = createChanceCards();
    private static final Random random = new SecureRandom();

    public ChanceTile(String name, int position) {
        super(name, position, "chance", "Draw a chance card!", "chance");
    }
    public static List<CommunityOrChanceCard> createChanceCards(){
        return List.of(
                new GetOutOfJailFreeCard("Get Out of Jail Free"),
                new PayOrReceive("Your building loan matures. Collect $150", 150),
                new PayOrReceive("Speeding fine $15", -15),
                new PayOrReceive("Bank pays you dividend of $50", 50),
                new PayOrReceive("Your building loan matures. Collect $150", 150),
                new GoToTile("Go to Jail. Go directly to Jail, do not pass Go, do not collect $200", 10),
                new GoToTile("Advance to Go (Collect $200)", 0),
                new GoToTile("Advance to Boardwalk", 39),
                new GoToTile("Advance to St. Charles Place. If you pass Go, collect $200", 11),
                new GoToTile("Advance to Illinois Avenue. If you pass Go, collect $200", 24),
                new GoToTile("Take a trip to Reading Railroad. If you pass Go, collect $200",5),
                new CollectOrGiveEveryPlayer("You have been elected Chairman of the Board. Pay each player $50", -50),
                new Repairs("Make general repairs on all your property. For each house pay $25. For each hotel pay $100", 25, 100),
                new AdvanceToNearest("Advance to the nearest Utility. If unowned, you may buy it from the Bank", "utility"),
                new AdvanceToNearest("Advance to the nearest Railroad. If unowned, you may buy it from the Bank", "railroad")
        );
    }

    public static CommunityOrChanceCard getRandomChanceCardAction() {
        int randomNumber = random.nextInt(chanceCards.size());
        return chanceCards.get(randomNumber);
    }

    @Override
    public void tileAction(Game game, Player player){
        CommunityOrChanceCard chanceCard = getRandomChanceCardAction();
        super.setDescription(chanceCard.getDescription());
        chanceCard.cardAction(game, player);
    }
}
