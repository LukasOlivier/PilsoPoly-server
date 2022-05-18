package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiPrisonTests extends OpenApiTestsBase {

    @Test
    void getOutOfJailFine(final VertxTestContext testContext) {
        service.setDelegate( new ServiceAdapter() {
            @Override
            public void getOutOfJailFine(String playerName,String gameId) {}
        });
        post(
                testContext,
                "/games/PilsoPoly/prison/Alice/fine",
                "PilsoPoly-Alice",
                this::assertOkResponse
        );
    }

    @Test
    void getOutOfJailFineUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/prison/Alice/fine",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void getOutOfJailFree(final VertxTestContext testContext) {
        service.setDelegate( new ServiceAdapter() {
            @Override
            public void getOutOfJailFree(String playerName,String gameId) {}
        });
        post(
                testContext,
                "/games/PilsoPoly/prison/Alice/free",
                "PilsoPoly-Alice",
                this::assertOkResponse
        );
    }

    @Test
    void getOutOfJailFreeUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/prison/Alice/free",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }
}
