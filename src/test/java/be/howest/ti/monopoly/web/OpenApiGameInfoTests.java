package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.GameState;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;


class OpenApiGameInfoTests extends OpenApiTestsBase {

    @Test
    void getGame(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public GameState getGameState(){
                GameState dummyGame = new GameState(2);
                dummyGame.addPlayers("Robin", "glass");
                dummyGame.addPlayers("Sibren", "beer");
                return dummyGame;
            }
        });
        get(
                testContext,
                "/games/game-id",
                "some-token",
                response -> assertOkResponse(response)
        );
    }

    @Test
    void getGameUnauthorized(final VertxTestContext testContext) {
        get(
                testContext,
                "/games/game-id",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void getDummyGame(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public GameState getGameState(){
                GameState dummyGame = new GameState(2);
                dummyGame.addPlayers("Robin", "glass");
                dummyGame.addPlayers("Sibren", "beer");
                return dummyGame;
            }
        });
        get(
                testContext,
                "/games/dummy",
                null,
                response -> assertOkResponse(response)
        );
    }
}
