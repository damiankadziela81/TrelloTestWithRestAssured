package org.example.tests.boards;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.requests.board.CreateBoardRequest;
import org.example.requests.board.DeleteBoardRequest;
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

        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(boardName);
        boardId = json.getString("id");

        // DELETE BOARD
        final Response responseAfterDelete = DeleteBoardRequest.deleteBoardRequest(boardId);
        Assertions.assertThat(responseAfterDelete.statusCode()).isEqualTo(200);
    }
}
