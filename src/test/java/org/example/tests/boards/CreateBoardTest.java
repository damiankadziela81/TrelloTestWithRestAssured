package org.example.tests.boards;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.requests.board.CreateBoardRequest;
import org.example.requests.board.DeleteBoardRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class CreateBoardTest {

    private String boardId;

    @DisplayName("Create a board with valid data")
    @ParameterizedTest(name = "Board name: {0}")
    @MethodSource("sampleBoardNameData")
    void createBoardTest(String boardName) {

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

    private static Stream<Arguments> sampleBoardNameData() {
        return Stream.of(
                Arguments.of("nazwaTablicy"),
                Arguments.of("NAZWA TABLICY"),
                Arguments.of("NAZWA_TABLICY"),
                Arguments.of("!"),
                Arguments.of("@"),
                Arguments.of("#"),
                Arguments.of("$"),
                Arguments.of("%"),
                Arguments.of("^"),
                Arguments.of("&"),
                Arguments.of("*")
        );
    }
}
