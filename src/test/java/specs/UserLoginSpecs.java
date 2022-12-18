package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.is;

public class UserLoginSpecs {
    public static RequestSpecification loginUserRequestSpec = with()
            .baseUri("https://reqres.in/api")
            .log().all()
            .contentType(ContentType.JSON);
    public static ResponseSpecification loginUserResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}
