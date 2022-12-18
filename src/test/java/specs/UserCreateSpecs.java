package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.Builder;

import static io.restassured.RestAssured.with;
@Builder
public class UserCreateSpecs {
    public static RequestSpecification createUserRequestSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in/api")
            .log(LogDetail.ALL)
            .setContentType(ContentType.JSON)
            .build();
    public static ResponseSpecification createUserResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}
