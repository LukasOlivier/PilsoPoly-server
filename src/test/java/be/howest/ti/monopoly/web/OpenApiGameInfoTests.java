package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Game;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiGameInfoTests extends OpenApiTestsBase {
    @Test
    void getGame(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Game getGameById(String gameId){
                Game game = new Game(2,"PilsoPoly",1);
                game.addPlayer("Robin", "icon");
                return game;
            }
        });
        get(
                testContext,
                "/games/PilsoPoly_2",
                "PilsoPoly_2-Robin",
                this::assertOkResponse
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
}
