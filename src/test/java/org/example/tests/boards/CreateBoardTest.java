package org.example.tests.boards;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class CreateBoardTest {

    private static final String BASE_URL = "https://api.trello.com/1";
    private static final String BOARDS = "/boards";
    private static final String KEY = "fdab1639d2dd56a92c48fab8f8c3e1fe";
    private static final String TOKEN = "ATTA18ed3c705c06a3d94b4f1bdf634793f68cf6a4a68deeda2e3c46a1ff4476fd013AC98DB8";
    private final String boardName = "Pierwszy board";
    private String boardId;

    @Test
    void createBoardTest() {

        // CREATE BOARD
        final Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .queryParam("name", boardName)
                .when()
                .post(BASE_URL + BOARDS)
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
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .pathParam("boardId", boardId)
                .when()
                .delete(BASE_URL + BOARDS + "/{boardId}")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(200, responseAfterDelete.statusCode());
    }
}
