package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiTaxManagementTests extends OpenApiTestsBase {

    @Test
    void useEstimateTax(final VertxTestContext testContext) {
        service.setDelegate( new ServiceAdapter() {

            @Override
            public void useEstimateTax(Request request) {}

        });

        post(
                testContext,
                "/games/game-id/players/Alice/tax/estimate",
                "some-token",
                this::assertOkResponse
        );
    }

    @Test
    void useEstimateTaxUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/tax/estimate",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void useComputeTax(final VertxTestContext testContext) {
        service.setDelegate( new ServiceAdapter() {

            @Override
            public void useEstimateTax(Request request) {}

        });

        post(
                testContext,
                "/games/game-id/players/Alice/tax/compute",
                "some-token",
                this::assertOkResponse
        );
    }

    @Test
    void useComputeTaxUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/tax/compute",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }
}
