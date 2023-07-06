package org.example.tests.boards;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.secrets.TrelloSecrets;
import org.example.url.TrelloUrl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class CreateBoardTest {


    private final String boardName = "Pierwszy board";
    private String boardId;

    @Test
    void createBoardTest() {

        // CREATE BOARD
        final Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("key", TrelloSecrets.getKey())
                .queryParam("token", TrelloSecrets.getToken())
                .queryParam("name", boardName)
                .when()
                .post(TrelloUrl.getBoardsUrl())
                .then()
                .extract()
                .response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, response.getStatusCode());

        JsonPath json = response.jsonPath();
        Assertions.assertEquals(boardName, json.getString("name"));
        boardId = json.getString("id");

        // DELETE BOARD
        final Response responseAfterDelete = given()
                .contentType(ContentType.JSON)
                .queryParam("key", TrelloSecrets.getKey())
                .queryParam("token", TrelloSecrets.getToken())
                .when()
                .delete(TrelloUrl.getBoardUrl(boardId))
                .then()
                .extract()
                .response();

        Assertions.assertEquals(200, responseAfterDelete.statusCode());
    }
}
