package apitests;

import models.lombok.CreateUserBodyLombokModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static specs.UserCreateSpecs.*;
import static specs.UserListSpecs.userListRequestSpec;
import static specs.UserListSpecs.userListResponseSpec;
import static specs.UserLoginSpecs.*;
import static specs.UserRegistrationSpecs.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class ReqresInTests {

    @DisplayName("Тест на успешное создание пользователя")
    @Test
    void userCreationSuccessTest() {

        CreateUserBodyLombokModel body = new CreateUserBodyLombokModel();
        body.setName("morpheus");
        body.setJob("leader");

        CreateUserBodyLombokModel response = given()
                .filter(withCustomTemplates())
                .spec(createUserRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(createUserResponseSpec)
                .body(
                        "id", is(not(emptyOrNullString())),
                        "createdAt", notNullValue()
                )
                .extract()
                .as(CreateUserBodyLombokModel.class);
        assertThat(response.getName()).isEqualTo("morpheus");
        assertThat(response.getJob()).isEqualTo("leader");
    }

    @DisplayName("Тест на успешную регистрацию")
    @Test
    void userRegistrationLombokSuccessTest() {
        CreateUserBodyLombokModel body = new CreateUserBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("pistol");

        CreateUserBodyLombokModel response = given()
                .filter(withCustomTemplates())
                .spec(registrationUserRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(registrationUserResponseSpec)
                .extract()
                .as(CreateUserBodyLombokModel.class);
        assertThat(response.getId()).isEqualTo("4");
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }
    @DisplayName("Тест на безуспешную регистрацию")
    @Test
    void userRegistrationUnSuccessTest() {
        CreateUserBodyLombokModel body = new CreateUserBodyLombokModel();
        body.setEmail("eve.holt@reqres");

        CreateUserBodyLombokModel response = given()
                .filter(withCustomTemplates())
                .spec(registrationUserRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(registrationUser400ResponseSpec)
                .extract()
                .as(CreateUserBodyLombokModel.class);
        assertThat(response.getError()).isEqualTo("Missing password");
    }
    @DisplayName("Тест на успешную авторизацию")
    @Test
    void userLoginSuccessTest() {
        CreateUserBodyLombokModel body = new CreateUserBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslick");

        CreateUserBodyLombokModel response = given()
                .filter(withCustomTemplates())
                .spec(loginUserRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(loginUserResponseSpec)
                .extract()
                .as(CreateUserBodyLombokModel.class);
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }@DisplayName("Тест на безуспешную авторизацию")
    @Test
    void userLoginUnSuccessTest() {
        CreateUserBodyLombokModel body = new CreateUserBodyLombokModel();
        body.setEmail("eve.holt@reqres");

        CreateUserBodyLombokModel response = given()
                .filter(withCustomTemplates())
                .spec(loginUserRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(loginUser400ResponseSpec)
                .extract()
                .as(CreateUserBodyLombokModel.class);
        assertThat(response.getError()).isEqualTo("Missing password");
    }
    @DisplayName("Тест на присутствие george.bluth@reqres.in в выдаче списка пользователей")
    @Test
    void userListEmailTest() {
        given()
                .filter(withCustomTemplates())
                .spec(userListRequestSpec)
                .when()
                .get()
                .then()
                .spec(userListResponseSpec)
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("george.bluth@reqres.in"));
    }

    @DisplayName("Тест на проверку количества элементов на выдаче")
    @Test
    void userListIDTest() {
        given()
                .filter(withCustomTemplates())
                .spec(userListRequestSpec)
                .when()
                .get()
                .then()
                .spec(userListResponseSpec)
                .body("data.findAll{it.id}.id.flatten()",
                        hasItem(6));
    }
}

