package org.example.requests.board;

import io.restassured.response.Response;
import org.example.requests.BaseRequest;
import org.example.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class ReadBoardRequest {

    public static Response readBoardRequest(String boardId) {
        return given()
                .spec(BaseRequest.requestSetup())
                .when()
                .get(TrelloUrl.getBoardUrl(boardId))
                .then()
                .extract()
                .response();
    }
}
