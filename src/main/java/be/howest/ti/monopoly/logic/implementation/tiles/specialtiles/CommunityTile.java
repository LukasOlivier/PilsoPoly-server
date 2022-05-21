package be.howest.ti.monopoly.logic.implementation.tiles.specialtiles;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.community_and_chance.CommunityOrChanceCard;
import be.howest.ti.monopoly.logic.implementation.community_and_chance.specific_community_or_chance.*;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class CommunityTile extends Tile {

    private static final List<CommunityOrChanceCard> communityCards = createCommunityCards();
    private static final Random random = new SecureRandom();

    public CommunityTile(String name, int position) {
        super(name, position, "community chest", "Draw a community card!", "community");
    }

    public static List<CommunityOrChanceCard> createCommunityCards(){
        return List.of(
                new PayOrReceive("Doctor's fee. Pay $50", -50),
                new PayOrReceive("Pay hospital fees of $100", -100),
                new PayOrReceive("Pay school fees of $50", -50),
                new PayOrReceive("Bank error in your favor. Collect $200", 200),
                new PayOrReceive("From sale of stock you get $50", 50),
                new PayOrReceive("Holiday fund matures. Receive $100", 100),
                new PayOrReceive("Income tax refund. Collect $20", 20),
                new PayOrReceive("Life insurance matures. Collect $100", 100),
                new PayOrReceive("Receive $25 consultancy fee", 25),
                new PayOrReceive("You inherit $100", 100),
                new PayOrReceive("You have won second prize in a beauty contest. Collect $10", 10),
                new GoToTile("Go to Jail. Go directly to jail, do not pass Go, do not collect $200", 0),
                new GoToTile("Advance to Go (Collect $200)", 0),
                new GetOutOfJailFreeCard("Get Out of Jail Free"),
                new CollectOrGiveEveryPlayer("It is your birthday. Collect $10 from every player", 10),
                new Repairs("You are assessed for street repair. $40 per house. $115 per hotel", 40, 115)
        );
    }

    public static CommunityOrChanceCard getRandomCommunityCard(){
        int randomNumber = random.nextInt(communityCards.size());
        return communityCards.get(randomNumber);
    }

    @Override
    public void tileAction(Game game, Player player){
        CommunityOrChanceCard communityCard = getRandomCommunityCard();
        super.setDescription(communityCard.getDescription());
        communityCard.cardAction(game, player);
    }
}
