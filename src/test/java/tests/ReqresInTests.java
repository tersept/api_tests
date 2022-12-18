package tests;

import lombok.Builder;
import models.lombok.CreateUserBodyLombokModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import specs.UserCreateSpecs;

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
    @Builder
    void userCreationSuccessTest() {

        CreateUserBodyLombokModel body = CreateUserBodyLombokModel.builder()
                .name("morpheus")
                .job("leader")
                .build();

        CreateUserBodyLombokModel response = given().spec(createUserRequestSpec)
                .body(body)
                .when()
                .post("/users")
                .then()
                .spec(createUserResponseSpec)
                .statusCode(201)
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
        CreateUserBodyLombokModel body = CreateUserBodyLombokModel.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();

        CreateUserBodyLombokModel response = given().spec(registrationUserRequestSpec)
                .body(body)
                .when()
                .post("/register")
                .then()
                .spec(registrationUserResponseSpec)
                .statusCode(200)
                .body("id", is(not(emptyOrNullString())))
                .body("token", is(not(emptyOrNullString())))
                .extract()
                .as(CreateUserBodyLombokModel.class);
        assertThat(response.getId()).isEqualTo("4");
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @DisplayName("Тест на безуспешную регистрацию")
    @Test
    void userRegistrationUnSuccessTest() {
        CreateUserBodyLombokModel body = CreateUserBodyLombokModel.builder()
                .email("eve.holt@reqres")
                .build();

        CreateUserBodyLombokModel response = given().spec(registrationUserRequestSpec)
                .body(body)
                .when()
                .post("/register")
                .then()
                .spec(registrationUserResponseSpec)
                .statusCode(400)
                .extract()
                .as(CreateUserBodyLombokModel.class);
        assertThat(response.getError()).isEqualTo("Missing password");
    }

    @DisplayName("Тест на успешную авторизацию")
    @Test
    void userLoginSuccessTest() {
        CreateUserBodyLombokModel body = CreateUserBodyLombokModel.builder()
                .email("eve.holt@reqres.in")
                .password("cityslick")
                .build();

        CreateUserBodyLombokModel response = given().spec(loginUserRequestSpec)
                .body(body)
                .when()
                .post("/login")
                .then()
                .spec(loginUserResponseSpec)
                .statusCode(200)
                .body("token", is(not(emptyOrNullString())))
                .extract()
                .as(CreateUserBodyLombokModel.class);
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @DisplayName("Тест на безуспешную авторизацию")
    @Test
    void userLoginUnSuccessTest() {
        CreateUserBodyLombokModel body = CreateUserBodyLombokModel.builder()
                .email("eve.holt@reqres")
                .build();

        CreateUserBodyLombokModel response = given().spec(loginUserRequestSpec)
                .body(body)
                .when()
                .post("/login")
                .then()
                .spec(loginUserResponseSpec)
                .statusCode(400)
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
                .statusCode(200)
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
                .statusCode(200)
                .body("data.findAll{it.id}.id.flatten()",
                        hasItem(6));
    }
}

