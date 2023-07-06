package org.example.tests.boards.e2e;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.requests.board.CreateBoardRequest;
import org.example.requests.list.CreateListRequest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class MoveCardBetweenListsTest {

    private final String boardName = "Table for E2E test";
    private final String firstListName = "First list for E2E test";
    private final String secondListName = "Second list for E2E test";
    private String boardId;
    private String firstListId;
    private String secondListId;

    @Test
    void scenarioE2ETest() {
        createNewBoardTest();
        createFirstListTest();
        createSecondListTest();
    }

    void createNewBoardTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", boardName);

        final Response createBoardResponse = CreateBoardRequest.createBoardRequest(queryParams);

        Assertions.assertThat(createBoardResponse.statusCode()).isEqualTo(200);

        JsonPath json = createBoardResponse.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(boardName);
        boardId = json.getString("id");
    }

    //{{url}}/lists?name={{firstList}}&idBoard={{boardId}}&key={{key}}&token={{token}}
    void createFirstListTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", firstListName);
        queryParams.put("idBoard", boardId);

        final Response response = CreateListRequest.createListRequest(queryParams);

        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(firstListName);
        firstListId = json.getString("id");
    }

    void createSecondListTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", secondListName);
        queryParams.put("idBoard", boardId);

        final Response response = CreateListRequest.createListRequest(queryParams);

        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(secondListName);
        secondListId = json.getString("id");
    }
}
