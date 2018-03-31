package ru.csu.iit.backend.builders;

import io.restassured.specification.RequestSpecification;

public class DatasetRequestBuilder2 {
    private RequestSpecification requestSpecification;

    public DatasetRequestBuilder2(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public DatasetRequestBuilder2 filter(String filter) {
        requestSpecification.queryParams("$filter", filter);
        return this;
    }

    public DatasetRequestBuilder2 contains(String attributeName, String attributeValueSubstring) {
        return filter(String.format("%s eq '%s'", attributeName, attributeValueSubstring));
    }

    public DatasetRequestBuilder2 getFields(String... fields) {
        requestSpecification.body(fields);
        return this;
    }

    public RequestSpecification build() {
        return requestSpecification;
    }
}
