package ru.csu.iit.backend.services;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.csu.iit.backend.builders.DatasetRequestBuilder2;

import java.util.Properties;

public class DatasetsService2 extends BaseService {
    public DatasetsService2(Properties properties) {
        super(properties);
    }

    public DatasetRequestBuilder2 request() {
        return new DatasetRequestBuilder2(baseRequest());
    }

    public Response execute(RequestSpecification requestSpecification) {
        return requestSpecification.post("datasets");
    }
}