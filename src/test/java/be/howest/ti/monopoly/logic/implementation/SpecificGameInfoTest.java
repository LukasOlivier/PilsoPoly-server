package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.web.views.SpecificGameInfo;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SpecificGameInfoTest {

    int gameMapSize = 5;
    Game testGame = new Game(2, "PilsoPoly", gameMapSize);

    Player Alice;
    Player Bob;

    @BeforeEach
    void addPlayers(){
        testGame.addPlayer("Alice", "icon");
        Alice = testGame.getSpecificPlayer("Alice");
    }

    @Test
    void gameInfoTest() {
        List<JsonObject> expected = new ArrayList<>();
        expected.add(new JsonObject()
                .put("name", "Alice")
                .put("icon", "icon"));
        SpecificGameInfo gameInfo = new SpecificGameInfo(testGame);
        assertEquals(expected, gameInfo.getPlayers());
    }
}
