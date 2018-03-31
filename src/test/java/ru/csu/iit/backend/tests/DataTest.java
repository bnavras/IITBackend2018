package ru.csu.iit.backend.tests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import ru.csu.iit.backend.models.DatasetModel;
import ru.csu.iit.backend.services.DatasetsService;
import ru.csu.iit.backend.services.DatasetsService2;


import java.util.Arrays;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;
import static ru.csu.iit.backend.StringContainsIgnoringCase.containsStringIgnoringCase;

//iit-114
public class ExampleTest extends BaseTest {
    @Test
    public void dataProjectionQuery() {
        DatasetsService2 datasetsService = new DatasetsService2(properties);
        //checkDataQuery(datasetsService);
        checkIdQuery(datasetsService);
    }

    private void checkDataQuery(DatasetsService datasetsService) {
        RequestSpecification requestSpecification = datasetsService.request()
                .top(10)
                .contains("District", "басман")
                .orderby("global_id")
                .build();
        DatasetModel[] datasets = datasetsService.execute(requestSpecification);
        assertThat(Arrays.asList(datasets), hasSize(10));

        DatasetModel[] actual = datasets.clone();
        Arrays.sort(actual);
        assertArrayEquals("The result is not sorted", datasets, actual);

        for (DatasetModel dataset : datasets) {
            assertThat(dataset.getCells().getDistrict(), containsStringIgnoringCase("басман"));
        }
    }

    private int checkIdQuery(DatasetsService2 datasetsService) {
        RequestSpecification requestSpecification = datasetsService.request()
                .contains("Caption", "Общественные пункты охраны порядка")
                .getFields("Id")
                .build();
        Response response = datasetsService.execute(requestSpecification);
        response.then()
                .body("Id", hasSize(1))
                .body("Id", everyItem(isA(Integer.class)));

        return (Integer) response.jsonPath().getList("Id").get(0);
    }
}
