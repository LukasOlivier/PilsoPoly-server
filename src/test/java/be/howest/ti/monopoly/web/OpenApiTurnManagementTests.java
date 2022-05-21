package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Game;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiTurnManagementTests extends OpenApiTestsBase {

    @Test
    void rollDice(final VertxTestContext testContext) {
        service.setDelegate( new ServiceAdapter() {

            @Override
            public Game rollDice(String playerName, String gameId) {
                return new Game(2,"PilsoPoly", 1);
            }

        });
        post(
                testContext,
                "/games/PilsoPoly/players/Alice/dice",
                "PilsoPoly-Alice",
                this::assertOkResponse
        );
    }

    @Test
    void rollDiceUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/dice",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void declareBankruptcy(final VertxTestContext testContext) {
        service.setDelegate( new ServiceAdapter() {

            @Override
            public void setBankrupt(String playerName,String gameId) {}

        });
        post(
                testContext,
                "/games/PilsoPoly/players/Alice/bankruptcy",
                "PilsoPoly-Alice",
                this::assertOkResponse
        );
    }

    @Test
    void declareBankruptcyUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/bankruptcy",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }
}
