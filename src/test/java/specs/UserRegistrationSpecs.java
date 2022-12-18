package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class UserRegistrationSpecs {
    public static RequestSpecification registrationUserRequestSpec = with()
            .baseUri("https://reqres.in/api")
            .basePath("/register")
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification registrationUserResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();

}
