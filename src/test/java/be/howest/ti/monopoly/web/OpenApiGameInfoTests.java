package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Game;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiGameInfoTests extends OpenApiTestsBase {
    @Test
    void getGame(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            public Game getGameState(){
                Game dummyGame = new Game();
                return dummyGame;
            }
        });
        get(
                testContext,
                "/games/game-id",
                "some-token",
                response -> assertErrorResponse(response, 401)
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
            public Game getDummyGame(){
                return new Game();

            }

        });
        get(
                testContext,
                "/games/dummy",
                null,
                this::assertOkResponse
        );
    }
}
