package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiBuyingPropertyTests extends OpenApiTestsBase {

    @Test
    void buyProperty(final VertxTestContext testContext) {
        service.setDelegate( new ServiceAdapter() {
            @Override
            public void buyProperty(String gameName,String playerName,String propertyName) {}

        });
        post(
                testContext,
                "/games/PilsoPoly/players/Alice/properties/some-property",
                "PilsoPoly-Alice",
                this::assertOkResponse
        );
    }

    @Test
    void buyPropertyUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/properties/some-property",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void dontBuyProperty(final VertxTestContext testContext) {

        service.setDelegate( new ServiceAdapter() {

            @Override
            public void dontBuyProperty(String gameId, String bidder, String property) {}

        } );

        delete(
                testContext,
                "/games/PilsoPoly/players/Alice/properties/some-property",
                "PilsoPoly-Alice",
                this::assertOkResponse
        );
    }

    @Test
    void dontBuyPropertyUnauthorized(final VertxTestContext testContext) {
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }
}
