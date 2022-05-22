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
                new PayOrReceive("Doctor's fee. Pay M50.", -50),
                new PayOrReceive("Pay hospital fees of M100.", -100),
                new PayOrReceive("Pay school fees of M50.", -50),
                new PayOrReceive("Bank error in your favor. You received M200.", 200),
                new PayOrReceive("Uou got M50 from sale of stock.", 50),
                new PayOrReceive("Holiday fund matures. You received M100.", 100),
                new PayOrReceive("Income tax refund. You got M20.", 20),
                new PayOrReceive("Life insurance matures. You got M100.", 100),
                new PayOrReceive("Receive M25 consultancy fee.", 25),
                new PayOrReceive("You inherit M100.", 100),
                new PayOrReceive("You have won second prize in a beauty contest. Collect M10.", 10),
                new GoToTile("Go to Jail. Go directly to jail, do not pass Go, do not collect M200.", 0),
                new GoToTile("Advance to Go (Collect M200).", 0),
                new GetOutOfJailFreeCard("You received a get out of jail free card!"),
                new CollectOrGiveEveryPlayer("It is your birthday. Collect M10 from every player.", 10),
                new Repairs("You are assessed for street repair. M40 per house. M115 per hotel.", 40, 115)
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
