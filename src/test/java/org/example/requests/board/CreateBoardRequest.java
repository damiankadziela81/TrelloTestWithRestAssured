package org.example.requests.board;

import io.restassured.response.Response;
import org.example.requests.BaseRequest;
import org.example.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateBoardRequest {

    public static Response createBoardRequest(Map<String, String> queryParams) {
        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
                .when()
                .post(TrelloUrl.getBoardsUrl())
                .then()
                .extract()
                .response();
    }
}
