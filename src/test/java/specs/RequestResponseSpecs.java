package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;


public class RequestResponseSpecs {
    public static RequestSpecification commonRequestSpecification = with()
            .log().all()
            .contentType(JSON);

    public static RequestSpecification authorizedRequestSpec(String token) {
        return with()
                .log().uri()
                .log().body()
                .log().headers()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token);

    }

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .build();
}
