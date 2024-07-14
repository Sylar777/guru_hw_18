package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;


public class RequestResponseSpecs {
    public static RequestSpecification commonRequestSpecification = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON);

    public static RequestSpecification authorizedRequestSpec(String token) {
        return with()
                .filter(withCustomTemplates())
                .log().all()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token);
    }

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .build();
}
