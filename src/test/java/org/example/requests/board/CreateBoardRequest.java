package org.example.requests.board;

import io.restassured.response.Response;
import org.example.requests.BaseRequest;
import org.example.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class CreateBoardRequest {

    public static Response createBoardRequest(String boardName) {
        return given()
                .spec(BaseRequest.requestSetup())
                .queryParam("name", boardName)
                .when()
                .post(TrelloUrl.getBoardsUrl())
                .then()
                .extract()
                .response();

    }
}
