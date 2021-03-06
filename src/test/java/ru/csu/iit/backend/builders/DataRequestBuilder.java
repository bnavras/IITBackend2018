package ru.csu.iit.backend.builders;

import io.restassured.specification.RequestSpecification;

public class DatasetRequestBuilder {
    private RequestSpecification requestSpecification;

    public DatasetRequestBuilder(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public DatasetRequestBuilder top(int top) {
        requestSpecification.queryParams("$top", top);
        return this;
    }

    public DatasetRequestBuilder filter(String filter) {
        requestSpecification.queryParams("$filter", filter);
        return this;
    }

    public DatasetRequestBuilder contains(String attributeName, String attributeValueSubstring) {
        return filter(String.format("Cells/%s eq %s", attributeName, attributeValueSubstring));
    }

    public DatasetRequestBuilder orderby(String attributeName){
        requestSpecification.queryParams("$orderby", attributeName);
        return this;
    }

    public RequestSpecification build() {
        return requestSpecification;
    }
}
