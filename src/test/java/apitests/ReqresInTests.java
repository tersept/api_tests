package apitests;

import models.lombok.CreateUserBodyLombokModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static specs.UserCreatSpecs.creatUserRequestSpec;
import static specs.UserCreatSpecs.creatUserResponseSpec;
import static specs.UserListSpecs.userListRequestSpec;
import static specs.UserListSpecs.userListResponseSpec;
import static specs.UserRegistrationSpecs.registrationUserRequestSpec;
import static specs.UserRegistrationSpecs.registrationUserResponseSpec;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class ReqresInTests {

    @DisplayName("Тест на успешное создание пользователя lombok модель")
    @Test
    void userCreationSuccessTest() {

        CreateUserBodyLombokModel body = new CreateUserBodyLombokModel();
        body.setName("morpheus");
        body.setJob("leader");

        CreateUserBodyLombokModel response = given()
                .spec(creatUserRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(creatUserResponseSpec)
                .body(
                        "id", is(not(emptyOrNullString())),
                        "createdAt", notNullValue()
                )
                .extract()
                .as(CreateUserBodyLombokModel.class);
        assertThat(response.getName()).isEqualTo("morpheus");
        assertThat(response.getJob()).isEqualTo("leader");
    }

    @DisplayName("Тест на успешную регистрацию lombok модель")
    @Test
    void userRegistrationLombokSuccessTest() {
        CreateUserBodyLombokModel body = new CreateUserBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("pistol");

        CreateUserBodyLombokModel response = given()
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

    @DisplayName("Тест на присутствие george.bluth@reqres.in в выдаче списка пользователей")
    @Test
    void userListEmailTest() {
        given()
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
                .spec(userListRequestSpec)
                .when()
                .get()
                .then()
                .spec(userListResponseSpec)
                .body("data.findAll{it.id}.id.flatten()",
                        hasItem(6));
    }
}

