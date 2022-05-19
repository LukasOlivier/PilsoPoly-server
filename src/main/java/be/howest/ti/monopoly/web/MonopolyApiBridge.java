package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.IService;
import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.exceptions.InsufficientFundsException;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.MonopolyService;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.web.exceptions.ForbiddenAccessException;
import be.howest.ti.monopoly.web.exceptions.InvalidRequestException;
import be.howest.ti.monopoly.web.exceptions.NotYetImplementedException;
import be.howest.ti.monopoly.web.tokens.InvalidTokenException;
import be.howest.ti.monopoly.web.tokens.MonopolyUser;
import be.howest.ti.monopoly.web.tokens.PlainTextTokens;
import be.howest.ti.monopoly.web.tokens.TokenManager;
import be.howest.ti.monopoly.web.views.SpecificGameInfo;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BearerAuthHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.validation.RequestParameter;

import javax.naming.AuthenticationException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MonopolyApiBridge {

    private static final Logger LOGGER = Logger.getLogger(MonopolyApiBridge.class.getName());

    private final IService service;
    private final TokenManager tokenManager;

    public MonopolyApiBridge(IService service, TokenManager tokenManager) {
        this.service = service;
        this.tokenManager = tokenManager;
    }

    public MonopolyApiBridge() {
        this(
                new MonopolyService(),
                new PlainTextTokens()
        );
    }


    public Router buildRouter(RouterBuilder routerBuilder) {
        LOGGER.log(Level.INFO, "Installing CORS handlers");
        routerBuilder.rootHandler(createCorsHandler());

        LOGGER.log(Level.INFO, "Installing security handlers");
        routerBuilder.securityHandler("playerAuth", BearerAuthHandler.create(tokenManager));


        LOGGER.log(Level.INFO, "Installing Failure handlers");
        routerBuilder.operations().forEach(op -> op.failureHandler(this::onFailedRequest));

        LOGGER.log(Level.INFO, "Installing Actual handlers");

        // General Game and API Info
        routerBuilder.operation("getInfo").handler(this::getInfo);
        routerBuilder.operation("getTiles").handler(this::getTiles);
        routerBuilder.operation("getTile").handler(this::getTile);
        routerBuilder.operation("getChance").handler(this::getChance);
        routerBuilder.operation("getCommunityChest").handler(this::getCommunityChest);

        // Managing Games
        routerBuilder.operation("getGames").handler(this::getGames);
        routerBuilder.operation("createGame").handler(this::createGame);
        routerBuilder.operation("joinGame").handler(this::joinGame);
        routerBuilder.operation("clearGameList").handler(this::clearGameList);

        // Game Info
        routerBuilder.operation("getGame").handler(this::getGame);

        // Turn Management
        routerBuilder.operation("rollDice").handler(this::rollDice);
        routerBuilder.operation("declareBankruptcy").handler(this::declareBankruptcy);

        // Tax Management
        routerBuilder.operation("useEstimateTax").handler(this::useEstimateTax);
        routerBuilder.operation("useComputeTax").handler(this::useComputeTax);

        // Buying property
        routerBuilder.operation("buyProperty").handler(this::buyProperty);
        routerBuilder.operation("dontBuyProperty").handler(this::dontBuyProperty);

        // Improving property
        routerBuilder.operation("buyHouse").handler(this::buyHouse);
        routerBuilder.operation("sellHouse").handler(this::sellHouse);
        routerBuilder.operation("buyHotel").handler(this::buyHotel);
        routerBuilder.operation("sellHotel").handler(this::sellHotel);

        // Mortgage
        routerBuilder.operation("takeMortgage").handler(this::takeMortgage);
        routerBuilder.operation("settleMortgage").handler(this::settleMortgage);

        // Interaction with other player
        routerBuilder.operation("collectDebt").handler(this::collectDebt);
        routerBuilder.operation("trade").handler(this::trade);

        // Prison
        routerBuilder.operation("getOutOfJailFine").handler(this::getOutOfJailFine);
        routerBuilder.operation("getOutOfJailFree").handler(this::getOutOfJailFree);

        // Auctions
        routerBuilder.operation("getBankAuctions").handler(this::getBankAuctions);
        routerBuilder.operation("placeBidOnBankAuction").handler(this::placeBidOnBankAuction);
        routerBuilder.operation("getPlayerAuctions").handler(this::getPlayerAuctions);
        routerBuilder.operation("startPlayerAuction").handler(this::startPlayerAuction);
        routerBuilder.operation("placeBidOnPlayerAuction").handler(this::placeBidOnPlayerAuction);


        LOGGER.log(Level.INFO, "All handlers are installed");
        return routerBuilder.createRouter();
    }

    private void authorizationCheck(Request request) throws AuthenticationException {
        if (!request.isAuthorized(request.getPathParameterValueString("gameId"), request.getPathParameterValueString("playerName"))) {
            throw new AuthenticationException();
        }
    }

    private void getInfo(RoutingContext ctx) {
        Response.sendJsonResponse(ctx, 200, new JsonObject()
                .put("name", "monopoly")
                .put("version", service.getVersion())
        );
    }

    private void getTiles(RoutingContext ctx) {
        Response.sendJsonResponse(ctx, 200, service.getTiles());
    }

    private void getTile(RoutingContext ctx) {
        Request request = Request.from(ctx);

        Tile tile;
        if (request.isNumber("tileId")) {
            int position = request.getPathParameterValueInteger("tileId");
            tile = service.getTile(position);
        } else {
            String name = request.getPathParameterValueString("tileId");
            tile = service.getTile(name);
        }

        Response.sendJsonResponse(ctx, 200, tile);
    }

    private void getChance(RoutingContext ctx) {
        Response.sendJsonResponse(ctx, 200, service.getChanceCards());
    }

    private void getCommunityChest(RoutingContext ctx) {
        Response.sendJsonResponse(ctx, 200, service.getCommunityCards());
    }

    private void clearGameList(RoutingContext ctx) {
        service.clearGameList();
        Response.sendOkResponse(ctx);
    }

    private void createGame(RoutingContext ctx) {
        Request request = Request.from(ctx);
        try {
            if (request.getRequestParameters() == null) {
                throw new InvalidRequestException("empty body");
            }
            int numberOfPlayers = request.getBodyValueInteger("numberOfPlayers");
            String gameId = request.getBodyValueString("prefix");
            Game createdGame = new Game(numberOfPlayers, gameId, service.getGameMapSize());
            service.addGame(createdGame);
            SpecificGameInfo specificGameInfo = new SpecificGameInfo(createdGame);
            service.addGame(createdGame);
            Response.sendJsonResponse(ctx, 200, specificGameInfo);
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("failed to create game!");
        }
    }

    private void getGames(RoutingContext ctx) {
        Request request = Request.from(ctx);
        Map<String, Game> filteredMapOfGames = service.getAllGames();
        RequestParameter isStarted = request.getRequestParameters().queryParameter("started");
        RequestParameter numberOfPlayers = request.getRequestParameters().queryParameter("numberOfPlayers");
        RequestParameter prefix = request.getRequestParameters().queryParameter("prefix");
        if (isStarted != null) {
            filteredMapOfGames = filterGamesByStarted(isStarted.getBoolean(), filteredMapOfGames);
        }
        if (numberOfPlayers != null) {
            filteredMapOfGames = filterGamesByNumberOfPlayers(numberOfPlayers.getInteger(), filteredMapOfGames);
        }
        if (prefix != null) {
            filteredMapOfGames = filterGamesByPrefix(prefix.toString(), filteredMapOfGames);
        }
        List<SpecificGameInfo> listOfGames = new ArrayList<>();
        for (Game game : filteredMapOfGames.values()) {
            listOfGames.add(new SpecificGameInfo(game));
        }
        Response.sendJsonResponse(ctx, 200, listOfGames);
    }

    public Map<String, Game> filterGamesByStarted(boolean isStarted, Map<String, Game> mapToFilter) {
        Map<String, Game> filteredMap = new HashMap<>();
        for (Map.Entry<String, Game> entry : mapToFilter.entrySet()) {
            if (entry.getValue().isStarted() == isStarted) {
                filteredMap.put(entry.getKey(), entry.getValue());
            }
        }
        return filteredMap;
    }

    public Map<String, Game> filterGamesByPrefix(String prefix, Map<String, Game> mapToFilter) {
        Map<String, Game> filteredMap = new HashMap<>();
        for (Map.Entry<String, Game> entry : mapToFilter.entrySet()) {
            if (Objects.equals(entry.getValue().getId().split("_")[0], prefix)) {
                filteredMap.put(entry.getKey(), entry.getValue());
            }
        }
        return filteredMap;
    }

    public Map<String, Game> filterGamesByNumberOfPlayers(int numberOfPlayers, Map<String, Game> mapToFilter) {
        Map<String, Game> filteredMap = new HashMap<>();
        for (Map.Entry<String, Game> entry : mapToFilter.entrySet()) {
            if (entry.getValue().getNumberOfPlayers() == numberOfPlayers) {
                filteredMap.put(entry.getKey(), entry.getValue());
            }
        }
        return filteredMap;
    }

    private void joinGame(RoutingContext ctx) {
        try {
            Request request = Request.from(ctx);
            String playerName = request.getBodyValueString("playerName");
            String icon = request.getBodyValueString("icon");
            String gameId = request.getPathParameterValueString("gameId");
            service.joinGame(gameId, playerName, icon);
            String playerToken = tokenManager.createToken(
                    new MonopolyUser(gameId, playerName)
            );
            Response.sendJsonResponse(ctx, 200, new JsonObject()
                    .put("token", playerToken)
            );
        } catch (IllegalArgumentException exceptions) {
            throw new IllegalArgumentException(exceptions);
        }
    }

    private void getGame(RoutingContext ctx) {
        Request request = Request.from(ctx);
        String gameId = request.getPathParameterValueString("gameId");
        Game game = service.getGameById(gameId);
        try {
            authenticateGetGame(request,game, gameId);
        } catch (InvalidTokenException exception) {
            throw new InvalidTokenException();
        }
        Response.sendJsonResponse(ctx, 200, game);
    }

    private void authenticateGetGame(Request request,Game game, String gameId) {
        List<Player> players = game.getPlayers();
        for (Player player : players) {
            if (request.isAuthorized(gameId, player.getName())) {
                return;
            }
        }
        throw new InvalidTokenException();
    }

    private void useEstimateTax(RoutingContext ctx) {
        Request request = Request.from(ctx);
        String playerName = request.getPathParameterValueString("playerName");
        String gameId = request.getPathParameterValueString("gameId");
        try {
            service.useEstimateTax(playerName,gameId);
            Response.sendOkResponse(ctx);
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("Something went wrong with useEstimateTax");
        }
    }

    private void useComputeTax(RoutingContext ctx) {
        Request request = Request.from(ctx);
        String playerName = request.getPathParameterValueString("playerName");
        String gameId = request.getPathParameterValueString("gameId");
        try {
            service.useComputeTax(playerName,gameId);
            Response.sendOkResponse(ctx);
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("something went wrong with useComputeTax");
        }
    }

    private void rollDice(RoutingContext ctx) {
        Request request = Request.from(ctx);
        String playerName = request.getPathParameterValueString("playerName");
        String gameId = request.getPathParameterValueString("gameId");
        Response.sendJsonResponse(ctx, 200, service.rollDice(playerName,gameId));
    }

    private void declareBankruptcy(RoutingContext ctx) {
        Request request = Request.from(ctx);
        String playerName = request.getPathParameterValueString("playerName");
        String gameId = request.getPathParameterValueString("gameId");
        try {
            service.setBankrupt(playerName,gameId);
            Response.sendOkResponse(ctx);
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("something went wrong with declareBankruptcy");
        }
    }

    private void buyProperty(RoutingContext ctx) {
        try {
            Request request = Request.from(ctx);
            if (!request.isAuthorized(request.getPathParameterValueString("gameId"), request.getPathParameterValueString("playerName"))) {
                throw new AuthenticationException();
            }
            String gameId = request.getPathParameterValueString("gameId");
            String playerName = request.getPathParameterValueString("playerName");
            String propertyName = request.getPathParameterValueString("propertyName");
            service.buyProperty(gameId,playerName,propertyName);
            Response.sendOkResponse(ctx);
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("failed to buy property");
        } catch (AuthenticationException e) {
            throw new InvalidRequestException("failed to authenticate");
        }catch (IllegalStateException e) {
            throw new IllegalStateException("failed to buy property");
        }
    }

    private void dontBuyProperty(RoutingContext ctx) {
        Request request = Request.from(ctx);
        String gameId = request.getPathParameterValueString("gameId");
        String playerName = request.getPathParameterValueString("playerName");
        String propertyName = request.getPathParameterValueString("propertyName");
        service.dontBuyProperty(gameId, playerName, propertyName);
        Response.sendOkResponse(ctx);
    }

    private void collectDebt(RoutingContext ctx) {
        Request request = Request.from(ctx);
        try {
            String game = request.getPathParameterValueString("gameId");
            String  player = request.getPathParameterValueString("playerName");
            String  debtPlayer = request.getPathParameterValueString("debtorName");
            String tileName = request.getPathParameterValueString("propertyName");
            service.collectDebt(game,player,debtPlayer,tileName);
            Response.sendOkResponse(ctx);
        }catch (IllegalArgumentException e){
            throw new IllegalStateException("Failed to pay rent");
        }
    }

    private void takeMortgage(RoutingContext ctx) {
        Request request = Request.from(ctx);
        String playerName = request.getPathParameterValueString("playerName");
        String gameId = request.getPathParameterValueString("gameId");
        String propertyName = request.getPathParameterValueString("propertyName");
        try {
            service.takeMortgage(gameId, playerName, propertyName);
            Response.sendOkResponse(ctx);
        }catch (IllegalStateException e){
            throw new IllegalStateException("cant mortgage property");
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e);
        }
    }

    private void settleMortgage(RoutingContext ctx) {
        Request request = Request.from(ctx);
        String playerName = request.getPathParameterValueString("playerName");
        String gameId = request.getPathParameterValueString("gameId");
        String propertyName = request.getPathParameterValueString("propertyName");
        try {
            service.settleMortgage(gameId, playerName, propertyName);
            Response.sendOkResponse(ctx);
        }catch (IllegalArgumentException e){
            throw new IllegalStateException(e);
        }

    }

    private void buyHouse(RoutingContext ctx) {
        Request request = Request.from(ctx);
        String playerName = request.getPathParameterValueString("playerName");
        String gameId = request.getPathParameterValueString("gameId");
        String propertyName = request.getPathParameterValueString("propertyName");
        service.buyHouse(gameId, playerName, propertyName);
        Response.sendOkResponse(ctx);
    }

    private void sellHouse(RoutingContext ctx) {
        Request request = Request.from(ctx);
        String gameId = request.getPathParameterValueString("gameId");
        String playerName = request.getPathParameterValueString("playerName");
        String propertyName = request.getPathParameterValueString("propertyName");
        service.sellHouse(gameId, playerName, propertyName);
        Response.sendOkResponse(ctx);
    }

    private void buyHotel(RoutingContext ctx) {
        Request request = Request.from(ctx);
        String gameId = request.getPathParameterValueString("gameId");
        String playerName = request.getPathParameterValueString("playerName");
        String propertyName = request.getPathParameterValueString("propertyName");
        service.buyHotel(gameId, playerName, propertyName);
        Response.sendOkResponse(ctx);
    }

    private void sellHotel(RoutingContext ctx) {
        Request request = Request.from(ctx);
        String gameId = request.getPathParameterValueString("gameId");
        String playerName = request.getPathParameterValueString("playerName");
        String propertyName = request.getPathParameterValueString("propertyName");
        service.sellHotel(gameId, playerName, propertyName);
        Response.sendOkResponse(ctx);
    }

    private void getOutOfJailFine(RoutingContext ctx) {
        Request request = Request.from(ctx);
        try {
            authorizationCheck(request);
            String playerName = request.getPathParameterValueString("playerName");
            String gameId = request.getPathParameterValueString("gameId");
            service.getOutOfJailFine(gameId,playerName);
        } catch (AuthenticationException e) {
            throw new InvalidTokenException();
        }
        Response.sendOkResponse(ctx);
    }

    private void getOutOfJailFree(RoutingContext ctx) {
        Request request = Request.from(ctx);
        try {
            authorizationCheck(request);
            String playerName = request.getPathParameterValueString("playerName");
            String gameId = request.getPathParameterValueString("gameId");
            service.getOutOfJailFree(gameId,playerName);
        } catch (AuthenticationException e) {
            throw new InvalidTokenException();
        }
        Response.sendOkResponse(ctx);
    }
    private void getBankAuctions(RoutingContext ctx) {
        throw new NotYetImplementedException("getBankAuctions");
    }

    private void placeBidOnBankAuction(RoutingContext ctx) {
        throw new NotYetImplementedException("placeBidOnBankAuction");
    }

    private void getPlayerAuctions(RoutingContext ctx) {
        Request request = Request.from(ctx);
        String gameId = request.getPathParameterValueString("gameId");
        Response.sendJsonResponse(ctx, 200, service.getPlayerAuctions(gameId));
    }

    private void startPlayerAuction(RoutingContext ctx) {
        throw new NotYetImplementedException("startPlayerAuction");
    }

    private void placeBidOnPlayerAuction(RoutingContext ctx) {
        Request request = Request.from(ctx);
        String gameId = request.getPathParameterValueString("gameId");
        String bidder = request.getBodyValueString("bidder");
        int amount = request.getBodyValueInteger("amount");
        service.placeBidOnPlayerAuction(gameId, bidder, amount);
        Response.sendOkResponse(ctx);
    }

    private void trade(RoutingContext ctx) {
        throw new NotYetImplementedException("trade");
    }

    private void onFailedRequest(RoutingContext ctx) {
        Throwable cause = ctx.failure();
        int code = ctx.statusCode();
        String quote = Objects.isNull(cause) ? "" + code : cause.getMessage();

        // Map custom runtime exceptions to a HTTP status code.
        LOGGER.log(Level.INFO, "Failed request", cause);
        if (cause instanceof InvalidRequestException) {
            code = 400;
        } else if (cause instanceof IllegalArgumentException) {
            code = 400;
        } else if (cause instanceof InsufficientFundsException) {
            code = 402;
        } else if (cause instanceof ForbiddenAccessException) {
            code = 403;
        } else if (cause instanceof MonopolyResourceNotFoundException) {
            code = 404;
        } else if (cause instanceof IllegalMonopolyActionException) {
            code = 409;
        } else if (cause instanceof NotYetImplementedException) {
            code = 501;
        } else {
            LOGGER.log(Level.WARNING, "Failed request", cause);
        }

        Response.sendFailure(ctx, code, quote);
    }

    private CorsHandler createCorsHandler() {
        return CorsHandler.create(".*.")
                .allowedHeader("x-requested-with")
                .allowedHeader("Access-Control-Allow-Origin")
                .allowedHeader("origin")
                .allowedHeader("Content-Type")
                .allowedHeader("accept")
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.OPTIONS)
                .allowedMethod(HttpMethod.PATCH)
                .allowedMethod(HttpMethod.DELETE)
                .allowedMethod(HttpMethod.PUT);
    }
}
