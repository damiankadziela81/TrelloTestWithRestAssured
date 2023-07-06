package org.example.requests.card;

import io.restassured.response.Response;
import org.example.requests.BaseRequest;
import org.example.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateCardRequest {

    public static Response createCardRequest(Map<String, String> queryParams) {
        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
                .when()
                .post(TrelloUrl.getCardsUrl())
                .then()
                .extract()
                .response();
    }
}
