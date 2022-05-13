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
                return new Game(2,"PilsoPoly",1);
            }

        });
        get(
                testContext,
                "/games/PilsoPoly",
                "PilsoPoly",
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
