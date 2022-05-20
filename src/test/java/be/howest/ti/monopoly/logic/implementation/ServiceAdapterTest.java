package be.howest.ti.monopoly.logic.implementation;
import be.howest.ti.monopoly.logic.ServiceAdapter;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceAdapterTest {
    ServiceAdapter service = new ServiceAdapter();
    Game game = new Game(5,"PilsoPoly",0);

    @Test
    void testErrors() {
        assertThrows(UnsupportedOperationException.class, () -> {
            service.getVersion();
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.getCommunityCards();
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.getTiles();
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.addGame(game);
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.getGame();
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.getDummyGame();
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.getAllGames();
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.mapToList(new HashMap<>());
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.clearGameList();
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.getGameMapSize();
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.getChanceCards();
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.getTile(1);
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.filterGamesBy("true","1","PilsoPoly");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.getTile("Cara");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.buyProperty("PilsoPoly_1","Alice","Cara");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.dontBuyProperty("PilsoPoly_1","Alice","Cara");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.joinGame("PilsoPoly_1","Alice","icon");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.placeBidOnBankAuction("PilsoPoly_1","Alice",100);
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.collectDebt("PilsoPoly_1","Bob","Alice","Cara");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.getPlayerAuctions("PilsoPoly_1");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.getOutOfJailFine("ALice","PilsoPoly_1");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.setBankrupt("ALice","PilsoPoly_1");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.useComputeTax("ALice","PilsoPoly_1");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.getOutOfJailFine("ALice","PilsoPoly_1");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.useEstimateTax("ALice","PilsoPoly_1");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.getGames();
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.rollDice("Alice","PilsoPoly_1");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.buyHouse("PilsoPoly_1","Alice","Cara");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.sellHouse("PilsoPoly_1","Alice","Cara");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.buyHotel("PilsoPoly_1","Alice","Cara");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.sellHotel("PilsoPoly_1","Alice","Cara");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.settleMortgage("PilsoPoly_1","Alice","Cara");
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            service.takeMortgage("PilsoPoly_1","Alice","Cara");
        });
    }
}
