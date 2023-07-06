package org.example.tests.boards.e2e;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.requests.board.CreateBoardRequest;
import org.example.requests.board.DeleteBoardRequest;
import org.example.requests.board.ReadBoardRequest;
import org.example.requests.board.UpdateBoardRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CreatePutDeleteBoardTest {

    private final String boardName = "Test board";
    private final String boardDesc = "Test description";
    private static String boardId;

    @Test
    @Order(1)
    void createNewBoardTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", boardName);

        final Response response = CreateBoardRequest.createBoardRequest(queryParams);

        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(boardName);
        boardId = json.getString("id");
    }

    @Test
    @Order(2)
    void editBoardDescriptionTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("desc", boardDesc);

        final Response response = UpdateBoardRequest.updateBoardRequest(queryParams, boardId);

        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("desc")).isEqualTo(boardDesc);
    }

    @Test
    @Order(3)
    void readExistingBoardTest() {
        final Response response = ReadBoardRequest.readBoardRequest(boardId);

        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(boardName);
    }

    @Test
    @Order(4)
    void deleteExistingBoardTest() {
        final Response response = DeleteBoardRequest.deleteBoardRequest(boardId);

        Assertions.assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    @Order(5)
    void readNonExistingBoardTest() {
        final Response response = ReadBoardRequest.readBoardRequest(boardId);

        Assertions.assertThat(response.statusCode()).isEqualTo(404);
        Assertions.assertThat(response.asString()).isEqualTo("The requested resource was not found.");
    }

    @Test
    @Order(6)
    void deleteNonExistingBoardTest() {
        final Response response = DeleteBoardRequest.deleteBoardRequest(boardId);

        Assertions.assertThat(response.statusCode()).isEqualTo(404);
        Assertions.assertThat(response.asString()).isEqualTo("The requested resource was not found.");
    }
}
