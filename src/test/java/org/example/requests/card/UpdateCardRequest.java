package org.example.requests.card;

import io.restassured.response.Response;
import org.example.requests.BaseRequest;
import org.example.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdateCardRequest {

    public static Response updateCardRequest(Map<String, String> queryParams, String cardId) {
        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
                .when()
                .put(TrelloUrl.getCardUrl(cardId))
                .then()
                .extract()
                .response();
    }
}
