package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.PlayerProperty;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;

import java.util.List;


class OpenApiImprovingPropertyTests extends OpenApiTestsBase {

    @Test
    void buyHouse(final VertxTestContext testContext) {

        service.setDelegate( new ServiceAdapter() {

            @Override
            public void buyHouse(String gameId, String playerName, String propertyName) {}

        });

        post(
                testContext,
                "/games/PilsoPoly/players/Alice/properties/some-property/houses",
                "PilsoPoly-Alice",
                this::assertOkResponse
        );
    }

    @Test
    void buyHouseUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/houses",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void sellHouse(final VertxTestContext testContext) {

        service.setDelegate( new ServiceAdapter() {

            @Override
            public void sellHouse(String gameId, String playerName, String propertyName) {}

        });

        delete(
                testContext,
                "/games/PilsoPoly/players/Alice/properties/some-property/houses",
                "PilsoPoly-Alice",
                this::assertOkResponse
        );
    }

    @Test
    void sellHouseUnauthorized(final VertxTestContext testContext) {
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/houses",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }


    @Test
    void buyHotel(final VertxTestContext testContext) {

        service.setDelegate( new ServiceAdapter() {

            @Override
            public void buyHotel(String gameId, String playerName, String propertyName) {}

        });

        post(
                testContext,
                "/games/PilsoPoly/players/Alice/properties/some-property/hotel",
                "PilsoPoly-Alice",
                this::assertOkResponse
        );
    }

    @Test
    void buyHotelUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/hotel",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void sellHotel(final VertxTestContext testContext) {

        service.setDelegate( new ServiceAdapter() {

            @Override
            public void sellHotel(String gameId, String playerName, String propertyName) {}

        });

        delete(
                testContext,
                "/games/PilsoPoly/players/Alice/properties/some-property/hotel",
                "PilsoPoly-Alice",
                this::assertOkResponse
        );
    }

    @Test
    void sellHotelUnauthorized(final VertxTestContext testContext) {
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/hotel",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }
}
