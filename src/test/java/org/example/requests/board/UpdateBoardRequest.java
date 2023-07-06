package org.example.requests.board;

import io.restassured.response.Response;
import org.example.requests.BaseRequest;
import org.example.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdateBoardRequest {

    public static Response updateBoardRequest(Map<String, String> queryParams, String boardId) {
        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
                .when()
                .put(TrelloUrl.getBoardUrl(boardId))
                .then()
                .extract()
                .response();
    }
}
