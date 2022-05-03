package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Game;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiGameInfoTests extends OpenApiTestsBase {

    @Test
    void getGame(final VertxTestContext testContext) {
<<<<<<< HEAD
        service.setDelegate(new ServiceAdapter(){
            public Game getGameState(){
                Game dummyGame = new Game();
                return dummyGame;
            }
        });
=======
>>>>>>> ae4490bee265ed5fffc892b887f6943e97d1c855
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
<<<<<<< HEAD
            public Game getGameState(){
                Game dummyGame = new Game();
                return dummyGame;
=======
            @Override
            public List<String > getCommunityCards(){
                return Collections.emptyList();
>>>>>>> ae4490bee265ed5fffc892b887f6943e97d1c855
            }
        });
        get(
                testContext,
                "/games/dummy",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }
}
