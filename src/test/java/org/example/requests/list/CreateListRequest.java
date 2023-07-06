package org.example.requests.list;

import io.restassured.response.Response;
import org.example.requests.BaseRequest;
import org.example.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateListRequest {

    public static Response createListRequest(Map<String, String> queryParams) {
        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
                .when()
                .post(TrelloUrl.getListsUrl())
                .then()
                .extract()
                .response();
    }
}
