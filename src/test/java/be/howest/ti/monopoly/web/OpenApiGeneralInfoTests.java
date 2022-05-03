package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Tile;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class OpenApiGeneralInfoTests extends OpenApiTestsBase {

    @Test
    void getInfo(final VertxTestContext testContext) {
        String versionForTest = "test-version";
        service.setDelegate(new ServiceAdapter() {
            @Override
            public String getVersion() {
                return versionForTest;
            }
        });

        get(
                testContext,
                "/",
                null,
                response -> {
                    assertEquals(200, response.statusCode());
                    assertEquals("monopoly", response.bodyAsJsonObject().getString("name"));
                    assertEquals(versionForTest, response.bodyAsJsonObject().getString("version"));
                }
        );
    }

    @Test
    void getTiles(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public List<Tile> getTiles() {
                return Collections.emptyList();
            }
        });
        get(
                testContext,
                "/tiles",
                null,
                response -> assertOkResponse(response)
        );
    }


    @Test
    void getTileByName(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Tile getTile(String name) {
                return new Tile("Go", 0, "Go", "Go");
            }
        });
        get(
                testContext,
                "/tiles/Go",
                null,
                response -> assertOkResponse(response)
        );
    }

    @Test
    void getTileById(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Tile getTile(int position) {
                return new Tile("Go", 0, "Go", "Go");
            }
        });
        get(
                testContext,
                "/tiles/0",
                null,
                response -> assertOkResponse(response)
        );
    }


    @Test
    void getChance(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter() {
            @Override
            public List<String> getChanceCards() {
                return Collections.emptyList();
            }
        });
        get(
                testContext,
                "/chance",
                null,
                response -> assertOkResponse(response)
        );
    }


    @Test
    void getCommunityChest(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public List<String > getCommunityCards(){
                return Collections.emptyList();
            }
        });
        get(
                testContext,
                "/community-chest",
                null,
                response -> assertOkResponse(response)
        );
    }

}
