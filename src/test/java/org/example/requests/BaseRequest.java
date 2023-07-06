package org.example.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.example.secrets.TrelloSecrets;

public class BaseRequest {

    protected static RequestSpecBuilder requestBuilder;

    public static RequestSpecification requestSetup() {
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.addQueryParam("key", TrelloSecrets.getKey());
        requestBuilder.addQueryParam("token", TrelloSecrets.getToken());

        return requestBuilder.build();
    }
}
