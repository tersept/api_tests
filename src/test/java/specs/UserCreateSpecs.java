package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class UserCreateSpecs {
    public static RequestSpecification createUserRequestSpec = with()
            .baseUri("https://reqres.in/api")
            .basePath("/users")
            .log().all()
            .contentType(ContentType.JSON);
    public static ResponseSpecification createUserResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}
