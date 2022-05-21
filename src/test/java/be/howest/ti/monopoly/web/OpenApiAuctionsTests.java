package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Game;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiAuctionsTests extends OpenApiTestsBase {

    @Test
    void getBankAuctions(final VertxTestContext testContext) {
        get(
                testContext,
                "/games/game-id/bank/auctions",
                "some-token",
                response -> assertNotYetImplemented(response, "getBankAuctions")
        );
    }

    @Test
    void getBankAuctionsUnauthorized(final VertxTestContext testContext) {
        get(
                testContext,
                "/games/game-id/bank/auctions",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void placeBidOnBankAuction(final VertxTestContext testContext) {
        service.setDelegate( new ServiceAdapter() {
            @Override
            public void placeBidOnBankAuction(String gameId, String bidder, int amount) {}
        });
        post(
                testContext,
                "/games/PilsoPoly/bank/auctions/some-property/bid",
                "PilsoPoly-Alice",
                new JsonObject()
                        .put("bidder", "Alice")
                        .put("amount", 100),
                this::assertOkResponse
        );
    }

    @Test
    void placeBidOnBankAuctionWithEmptyBody(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/bank/auctions/some-property/bid",
                "some-token",
                new JsonObject(),
                response -> assertErrorResponse(response, 400)
        );
    }

    @Test
    void placeBidOnBankAuctionWithoutBody(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/bank/auctions/some-property/bid",
                "some-token",
                response -> assertErrorResponse(response, 400)
        );
    }

    @Test
    void placeBidOnBankAuctionUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/bank/auctions/some-property/bid",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }


    @Test
    void getPlayerAuctions(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Auction getPlayerAuctions(String gameId) {
                return new Game(4, "PilsoPoly", 5).getAuction();
            }
        });


        get(
                testContext,
                "/games/PilsoPoly/players/Alice/auctions",
                "PilsoPoly-Alice",
                this::assertOkResponse
        );
    }

    @Test
    void getPlayerAuctionsUnauthorized(final VertxTestContext testContext) {
        get(
                testContext,
                "/games/PilsoPoly/players/Alice/auctions",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void startPlayerAuction(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/PilsoPoly/players/Alice/auctions/some-property",
                "some-token",
                new JsonObject()
                        .put("start-bid", 54)
                        .put("duration", 100)
                        .put("tradable", true),
                response -> assertNotYetImplemented(response, "startPlayerAuction")
        );
    }

    @Test
    void startPlayerAuctionUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/PilsoPoly/players/Alice/auctions/some-property",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void placeBidOnPlayerAuction(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/PilsoPoly/players/Alice/auctions/some-property/bid",
                "some-token",
                new JsonObject()
                        .put("bidder", "Bob")
                        .put("amount", 100),
                response -> assertNotYetImplemented(response, "placeBidOnPlayerAuction")
        );
    }

    @Test
    void placeBidOnPlayerAuctionWithoutBody(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/PilsoPoly/players/Alice/auctions/some-property/bid",
                "some-token",
                response -> assertErrorResponse(response, 400)
        );
    }

    @Test
    void placeBidOnPlayerAuctionWithEmptyBody(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/PilsoPoly/players/Alice/auctions/some-property/bid",
                "some-token",
                new JsonObject(),
                response -> assertErrorResponse(response, 400)
        );
    }

    @Test
    void placeBidOnPlayerAuctionUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/PilsoPoly/players/Alice/auctions/some-property/bid",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

}
