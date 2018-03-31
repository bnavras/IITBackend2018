package ru.csu.iit.backend.services;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.csu.iit.backend.builders.DatasetRequestBuilder;
import ru.csu.iit.backend.models.DatasetModel;

import java.util.Properties;

public class DatasetsService extends BaseService {
    public DatasetsService(Properties properties) {
        super(properties);
    }

    public DatasetRequestBuilder request() {
        return new DatasetRequestBuilder(baseRequest());
    }

    public DatasetModel[] execute(RequestSpecification requestSpecification) {
        return requestSpecification.post("datasets/1661/rows").then()
                .extract()
                .body().as(DatasetModel[].class);
    }

    public Response executeRow(RequestSpecification requestSpecification) {
        return requestSpecification.post("datasets/1661/rows");
    }
}
