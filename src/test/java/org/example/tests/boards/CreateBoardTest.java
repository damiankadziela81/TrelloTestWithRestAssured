package org.example.tests.boards;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.requests.board.CreateBoardRequest;
import org.example.requests.board.DeleteBoardRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class CreateBoardTest {


    private final String boardName = "Pierwszy board";
    private String boardId;

    @Test
    void createBoardTest() {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", boardName);

        // CREATE BOARD
        final Response response = CreateBoardRequest.createBoardRequest(queryParams);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, response.getStatusCode());

        JsonPath json = response.jsonPath();
        Assertions.assertEquals(boardName, json.getString("name"));
        boardId = json.getString("id");

        // DELETE BOARD
        final Response responseAfterDelete = DeleteBoardRequest.deleteBoardRequest(boardId);
        Assertions.assertEquals(200, responseAfterDelete.statusCode());
    }
}
